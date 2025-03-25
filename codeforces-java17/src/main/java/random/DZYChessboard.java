package random;

import java.io.*;

// https://codeforces.com/problemset/problem/445/A
public class DZYChessboard {
    private final IO io;
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int[] vars = io.readLineAsIntArray();
        int n=vars[0], m=vars[1];
        char[][] board = new char[n][m];
        for (int i=0; i<n; i++) {
            io.readLineCharacters(board[i]);
        }
        DZYChessboard solver = new DZYChessboard(io);
        solver.solve(board);
        io.flushOutput();
    }

    DZYChessboard(IO io) {
        this.io = io;
    }

    private void solve(char[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == '-') {
                    io.write('-');
                    continue;
                }
                boolean isOddRow = (i&1) == 1;
                boolean isOddCol = (j&1) == 1;
                if (!isOddRow && !isOddCol) {
                    io.write('B');
                } else if (!isOddRow && isOddCol) {
                    io.write('W');
                } else if (isOddRow && !isOddCol) {
                    io.write('W');
                } else {
                    io.write('B');
                }
            }
            io.writeLine();
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

        void readLineCharacters(char[] buf) {
            try {
                reader.read(buf);
                reader.readLine();
            } catch (IOException ignored) {

            }
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

        void write(char c) {
            writer.print(c);
        }

        void writeLine() {
            writer.print(System.lineSeparator());
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
/*

b   -   b

-   b   w

b   w   -

ee - b
eo - w
oe - w
oo - b


 */
