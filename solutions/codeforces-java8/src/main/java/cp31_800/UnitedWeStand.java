package cp31_800;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Queue;

public class UnitedWeStand {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            io.readLine(); // ignore length
            int[] a = io.readLineAsIntArray();
            boolean hasTwoPlusDistinct = false;
            int last = -1;
            Deque<Integer> bq = new ArrayDeque<>();
            Queue<Integer> cq = new ArrayDeque<>();
            for (int num: a) {
                if (bq.isEmpty() || bq.peek() > num) {
                    bq.offer(num);
                } else if (bq.peek() == num) {
                    bq.offerFirst(num);
                } else {
                    cq.offer(num);
                }
                if (last != -1 && num != last) {
                    hasTwoPlusDistinct = true;
                }
                last = num;
            }
            if (!hasTwoPlusDistinct) {
                io.writeLine(-1);
                continue;
            }
            if (cq.isEmpty()) {
                do {
                    cq.offer(bq.poll());
                } while (Objects.equals(bq.peek(), cq.peek()));
            }
            int j=0;
            int[] b = new int[bq.size()];
            while (!bq.isEmpty()) b[j++] = bq.poll();
            int[] c = new int[cq.size()];
            j=0;
            while (!cq.isEmpty()) c[j++] = cq.poll();
            io.writeIntsWithNewline(b.length, c.length);
            io.writeIntsWithNewline(b);
            io.writeIntsWithNewline(c);
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

        void writeIntsWithNewline(int... num) {
            for (int i=0; i<num.length; i++) {
                writer.print(num[i]);
                if (i<num.length-1) writer.print(" ");
            }
            writer.println();
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
2 2 2

b
c

1 2 3 4 5

b 1 2 3
c 4 5

1 3 5

b 1 3
c 5

1 7 7 2 9 1 4

b 1 2 1 4
c 7 7 9

4 8 12 12 4

b 4 4
c 8 12 12

5 4 3 2 1

b 4 3 2 1
c 5

5 5 5 4 4 3

b 4 4 3
c 5 5

5 5 5 5 4

b 4
c 5 5 5 5

1 2 3 4 5

b 1
c 2 3 4 5

2 1 2 1 2 7 3 8

b 2 1 2 1 2
c 7 3 8

5 4 6 7 2 1

b 5 4 2 1
c 6 7

1 2 3 4 5

b 1
c 2 3 4 5

2 1 3 4 5
b 2 1
c 3 4 5

1 1 1

b 1 1 1
c

 */
