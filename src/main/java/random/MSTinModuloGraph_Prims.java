package random;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://codeforces.com/problemset/problem/2081/D
public class MSTinModuloGraph_Prims {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        MSTinModuloGraph_Prims solver = new MSTinModuloGraph_Prims();
        int t = io.readLineAsInt();
        while (t > 0) {
            io.readLine();
            int[] nodes = io.readLineAsIntArray();
            int mstWeight = solver.mstWeight(nodes);
            io.writeLine(mstWeight);
            t--;
        }
        io.flushOutput();
    }

    /*
    4 3 3 4 4

    1 0 0 1 1

    u=1
    pq={ [2,0],[1,1],[1,1],[2,1],[2,1],[2,1] }
    mw=1
     */
    int mstWeight(int[] nodes) {
        int n = nodes.length;
        Queue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::weight));
        pq.add(new Edge(0,0));
        boolean[] visited = new boolean[n];
        int mstWeight = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.node;
            if (visited[u]) continue;
            mstWeight += e.weight;
            visited[u] = true;
            for (int i=1; i<n; i++) {
                if (i == u || visited[i]) continue;
                int wt = Math.max(nodes[u],nodes[i]) % Math.min(nodes[u],nodes[i]);
                pq.add(new Edge(i, wt));
            }
        }
        return mstWeight;
    }

    private record Edge(int node, int weight) {}

    private static final class IO {
        private final BufferedReader reader;
        private final PrintWriter writer;

        IO(InputStream i, OutputStream o) {
            reader = new BufferedReader(new InputStreamReader(i));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
        }

        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return null;
            }
        }

        int readLineAsInt() {
            String line = readLine();
            return (line == null) ? 0 : Integer.parseInt(line);
        }

        int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int[] arr = new int[n];
            for (int i=0; i<n; i++) arr[i] = Integer.parseInt(tokens[i]);
            return arr;
        }

        void writeLine(int x) {
            writer.println(x);
        }

        void writeWithSpace(int x) {
            writer.print(x);
            writer.print(' ');
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
