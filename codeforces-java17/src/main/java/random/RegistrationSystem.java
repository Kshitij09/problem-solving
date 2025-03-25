package random;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// https://codeforces.com/problemset/problem/4/C
public class RegistrationSystem {
    public static void main(String[] args) {
        IO io = new IO(System.in, System.out);
        Lookup lookup = new Lookup();
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            String name = io.readLine();
            if (name == null) throw new IllegalArgumentException("invalid input");
            String result = lookup.addOrGetSuggestion(name);
            if (result.equals(name)) {
                io.writeLine("OK");
            } else {
                io.writeLine(result);
            }
        }
        io.flushOutput();
    }
    private static final class Lookup {
        private final Map<String, Integer> db = new HashMap<>();

        String addOrGetSuggestion(String name) {
            int count = db.merge(name, 1, Integer::sum);
            if (count == 1) return name;
            return name + (count-1);
        }
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

        void writeLine(String s) {
            writer.println(s);
        }

        void flushOutput() {
            writer.flush();
        }
    }
}
