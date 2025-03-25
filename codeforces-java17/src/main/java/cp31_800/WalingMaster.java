package cp31_800;

import java.io.*;

public class WalingMaster {
    public static void main(String[] args) {
        final var io = new IO(System.in, System.out);
        int t = io.readLineAsInt();
        for (int i=0; i<t; i++) {
            int[] arr = io.readLineAsIntArray();
            if (arr.length != 4) throw new IllegalArgumentException("invalid input");
            int x1=arr[0], y1=arr[1], x2=arr[2], y2=arr[3];
            if (y1==y2) {
                if (x1 >= x2) {
                    io.writeLine(x1-x2);
                } else {
                    io.writeLine(-1);
                }
            } else if (y1>y2) {
                io.writeLine(-1);
            } else {
                int dist = (y2-y1)-(x2-x1);
                if (dist < 0) {
                    io.writeLine(-1);
                } else {
                    io.writeLine(dist + (y2-y1));
                }
            }
        }
        io.flushOutput();
    }

    private static final class IO {
        private final BufferedReader reader;
        private final PrintWriter writer;

        IO(InputStream i, OutputStream o) {
            reader = new BufferedReader(new InputStreamReader(i));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
        }

        int[] readLineAsIntArray() {
            final var line = readLine();
            if (line == null) return new int[0];
            final var tokens = line.split(" ");
            final var arr = new int[tokens.length];
            for (int i=0; i<tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            return arr;
        }

        int readLineAsInt() {
            final var line = readLine();
            return (line == null) ? 0 :Integer.parseInt(line);
        }

        void writeLine(int x) {
            writer.println(x);
        }

        void flushOutput() {
            writer.flush();
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
6
-1 0 -1 2 -> Y
0 0 4 5 -> Y
-2 -1 1 1 -> N
-3 2 -3 2
2 -1 -1 -1
1 1 0 2

                      |
                      |
                      |
                      |
                      o
                      |x
- - - - - - - - - - - |- - - - - - - - - - -
                      |
                      |
                      |
                      |
                      |
                      |

y2-y1-(x2-x1)


if y2 == y1 && x1 >= x2 return x1-x2
if (y2-y1)-(x2-x1) >= 0 return p + y2-y1
else return -1
 */
