package cp31_800;

import java.io.*;

public class ArrayColoring {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            io.readLine(); // ignore length
            int[] nums = io.readLineAsIntArray();
            int odds=0;
            for (int num: nums) {
                if ((num & 1) == 1) {
                    odds++;
                }
            }
            String yes = "Yes", no = "No";
            if ((odds & 1) == 0) {
                io.writeLine(yes);
            } else {
                io.writeLine(no);
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

        private int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int[] nums = new int[tokens.length];
            for (int i=0; i<tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            return nums;
        }

        private int readLineAsInt() {
            String line = readLine();
            return line == null ? 0 : Integer.parseInt(line);
        }

        private String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        private void writeLine(String line) {
            writer.println(line);
        }

        private void flushOutput() {
            writer.flush();
        }
    }
}
/*
1 2 4 3 2 3 5 4

4 7

3 9 8

1 7

5 4 3 2 1

4 3 4 5

50 48
e=2
o=0

50 49 48 no
o=1
e=2

50 49 48 47 yes
o=2
e=2

1 2 4 6 3 5

o=3
e=3


1 3 5 4 2 6
9
4
2
6
 */
