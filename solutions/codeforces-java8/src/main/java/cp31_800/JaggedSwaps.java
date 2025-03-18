package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JaggedSwaps {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        StringBuilder ans = new StringBuilder();
        int t = reader.readLineAsInt();
        for (int i=0; i<t; i++) {
            reader.readLineAsInt();
            int[] perm = reader.readLineAsIntArray();
            ans.append(perm[0]==1 ? "YES" : "NO");
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

1 3 4 2 5 ==
1 2 3 4 5

5 2 4 3 1
5 2 3 4 1 x


1 3 2 5 4 x2
1 2 3 4 5

1 2 3 4 5 6 p=0,w=0 ~

1 3 2 4 5 6 p=1,w=1 ~

1 3 2 5 4 6 p=2,w=2
1 2 3 4 5 6 ~

1 4 2 6 5 3 p=2, w=3
1 2 4 6 5 3 p=2, w=3
1 2 4 5 6 3 p=2, w=3

1 5 2 3 4 6 p=2,w=2
1 2 3 4 5 6 p=2,w=2
 */
