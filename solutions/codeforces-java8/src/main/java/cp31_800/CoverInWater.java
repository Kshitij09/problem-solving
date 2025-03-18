package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CoverInWater {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        int t = reader.readLineAsInt();
        StringBuilder ans = new StringBuilder();
        for (int i=0; i<t; i++) {
            reader.readLineAsInt();
            String cells = reader.readLine();
            if (cells == null) break;
            ans.append(minEmptyFills(cells));
            ans.append("\n");
        }
        System.out.println(ans);
    }

    private static int minEmptyFills(String cells) {
        int ans = 0;
        int emptyInBlock = 0;
        boolean hasInfiniteSource = false;
        for (char c : cells.toCharArray()) {
            if (c == '#') {
                ans += Math.min(emptyInBlock, 2);
                emptyInBlock = 0;
            } else if (c == '.') {
                emptyInBlock++;
                if (emptyInBlock == 3) {
                    hasInfiniteSource = true;
                    break;
                }
            }
        }
        if (hasInfiniteSource) return 2;
        ans += Math.min(emptyInBlock, 2);
        return ans;
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
##....#
##__._#
8 -> 5
7 -> 4
6 -> 4
5 -> 3
4 -> 3
3 -> 2
2 -> 2
1 -> 1


# _ = = = _ . . #

#....##.#.....#...##
  3

 */
