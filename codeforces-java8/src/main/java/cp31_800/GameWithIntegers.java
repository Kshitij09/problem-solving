package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GameWithIntegers {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        int t = reader.readLineAsInt();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<t; i++) {
            int n = reader.readLineAsInt();
            String winner = (n % 3 == 0) ? "Second" : "First";
            sb.append(winner);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static final class InputReader {
        private final BufferedReader reader;

        InputReader(InputStream i) {
            reader = new BufferedReader(new InputStreamReader(i));
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
     3   4
va  +1
vo      -1



 */
