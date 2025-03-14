package cp31_800;

import java.io.*;

public class Buttons {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i = 0; i < t; i++) {
            int[] vars = io.readLineAsIntArray();
            int a=vars[0], b=vars[1], c=vars[2];
            String first = "First", second = "Second";
            if (a > b) {
                io.writeLine(first);
            } else if (b > a) {
                io.writeLine(second);
            } else {
                if ((c&1) == 0) {
                    io.writeLine(second);
                } else {
                    io.writeLine(first);
                }
            }
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

        void writeLine(int i) {
            writer.println(i);
        }

        void writeArray(int[] arr) {
            int n = arr.length;
            for (int i=0; i<n; i++) {
                writer.print(arr[i]);
                if (i<n-1) writer.print(" ");
            }
            writer.println();
        }

        void writeLine(String s) {
            writer.println(s);
        }

        void writeIntsWithNewline(int... num) {
            for (int i=0; i<num.length; i++) {
                writer.print(num[i]);
                if (i<num.length-1) writer.print(" ");
            }
            writer.println();
        }

        void flushOutput() {
            writer.flush();
        }

        int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            return nums;
        }

        int readLineAsInt() {
            String line = readLine();
            return line == null ? 0 : Integer.parseInt(line);
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
