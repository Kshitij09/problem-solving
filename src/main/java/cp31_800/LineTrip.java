package cp31_800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineTrip {

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int t = reader.readLineAsInt();
        for (int i=0; i<t; i++) {
            int[] vars = reader.readLineAsIntArray();
            int n = vars[0];
            int x = vars[1];
            int prev=0, ans=0;
            String[] line = reader.readLine().split(" ");
            for (int j=0; j<n; j++) {
                int a = Integer.parseInt(line[j]);
                ans = Math.max(ans, a - prev);
                prev = a;
            }
            ans = Math.max(ans, 2*(x-prev));
            System.out.println(ans);
        }
    }

    private static int minFuelTankFor(int[] station, int dest) {
        int minCapacity = station[0];
        for (int i=1; i<station.length-1; i++) {
            minCapacity = Math.max(minCapacity, station[i+1]-station[i]);
        }
        int last = station.length-1;
        minCapacity = Math.max(minCapacity, 2 * (dest-station[last]));
        return minCapacity;
    }

    private static final class InputReader {
       private BufferedReader reader;

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
3 7
1 2 5


0 1 2 3 4 5 6 7 8 9 10
              x
-
c=7
d=7

*/
