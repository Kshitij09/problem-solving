package cp31_800;

import java.io.*;
import java.util.Arrays;

public class ForbiddenInteger {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i = 0; i < t; i++) {
            int[] vars = io.readLineAsIntArray();
            int n=vars[0], k=vars[1], x=vars[2];
            Result res = sumPossible(n, k, x);
            io.writeLine(res.possibility);
            if (res != Result.NO) {
                io.writeLine(res.nums.length);
                io.writeArray(res.nums);
            }
        }
        io.flushOutput();
    }
    private static Result sumPossible(int n, int k, int x) {
        boolean isEvenTarget = (n & 1) == 0;

        if (x != 1) {
            int[] nums = new int[n];
            Arrays.fill(nums, 1);
            return new Result(true, nums);
        }

        if (k == 1 || (k == 2 && !isEvenTarget)) {
            return Result.NO;
        }

        int len = n/2;
        int[] nums = new int[len];
        if ((n&1) == 1) nums[0] = 3;
        int i= isEvenTarget ? 0 : 1;
        for (; i<len; i++) {
            nums[i] = 2;
        }
        return new Result(true, nums);
    }
    private static final class Result {
        final String possibility;
        final int[] nums;
        private static final Result NO = new Result(false, new int[]{});
        Result(boolean sumPossible, int[] nums) {
            this.possibility = sumPossible ? "YES" : "NO";
            this.nums = nums;
        }
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

        void writeLine(String line) {
            writer.println(line);
        }

        void writeArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                writer.print(arr[i]);
                if (i<arr.length-1) writer.print(" ");
            }
            writer.println();
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
10 3 2
1 3

5 2 1 - n
2

4 2 1 - y
2

7 7 3 - y
1 2 4 5 6 7

6 1 1 -




if 1 is allowed -> yes
if k==n -> yes
if k==1 && x==1 -> no

 */
