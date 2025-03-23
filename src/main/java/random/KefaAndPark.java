package random;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//n https://codeforces.com/problemset/problem/580/C
public class KefaAndPark {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int[] vars = io.readLineAsIntArray();
        require(vars.length == 2);
        int vertices = vars[0], maxCats = vars[1];
        String line = io.readLine();
        require(line != null);
        String[] tokens = line.split(" ");
        require(tokens.length == vertices);
        boolean[] hasCat = new boolean[vertices];
        for (int i=0; i<tokens.length; i++) hasCat[i] = tokens[i].equals("1");
        int[][] edges = new int[vertices-1][2];
        for (int i=0; i<vertices-1; i++) {
            int[] edge = io.readLineAsIntArray();
            require(edge.length == 2);
            edges[i] = edge;
        }
        var safeRestaurants = new SafeRestaurants(hasCat, maxCats, edges);
        io.writeLine(safeRestaurants.count());
        io.flushOutput();
    }

    private static final class SafeRestaurants {
        private final boolean[] hasCat;
        private final int maxCats;
        private final List<List<Integer>> adjacency;

        SafeRestaurants(boolean[] hasCat, int maxCats, int[][] edges) {
            this.hasCat = hasCat;
            this.maxCats = maxCats;
            this.adjacency = buildAdjacency(hasCat.length, edges);
        }

        int count() {
            return count(-1, 0, 0);
        }

        private int count(int src, int dst, int catsOnTheRoute) {
            if (hasCat[dst]) catsOnTheRoute++; else catsOnTheRoute = 0;
            if (catsOnTheRoute > maxCats) return 0;
            if (isLeaf(src, dst)) return 1;
            int safeRestaurants = 0;
            for (int next: adjacency.get(dst)) {
                if (next == src) continue;
                safeRestaurants += count(dst, next, catsOnTheRoute);
            }
            return safeRestaurants;
        }


        private boolean isLeaf(int src, int dst) {
            var adj = adjacency.get(dst);
            return adj.size() == 1 && adj.get(0) == src;
        }

        private List<List<Integer>> buildAdjacency(int vertices, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>(vertices);
            for (int i=0; i<vertices; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge: edges) {
                int u=edge[0]-1, v=edge[1]-1;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            return adj;
        }
    }

    private static void require(boolean predicate) {
        if (!predicate) throw new IllegalArgumentException("Invalid input");
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

        void writeLine(int x) {
            writer.println(x);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
/*
8 1
0 1 1 0 1 0 0 1
1 2
1 3
2 4
2 5
3 6
3 7
3 8
 */