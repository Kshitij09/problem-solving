package random;

import java.util.*;

// https://codeforces.com/contest/520/problem/B
public class TwoButtons {
    private static final Map<Integer, Integer> memo = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int src = sc.nextInt(), dst = sc.nextInt();
        System.out.println(bfs(src, dst));
    }

    static int bfs(int src, int dst) {
        if (src >= dst) return src - dst;

        Map<Integer, Integer> steps = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        steps.put(src, 0);

        while (!q.isEmpty()) {
            int u = q.poll();
            int stepsSoFar = steps.get(u);

            // Generate possible next states
            int[] moves = {u - 1, u * 2};

            for (int v : moves) {
                if (v == dst) return stepsSoFar + 1;
                if (v < 0 || v > 2 * dst) continue; // Reasonable upper bound

                if (!steps.containsKey(v) || stepsSoFar + 1 < steps.get(v)) {
                    steps.put(v, stepsSoFar + 1);
                    q.add(v);
                }
            }
        }
        return -1; // Should never reach for valid inputs
    }
}