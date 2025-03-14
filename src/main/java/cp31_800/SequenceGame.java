package cp31_800;

import java.io.*;

public class SequenceGame {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            io.readLine(); // ignore length
            int[] b = io.readLineAsIntArray();
            int insertions = 0;
            for (int j=1; j<b.length; j++) {
                if (b[j] < b[j-1]) insertions++;
            }
            int alen = b.length+insertions;
            io.writeLine(alen);
            int[] a = new int[alen];
            int k = 0;
            a[k++] = b[0];
            for (int j=1; j<b.length; j++) {
                if (b[j] < b[j-1]) {
                    a[k++] = b[j];
                }
                a[k++] = b[j];
            }
            io.writeArray(a);
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
/*
1 2 2 1 1

1 2 2 1 1 1
 */