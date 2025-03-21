package cp31_800;

import java.io.*;

public class BlankSpace {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            io.readLine();
            int[] arr = io.readLineAsIntArray();
            int max = 0, curr=0;
            for (int bit : arr) {
                if (bit == 1) {
                    curr = 0;
                } else {
                    curr++;
                }
                max = Math.max(curr, max);
            }
            io.writeLine(max);
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

        int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            return arr;
        }

        int readLineAsInt() {
            String line = readLine();
            return (line == null) ? 0 : Integer.parseInt(line);
        }

        String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        void writeLine(int... nums) {
            int n = nums.length;
            for (int i=0; i<n; i++) {
                writer.print(nums[i]);
                if (i<n-1) writer.print(' ');
            }
            writer.println();
        }

        void writeLine() {
            writer.println();
        }

        void write(int x) {
            writer.print(x);
        }

        void write(char c) {
            writer.print(c);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
