package cp31_800;

import java.io.*;

public class TwinPermutation {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            int n = io.readLineAsInt();
            int[] nums = io.readLineAsIntArray();
            for (int j=0; j<n; j++) {
                io.write(n+1-nums[j]);
                if (j<n-1) io.write(' ');
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
/*
1 2 4 5 3
1 4 2 3 5

1 2
2 1

1
1

3 2 1
3 1 2

1 4 3 2
1 4 2 3



6 3 4 5 1 2
1 4 3 2 6 5



4 1 3 2
1 4 2 3



1 2 3 4 5
5 4 3 2 1


1 3 4 2 5
5 3 2 4 1

 */
