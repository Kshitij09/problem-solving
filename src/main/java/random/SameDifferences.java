package random;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// https://codeforces.com/problemset/problem/1520/D
public class SameDifferences {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        while (t > 0) {
            int n = io.readLineAsInt();
            int[] nums = io.readLineAsIntArray();
            Map<Integer, Integer> offsets = new HashMap<>();
            long pairs = 0;
            for (int i=0; i<n; i++) {
                int currOffset = nums[i] - i;
                int matchingOffsets = offsets.getOrDefault(currOffset, 0);
                pairs += matchingOffsets;
                offsets.put(currOffset, matchingOffsets+1);
            }
            io.writeLine(pairs);
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

        int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int[] nums = new int[n];
            for (int i=0; i<n; i++) nums[i] = Integer.parseInt(tokens[i]);
            return nums;
        }

        int readLineAsInt() {
            String line = readLine();
            return (line == null) ? 0 : Integer.parseInt(line);
        }
        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return null;
            }
        }

        void writeLine(long x) {
            writer.println(x);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}