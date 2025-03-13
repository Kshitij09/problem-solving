package cp31_800;

import java.io.*;

public class GoalsOfVictory {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        StringBuilder ans = new StringBuilder();
        int t = reader.readLineAsInt();
        for (int i = 0; i < t; i++) {
            reader.readLineAsInt();
            String line = reader.readLine();
            if (line == null) continue;
            String[] tokens = line.split(" ");
            int sum = 0;
            for (String s : tokens) {
                int num = Integer.parseInt(s);
                sum += num;
            }
            ans.append(-sum);
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

3 -4 5



 */
