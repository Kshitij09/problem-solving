package random;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

// https://codeforces.com/problemset/problem/1703/B
public class ICPCBalloons {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            Set<Character> solvedProblems = new HashSet<>();
            io.readLineAsInt();
            String timeline = io.readLine();
            if (timeline == null) throw new IllegalArgumentException("invalid input");
            int balloons = 0;
            for (char problem: timeline.toCharArray()) {
                if (solvedProblems.contains(problem)) {
                    balloons++;
                } else {
                    balloons += 2;
                }
                solvedProblems.add(problem);
            }
            io.writeLine(balloons);
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

        void writeLine(int x) {
            writer.println(x);
        }

        void writeLine(String s) {
            writer.println(s);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
