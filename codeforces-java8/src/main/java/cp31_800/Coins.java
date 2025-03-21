package cp31_800;

import java.io.*;

public class Coins {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            String line = io.readLine();
            if (line == null) throw new IllegalArgumentException("invalid input");
            String[] tokens = line.split(" ");
            if (tokens.length != 2) throw new IllegalArgumentException("invalid input");
            long n = Long.parseLong(tokens[0]), k=Long.parseLong(tokens[1]);
            if (k == 1 || k == n) {
                io.writeLine(YES);
                continue;
            }
            if ((n&1) == 0 || (k&1)==1) {
                io.writeLine(YES);
            } else {
                io.writeLine(NO);
            }
        }
        io.flushOutput();
    }

    private static final String YES = "YES", NO = "NO";

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
        void writeLine(String s) {
            writer.println(s);
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
5 3
3 + 2

6 1


7 4
8 8


9 6
11 9

if k is even and x is odd return no

if k is odd and x is odd



7 3

 */
