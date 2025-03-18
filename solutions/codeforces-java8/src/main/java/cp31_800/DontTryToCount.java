package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DontTryToCount {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        StringBuilder ans = new StringBuilder();
        int t = reader.readLineAsInt();
        testcase: for (int i=0; i<t; i++) {
            reader.readLine(); // ignore lengths
            String x = reader.readLine();
            String s = reader.readLine();
            assert x != null: "x should not be null";
            assert s != null: "s should not be null";
            int count = 0;
            for (int j=0; j<=5; j++) {
                if (x.contains(s)) {
                    ans.append(count);
                    ans.append("\n");
                    continue testcase;
                }
                x = x + x;
                count++;
            }
            ans.append(-1);
            ans.append("\n");
        }
        System.out.println(ans);
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

1. x should be equal to s
    or should be left rotated version

1. x chars should be repeated fully or partially, in-order when len(x) < len(s)

a
aaaaa
a -> aa -> aaaa -> aaaaaaaa (3)


eforc
cefor
rcefo
force
eforc -> eforceforc (1)



ab
ababa
ab -> ababa



aba
ababa

aba -> abaabaabaaba

aba
abaabb


aba -> abaaba


babb
bbb


5 1
aaaaa
a
4 2
aabb
ba
2 8
bk
kbkbkbkb
12 2
fjdgmujlcont
tf
2 2
aa
aa
3 5
abb
babba
1 19
m
mmmmmmmmmmmmmmmmmmm

 */
