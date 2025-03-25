package random;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// https://codeforces.com/problemset/problem/519/B
public class ABCompilationErrors {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int n = io.readLineAsInt();
        Map<String, Integer> errorCount = new HashMap<>(n);
        String first = io.readLine();
        require(first != null);
        String second = io.readLine();
        require(second != null);
        String third = io.readLine();
        require(third != null);
        String fix = findFix(first, second, errorCount);
        io.writeLine(fix);
        errorCount.clear();
        fix = findFix(second, third, errorCount);
        io.writeLine(fix);
        io.flushOutput();
    }

    private static String findFix(String initialErrors, String errorsAfterFix, Map<String, Integer> errorCount) {
        String[] tokens = initialErrors.split(" ");
        for (String token : tokens) {
            errorCount.merge(token, 1, Integer::sum);
        }
        tokens = errorsAfterFix.split(" ");
        for (String token: tokens) {
            int newCount = errorCount.merge(token, -1, Integer::sum);
            if (newCount == 0) errorCount.remove(token);
        }
        var fix = errorCount.keySet().stream().findFirst();
        require(fix.isPresent());
        return fix.get();
    }

    private static void require(boolean predicate) {
        if (!predicate) throw new IllegalArgumentException("Invalid input");
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

        void writeLine(String s) {
            writer.println(s);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
/*
5
1 5 8 123 7
123 7 5 1
5 1 7

8   :   1

 */
