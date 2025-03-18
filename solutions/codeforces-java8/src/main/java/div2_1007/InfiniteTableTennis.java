package div2_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class InfiniteTableTennis {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsInt().orElse(0);
        int[] testCases = new int[t];
        for (int i = 0; i < t; i++) {
            testCases[i] = reader.readLineAsIntOrZero();
        }
        for (int i = 0; i < t; i++) {
            String ans = canSpectateAt(testCases[i]) ? "YES" : "NO";
            System.out.println(ans);
        }
    }

    private static boolean canSpectateAt(int k) {
        if ((k & 1) == 0) {
            return (k-4) % 6 == 0;
        } else {
            return (k / 2) % 3 == 0;
        }
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

        Optional<Integer> readLineAsInt() {
            return readLine().flatMap(this::tryParseInt);
        }

        private Optional<Integer> tryParseInt(String s) {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException ignored) {
                return Optional.empty();
            }
        }

        Optional<char[]> readLineAsChars() {
            return readLine().map(String::toCharArray);
        }

        Optional<String> readLine()  {
            try {
                return Optional.of(reader.readLine().trim());
            } catch (IOException ignored) {
                return Optional.empty();
            }
        }
    }
}
/**

a,b,c

 ab
    ca
        cb
            ba
                ac
                    cb
    cb
        ca
            ab
                bc
                    ac

 a - 1,2,4,5
 a - 1,3,4,6

 c - 2,3,5,6,8,9,11,12,14,15,17,18

 1,3,4,6,7,8

 5 -> 5/2 -> 2 = yes

 7 -> 7/2 -> 3 = no


 15 -> 15/2 -> 7 = yes
 17 -> 17/2 -> 8 = yes


 4 -> no -> 2
 6 -> yes
 8 -> yes
 10 -> no -> 5
 12 -> yes
 14 -> yes
 16 -> no -> 8
 18 -> yes
 20 -> yes
 22 -> no
 */