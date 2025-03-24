package random;

import java.io.*;
import java.util.*;

public class MSTinModuleGaph_Kruskal {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        MSTinModuleGaph_Kruskal solver = new MSTinModuleGaph_Kruskal();
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

    int mstWeight(int[] nodes) {
        int n = nodes.length;
        DSU dsu = new DSU(n);
        List<Edge> edges = new ArrayList<>((n-1)*n/2);
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int wt = Math.max(nodes[i], nodes[j]) % Math.min(nodes[i], nodes[j]);
                edges.add(new Edge(i,j,wt));
            }
        }
        edges.sort(Comparator
                .comparing(Edge::weight)
                .thenComparing(Edge::src));

        int mstWeight = 0, edgesAdded=0;
        for (Edge e : edges) {
            if (dsu.union(e.src, e.dst)) {
                mstWeight += e.weight;
            }
            if (edgesAdded == n - 1) break;
        }
        return mstWeight;
    }

    private static final class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i=0; i<n; i++) parent[i] = i;
        }

        int findParent(int u) {
            if (parent[u] != u) {
                parent[u] = findParent(parent[u]);
            }
            return parent[u];
        }

        boolean union(int u, int v) {
           int pu = findParent(u);
           int pv = findParent(v);
           if (pu == pv) return false;
           if (size[pv] <= size[pu]) {
               parent[pv] = pu;
               size[pu] += size[pv];
           } else {
               parent[pu] = pv;
               size[pv] += size[pu];
           }
           return true;
        }
    }

    private record Edge(int src, int dst, int weight) {}

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
