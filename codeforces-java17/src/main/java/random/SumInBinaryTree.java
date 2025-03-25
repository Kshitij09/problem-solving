package random;

import java.io.*;

// https://codeforces.com/problemset/problem/1843/C
public class SumInBinaryTree {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        while (t > 0) {
            long num = io.readLineAsLong()-1;
            long sum = 0;
            while (num>0) {
                sum += (num+1);
                num = (num-1)/2;
            }
            io.writeLine(sum+1);
            t--;
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

        long readLineAsLong() {
            String line = readLine();
            return (line == null) ? 0 : Long.parseLong(line);
        }

        void writeLine(long x) {
            writer.println(x);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
