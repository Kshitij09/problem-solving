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
            int dest = vars[1];
            boolean[] isStation = new boolean[101];
            String[] tokens = reader.readLine().split(" ");
            for (String token : tokens) {
                int a = Integer.parseInt(token);
                isStation[a] = true;
            }
            System.out.println(minFuelTankFor(isStation, dest));
        }
    }

    private static int minFuelTankFor(boolean[] isStation, int dest) {
        int minCapacity = 1;
        int travelDistance = 0;
        for (int i=0; i<dest; i++) {
            if (isStation[i]) {
                minCapacity = Math.max(minCapacity, travelDistance);
                travelDistance=0;
            }
            travelDistance++;
        }
        for (int i=dest; i>0; i--) {
            if (isStation[i]) {
                minCapacity = Math.max(minCapacity, travelDistance);
                travelDistance=0;
            }
            travelDistance++;
        }
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
