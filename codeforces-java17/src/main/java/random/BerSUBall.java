package random;

import java.io.*;
import java.util.Arrays;

// https://codeforces.com/problemset/problem/489/B
public class BerSUBall {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        io.readLine();
        var boySkills = io.readLineAsIntArray();
        Arrays.sort(boySkills);
        io.readLine();
        var girlSkills = io.readLineAsIntArray();
        Arrays.sort(girlSkills);
        int i=0, j=0, pairs=0;
        while (i<boySkills.length && j<girlSkills.length) {
            if (Math.abs(boySkills[i] - girlSkills[j]) <= 1) {
                pairs++;
                i++;
                j++;
            } else if (boySkills[i] < girlSkills[j]) {
                i++;
            } else {
                j++;
            }
        }
        io.writeLine(pairs);
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

        void flushOutput() {
            writer.flush();
        }
    }
}
/*
1 2 2 2

3 4 5 6

2,3
3,4
4,5
*/