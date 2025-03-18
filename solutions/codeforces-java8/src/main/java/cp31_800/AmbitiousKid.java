package cp31_800;

import java.io.*;

public class AmbitiousKid {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        io.readLine(); // ignore length
        String[] tokens = io.readLineAsStringArray();
        int minAbs = Integer.MAX_VALUE;
        for (String s : tokens) {
            int num = Integer.parseInt(s);
            minAbs = Math.min(minAbs, Math.abs(num));
        }
        io.writeLine(minAbs);
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

        void writeLine(String s) {
            writer.println(s);
        }

        void flushOutput() {
            writer.flush();
        }

        int[] readLineAsIntArray() {
            String[] tokens = readLineAsStringArray();
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            return nums;
        }

        String[] readLineAsStringArray() {
            String line = readLine();
            if (line == null) return new String[0];
            return line.split(" ");
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
