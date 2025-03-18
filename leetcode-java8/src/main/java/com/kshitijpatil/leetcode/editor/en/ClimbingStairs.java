//You are climbing a staircase. It takes n steps to reach the top. 
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can 
//you climb to the top? 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
// 
//
// Example 2: 
//
// 
//Input: n = 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics Math Dynamic Programming Memoization 👍 22892 👎 938

package com.kshitijpatil.leetcode.editor.en;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        int[] testCases = { 3, 1, 5, 45 };
        for (int t : testCases) {
            System.out.println(solution.climbStairs(t));

        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        int lastWays=1,lastLastWays=1;
        for (int i = 2; i <= n; i++) {
            int current = lastWays + lastLastWays;
            lastLastWays = lastWays;
            lastWays = current;
        }
        return lastWays;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}