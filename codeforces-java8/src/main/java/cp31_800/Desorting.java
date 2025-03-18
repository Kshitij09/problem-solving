package cp31_800;

import java.io.*;

public class Desorting {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i = 0; i < t; i++) {
            io.readLine(); // ignore length
            int[] nums = io.readLineAsIntArray();
            boolean isSorted = true;
            int minGap = Integer.MAX_VALUE;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] < nums[j-1]) {
                    isSorted = false;
                    break;
                }
                minGap = Math.min(minGap, nums[j]-nums[j-1]);
            }
            if (!isSorted) {
                io.writeLine(0);
            } else {
                int ans = (minGap / 2) + 1;
                io.writeLine(ans);
            }
        }
        io.flushOutput();
    }
    private static final class IO {
        private final BufferedReader reader;
        private final PrintWriter writer;
        IO(InputStream i, OutputStream o) {
            Reader r = new InputStreamReader(i);
            reader = new BufferedReader(r);
            Writer w = new OutputStreamWriter(o);
            w = new BufferedWriter(w);
            writer = new PrintWriter(w);
        }

        void writeLine(int i) {
            writer.println(i);
        }

        void flushOutput() {
            writer.flush();
        }

        private int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            return nums;
        }
        private int readLineAsInt() {
            String line = readLine();
            return (line == null) ? 0 : Integer.parseInt(line);
        }
        private String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }
    }
}
/*
2 0 (1)

1 10 8 13 (2)

1 3 2 (0)

1 12 11

6 5 31

8 6
 */
