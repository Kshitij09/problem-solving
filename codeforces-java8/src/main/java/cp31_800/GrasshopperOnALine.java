package cp31_800;

import java.io.*;
import java.util.function.Supplier;

public class GrasshopperOnALine {
    public static void main(String[] args) {
        IO io = new IO(System.in , System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            String line = io.readLine();
            final int tc = i+1;
            require(line != null, () -> String.format("line was null for a testcase %d", tc));
            String[] tokens = line.split(" ");
            require(tokens.length == 2, () -> String.format("invalid no. of variables for a testcase %d", tc));
            int x = Integer.parseInt(tokens[0]), k = Integer.parseInt(tokens[1]);
            if (x % k != 0) {
                writeAnswer(io, 1, new int[]{x});
            } else {
                writeAnswer(io, 2, new int[]{x-1, 1});
            }
        }
        io.flushOutput();
    }

    private static void writeAnswer(IO io, int moveCount, int[] moves) {
        io.writeLine(moveCount);
        io.writeLine(moves);
    }

    private static void require(boolean predicate, Supplier<String> message) {
        if (!predicate) throw new IllegalArgumentException(message.get());
    }

    private static final class IO {
        private final BufferedReader reader;
        private final PrintWriter writer;
        IO(InputStream is, OutputStream os) {
            Reader reader = new InputStreamReader(is);
            this.reader = new BufferedReader(reader);
            Writer writer = new OutputStreamWriter(os);
            writer = new BufferedWriter(writer);
            this.writer = new PrintWriter(writer);
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
            return (line==null) ? 0 : Integer.parseInt(line);
        }

        void writeLine(int x) {
            writer.println(x);
        }

        void writeLine(int... arr) {
            for (int i=0; i<arr.length; i++) {
                writer.print(arr[i]);
                if (i<arr.length-1) writer.print(' ');
            }
            writer.println();
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
/*
10 2
9 1


10 3
10

3 4
3


if x % k != 0 return 1, [x]

12 6

if x % k == 0 return 2,[x-1, 1]

12 8
11 1

14 13

7 7


100 11
100 1

 */
