package div2_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Perfecto {

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsIntOrZero();
        int[] testCases = new int[t];
        for (int i = 0; i < t; i++) {
            testCases[i] = reader.readLineAsIntOrZero();
        }
        for (int i = 0; i < t; i++) {
            perfecto(testCases[i]);
        }
    }

    private static void perfecto(int n) {
        if (!isValid(n)) {
            System.out.println(-1);
            return;
        }

        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) nums[i] = i;

        int j = 0;
        List<Integer> soln = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            while ((long) j * j < (long) i * (i + 1) / 2) j++;
            if ((long) j * j == (long) i * (i + 1) / 2 && i < n) {
                swap(nums, i, i + 1);
            }
            soln.add(nums[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<soln.size(); i++) {
            sb.append(soln.get(i));
            if (i != soln.size() - 1) sb.append(' ');
        }
        System.out.println(sb);
    }

    private static boolean isValid(int n) {
        long sum = (long) n * (n + 1) / 2;
        long sqrt = (long) Math.sqrt(sum);
        return sqrt * sqrt != sum;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static class InputReader {
        private final BufferedReader reader;
        InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        int readLineAsIntOrZero() {
            try {
                String line = reader.readLine().trim();
                return Integer.parseInt(line);
            } catch (IOException ignored) {
                return 0;
            }
        }
    }
}
