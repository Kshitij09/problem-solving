package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DoremyPaint3 {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        int t = reader.readLineAsInt();
        StringBuilder ans = new StringBuilder();
        for (int i=0; i<t; i++) {
            int n = reader.readLineAsInt();
            String line = reader.readLine();
            if (line == null) break;
            int num = 0;
            int first=-1, second=-1;
            int firstCount=0, secondCount=0;
            boolean hasTwoPlusDistincts = false;
            for (int j=0; j<line.length(); j++) {
                char c = line.charAt(j);
                if (c != ' ') {
                    int digit = c - '0';
                    num = num * 10 + digit;
                }
                if (c == ' ' || j == line.length() - 1) {
                    if (first == -1) {
                        first = num;
                        firstCount++;
                    } else if (second == -1 && num != first) {
                        second = num;
                        secondCount++;
                    } else if (num == first) {
                        firstCount++;
                    } else if (num == second) {
                        secondCount++;
                    } else {
                        hasTwoPlusDistincts = true;
                        break;
                    }
                    num = 0;
                }
            }
            String isGood = "Yes", isBad = "No";
            if (hasTwoPlusDistincts) {
                appendLine(ans, isBad);
            } else if (second == -1) {
                appendLine(ans, isGood);
            } else if (firstCount == secondCount) {
                appendLine(ans, isGood);
            } else if (firstCount == n/2 || secondCount == n/2) {
                appendLine(ans, isGood);
            } else {
                appendLine(ans, isBad);
            }
        }
        System.out.println(ans);
    }

    private static void appendLine(StringBuilder sb, String line) {
        sb.append(line);
        sb.append('\n');
    }

    private static final class InputReader {
        private final BufferedReader reader;

        InputReader(InputStream i) {
            reader = new BufferedReader(new InputStreamReader(i));
        }

        int[] readLineAsIntArray() {
            String[] tokens = readLine().split(" ");
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
8 9
1 1 2 -> 1 2 1
1 1 4 5 x
2 3 3 3 3 x
100000 100000 100000 100000
 */
