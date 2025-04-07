//Given a m x n grid filled with non-negative numbers, find a path from top 
//left to bottom right, which minimizes the sum of all numbers along its path. 
//
// Note: You can only move either down or right at any point in time. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
// 
//
// Example 2: 
//
// 
//Input: grid = [[1,2,3],[4,5,6]]
//Output: 12
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 200 
// 
//
// Related Topics Array Dynamic Programming Matrix 👍 12937 👎 178

package com.kshitijpatil.leetcode.editor.en;

import java.util.Arrays;

public class MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[][] grid;
    private int rows,cols;
    private int[][] dp;

    public int minPathSum(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid.length==0 ? 0 : grid[0].length;
        dp = new int[rows][cols];
        for (int i=rows-1; i>=0; i--) {
            for (int j=cols-1; j>=0; j--) {
                if (i == rows-1 && j == cols-1) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                int pathSum = Integer.MAX_VALUE;
                if (canGo(i,j+1)) pathSum = Math.min(pathSum, dp[i][j+1]);
                if (canGo(i+1, j)) pathSum = Math.min(pathSum, dp[i+1][j]);
                dp[i][j] = grid[i][j] + pathSum;
            }
        }
        return dp[0][0];
    }

    private int f(int i, int j) {
        if (i == rows-1 && j == cols-1) return grid[i][j];
        if (dp[i][j] != -1) return dp[i][j];
        int pathSum = Integer.MAX_VALUE;
        if (canGo(i,j+1)) pathSum = Math.min(pathSum, f(i, j+1));
        if (canGo(i+1, j)) pathSum = Math.min(pathSum, f(i+1, j));
        dp[i][j] = grid[i][j] + pathSum;
        return dp[i][j];
    }
    private boolean canGo(int i, int j) {
        if (i < 0 || i >= rows) return false;
        if (j < 0 || j >= cols) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}