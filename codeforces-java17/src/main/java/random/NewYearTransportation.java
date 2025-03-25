package random;

import java.io.*;

// https://codeforces.com/problemset/problem/500/A
public class NewYearTransportation {

    public static void main(String[] args) {
        var io = new IO(System.in, System.out);
        var line = io.readLine();
        if (line == null) throw new IllegalArgumentException("Invalid input");
        var tokens = line.split(" ");
        if (tokens.length != 2) throw new IllegalArgumentException("Invalid input");
        int n = Integer.parseInt(tokens[0]), t = Integer.parseInt(tokens[1]);
        var link = io.readLineAsIntArray();
        if (link.length != n-1) throw new IllegalArgumentException("Invalid input");
        var solver = new Solver();
        boolean reachable = solver.hasPath(link, n, t);
        io.writeLine(reachable ? "YES" : "NO");
        io.flushOutput();
    }

    private static final class Solver {
        private boolean[] visited;
        private int[] link;
        private int cells;

        boolean hasPath(int[] link, int cells, int dest) {
            this.link = link;
            this.cells = cells;
            visited = new boolean[cells];
            return hasPath(0, dest-1);
        }
        private boolean hasPath(int from, int to) {
            if (from == to) return true;
            if (from >= cells-1) return false;
            visited[from] = true;
            int next = from + link[from];
            if (!visited[next]) {
                return hasPath(next, to);
            }
            visited[from] = false;
            return false;
        }
    }

    private static final class IO {
        private final BufferedReader reader;
        private final PrintWriter writer;

        IO(InputStream i, OutputStream o) {
            reader = new BufferedReader(new InputStreamReader(i));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
        }

        int[] readLineAsIntArray() {
            final var line = readLine();
            if (line == null) return new int[0];
            final var tokens = line.split(" ");
            final var arr = new int[tokens.length];
            for (int i=0; i<tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            return arr;
        }

        void writeLine(String s) {
            writer.println(s);
        }

        void flushOutput() {
            writer.flush();
        }

        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return null;
            }
        }
    }
}
