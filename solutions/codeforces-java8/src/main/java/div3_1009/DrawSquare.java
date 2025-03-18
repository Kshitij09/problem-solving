package div3_1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DrawSquare {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsInt();
        for (int i = 0; i < t; i++) {
            int[] p = reader.readLineAsIntArray();
            /*
            *       u
            *
            *   l       r
            *
            *       d
            * */
            int l=p[0], r=p[1], d=p[2], u=p[3];
            boolean isSqr = l == r && r == d && d == u;
            System.out.println(isSqr ? "YES" : "NO");
        }
    }
    private static final class InputReader {
        private final BufferedReader reader;

        InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        int readLineAsInt() {
            return Integer.parseInt(readLine());
        }

        int[] readLineAsIntArray() {
            String[] tokens = readLine().split(" ");
            int[] arr = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            return arr;
        }

        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return "";
            }
        }
    }
}
