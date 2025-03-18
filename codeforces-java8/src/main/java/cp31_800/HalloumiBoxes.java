package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HalloumiBoxes {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsInt();
        for (int i = 0; i < t; i++) {
            String[] line = reader.readLine().split(" ");
            assert line.length == 2: "invalid input";
            int k = Integer.parseInt(line[1]);
            line = reader.readLine().split(" ");
            int[] nums = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                nums[j] = Integer.parseInt(line[j]);
            }
            boolean isSortable = canSortWithFlips(nums, k);
            System.out.println(isSortable ? "YES" : "NO");
        }
    }
    private static class InputReader {
        private final BufferedReader reader;
        InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        int readLineAsInt() {
            return Integer.parseInt(readLine());
        }

        char[] readLineAsChars() throws IOException {
            return readLine().toCharArray();
        }

        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return "";
            }
        }
    }
    private static boolean canSortWithFlips(int[] nums, int k) {
        if (k > 1) return true;
        for (int i=0; i<nums.length-1; i++) {
            if (nums[i] > nums[i+1]) return false;
        }
        return true;
    }
}
/*

if k=1 return false

k=3

1 5 4 3 2
1 5 4 2 3
1 5 2 4 3
1 2 3 4 5


1 4 2 5 3
1 2 3 4 5

5 4 3 2 1
2 1 3 4 5

3 1 2 4

 */