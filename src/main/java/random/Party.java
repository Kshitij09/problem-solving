package random;

import java.io.*;
import java.util.*;

// https://codeforces.com/problemset/problem/115/A
public class Party {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int n = io.readLineAsInt();
        Map<Integer, List<Integer>> managerReportees = new HashMap<>();
        List<Integer> chiefs = new ArrayList<>(n);
        for (int i=1; i<=n; i++) {
            int manager = io.readLineAsInt();
            if (manager == -1) {
                chiefs.add(i);
                continue;
            }
            List<Integer> reportees = managerReportees.computeIfAbsent(manager, (k) -> new ArrayList<>());
            reportees.add(i);
        }
        int groups = 0;
        for (int cheif : chiefs) {
            groups = Math.max(groups, farthestReportee(cheif, managerReportees) + 1);
        }
        io.writeLine(groups);
        io.flushOutput();
    }
    
    private static int farthestReportee(int manager, Map<Integer, List<Integer>> managerReportees)  {
        int max = 0;
        List<Integer> reportees = managerReportees.get(manager);
        if (reportees == null || reportees.isEmpty()) return max;
        for (int reportee: managerReportees.get(manager)) {
            max = Math.max(max, 1 + farthestReportee(reportee, managerReportees));
        }
        return max;
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
