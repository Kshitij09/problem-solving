//Given an m x n binary matrix filled with 0's and 1's, find the largest square 
//containing only 1's and return its area. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 4
// 
//
// Example 2: 
// 
// 
//Input: matrix = [["0","1"],["1","0"]]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: matrix = [["0"]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] is '0' or '1'. 
// 
//
// Related Topics Array Dynamic Programming Matrix 👍 10530 👎 243

package com.kshitijpatil.leetcode.editor.en;

public class MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new MaximalSquare().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows=matrix.length, cols=matrix[0].length;
        int[][] dp = new int[rows+1][cols+1];
        int maxSqr = 0;
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    int minExpansion = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    dp[i][j] = minExpansion + 1;
                    maxSqr = Math.max(maxSqr, dp[i][j]);
                }
            }
        }
        return maxSqr * maxSqr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
["1","0","1","0","0"]
["1","0","1","1","1"]
["1","1","1","1","1"]
["1","0","0","1","0"]
 */