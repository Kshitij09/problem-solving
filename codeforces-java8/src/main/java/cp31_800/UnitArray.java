package cp31_800;

import java.io.*;

public class UnitArray {

    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            io.readLine();
            String line = io.readLine();
            if (line == null) throw new IllegalArgumentException("invalid input");
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int positive=0, negative=0;
            for (String token : tokens) {
                int num = Integer.parseInt(token);
                if (num < 0) {
                    negative++;
                } else {
                    positive++;
                }
            }
            if (negative <= positive && (negative&1) == 0) {
                io.writeLine(0);
                continue;
            }
            int ops = 0;
            if (negative > positive) {
                int offset = (negative - positive + 1) / 2;
                ops += offset;
                negative -= offset;
                positive += offset;
            }
            if ((negative&1) == 1) {
                ops++;
            }
            /*
            3 0 -> 3/2 = 2
            4 0 -> 4/2 = 2
            5 0 -> 5/2 = 3

             */
            io.writeLine(ops);
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

        void writeLine(int x) {
            writer.println(x);
        }

        void writeLine(int... nums) {
            for (int i=0; i<nums.length; i++) {
                writer.print(nums[i]);
                if (i<nums.length-1) writer.print(' ');
            }
            writer.println();
        }

        void flushOutput() {
            writer.flush();
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
    }
}
/*
4
-1 -1 1 -1
5
-1 -1 -1 1 1
4
-1 1 -1 1
3
-1 -1 -1
5
1 1 1 1 1
1
-1
2
-1 -1

#minus <= #plus
#minus % 2 == 0

4 3 -> 2
5 2 -> 3


if (#minus <= #plus && #minus is even) return 0
ops=0
if (#minus is odd) {
    ops++
    minus--
    plus++
}
ops += Math.max(0, minus-plus)

 */