package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DaytonaCost {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        int t = reader.readLineAsInt();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int[] vars = reader.readLineAsIntArray();
            int n = vars[0], k = vars[1];
            int[] nums = reader.readLineAsIntArray();
            boolean exists = false;
            for (int num : nums) {
                if (num == k) {
                    exists = true;
                    break;
                }
            }
            ans.append(exists ? "YES" : "NO");
            ans.append("\n");
        }
        System.out.println(ans);
    }

    private static final class InputReader {
        private final BufferedReader reader;

        InputReader(InputStream i) {
            reader = new BufferedReader(new InputStreamReader(i));
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
1 4 3 4 1 (4)

2 3 4 4

43 5 60 4 2

1 5

5 3 3 1

3

3 4 1 5 5

 */
