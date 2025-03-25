package random;

import java.io.*;
import java.util.*;

public class Dijkstra {
    private record Path(int node, int weight) {}

    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int[] vars = io.readLineAsIntArray();
        int n = vars[0]; // Number of nodes
        int m = vars[1]; // Number of edges

        List<List<Path>> graph = new ArrayList<>(n + 1);
        for (int i = 1; i <= n+1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int[] edge = io.readLineAsIntArray();
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new Path(v, w));
            graph.get(v).add(new Path(u, w));
        }

        dijkstra(io, n, graph);
    }

    private static void dijkstra(IO io, int n, List<List<Path>> graph) {
        long[] dist = new long[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparing(Path::weight));
        pq.add(new Path(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            int u = curr.node;
            if (curr.weight > dist[u]) continue; // Outdated entry

            for (Path edge : graph.get(u)) {
                int v = edge.node, w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.add(new Path(v, (int) dist[v]));
                }
            }
        }

        if (dist[n] == Long.MAX_VALUE) {
            io.writeLine("-1");
            io.flushOutput();
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int v = n; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        for (int v : path) {
            io.writeWithSpace(v);
        }
        io.flushOutput();
    }

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

        void writeLine(String s) {
            writer.println(s);
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