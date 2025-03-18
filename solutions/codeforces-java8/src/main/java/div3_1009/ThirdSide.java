package div3_1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class ThirdSide {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsInt();
        for (int i=0; i<t; i++) {
            int n = reader.readLineAsInt();
            Queue<Integer> heap = new PriorityQueue<>(n);
            String[] tokens = reader.readLine().split(" ");
            for (int j=0; j<n; j++) {
                int num = Integer.parseInt(tokens[j]);
                heap.add(num);
            }
            while (heap.size() > 1) {
                int first = heap.poll();
                int second = heap.poll();
                heap.add(first + second - 1);
            }
            System.out.println(heap.poll());
        }
    }
    private static final class InputReader {
        private final BufferedReader reader;

        InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        int readLineAsInt() {
            return Integer.parseInt(readLine());
        }

        int[] readLineAsIntArray() {
            String[] tokens = readLine().split(" ");
            int[] arr = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }
            return arr;
        }

        String readLine() {
            try {
                return reader.readLine().trim();
            } catch (IOException e) {
                return "";
            }
        }
    }
}
/*

998 244 353

110..<597


1 2 3 4 5

2 3 4 5
4 4 5
7 5
11
 */
