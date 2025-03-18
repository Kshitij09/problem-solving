import java.io.*;

final class IO {
    private final BufferedReader reader;
    private final PrintWriter writer;

    IO(InputStream i, OutputStream o) {
        reader = new BufferedReader(new InputStreamReader(i));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
    }

    void writeLine(int i) {
        writer.println(i);
    }

    void writeLine(String s) {
        writer.println(s);
    }

    void flushOutput() {
        writer.flush();
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
