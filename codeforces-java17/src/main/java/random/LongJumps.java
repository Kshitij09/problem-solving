package random;

import java.io.*;

// https://codeforces.com/problemset/problem/1472/C
public class LongJumps {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        while (t > 0) {
            int n = io.readLineAsInt();
            int[] arr = io.readLineAsIntArray();
            int[] score = new int[n];
            score[n-1] = arr[n-1];
            int maxScore = arr[n-1];
            for (int i=n-2; i>=0; i--) {
                int currScore = arr[i];
                int next = i+arr[i];
                if (next < n) currScore += score[next];
                score[i] = currScore;
                maxScore = Math.max(maxScore, currScore);
            }
            io.writeLine(maxScore);
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

        int[] readLineAsIntArray() {
            String line = readLine();
            if (line == null) return new int[0];
            String[] tokens = line.split(" ");
            int n = tokens.length;
            int[] arr = new int[n];
            for (int i=0; i<n; i++) arr[i] = Integer.parseInt(tokens[i]);
            return arr;
        }

        void writeLine(int x) {
            writer.println(x);
        }

        void writeWithSpace(int x) {
            writer.print(x);
            writer.print(' ');
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
/*
7 3 1 2 3
7 6 3 2 3

2 1 4
6 5 4

2 1000 2 3 995 1
  1000     995 1
      997
1 1 1 1 1
      2 1
 */
