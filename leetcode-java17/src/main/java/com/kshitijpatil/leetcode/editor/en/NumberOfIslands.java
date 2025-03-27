//Given an m x n 2D binary grid grid which represents a map of '1's (land) and 
//'0's (water), return the number of islands. 
//
// An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all 
//surrounded by water. 
//
// 
// Example 1: 
//
// 
//Input: grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] is '0' or '1'. 
// 
//
// Related Topics Array Depth-First Search Breadth-First Search Union Find 
//Matrix 👍 23610 👎 558

package com.kshitijpatil.leetcode.editor.en;

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private char[][] grid;
    private boolean[][] visited;
    private final int[][] offsets = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int rows = grid.length, cols = grid[0].length;
        visited = new boolean[rows][cols];
        int islands = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (isUnvisitedLand(i,j)) {
                    islands++;
                    visit(i,j);
                }
            }
        }
        return islands;
    }

    private void visit(int i, int j) {
        visited[i][j] = true;
        for (final var offset : offsets) {
            int x=i+offset[0], y=j+offset[1];
            if (isUnvisitedLand(x,y)) visit(x,y);
        }
    }

    private boolean isUnvisitedLand(int i, int j) {
        if (i < 0 || i >= grid.length) return false;
        if (j < 0 || j >= grid[0].length) return false;
        return grid[i][j]=='1' && !visited[i][j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}