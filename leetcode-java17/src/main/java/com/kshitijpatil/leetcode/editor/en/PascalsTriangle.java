//Given an integer numRows, return the first numRows of Pascal's triangle. 
//
// In Pascal's triangle, each number is the sum of the two numbers directly 
//above it as shown: 
// 
// 
// Example 1: 
// Input: numRows = 5
//Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
// Example 2: 
// Input: numRows = 1
//Output: [[1]]
// 
// 
// Constraints: 
//
// 
// 1 <= numRows <= 30 
// 
//
// Related Topics Array Dynamic Programming 👍 13632 👎 500

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>(numRows);
        for (int row=1; row<=numRows; row++) {
            rows.add(generateRow(row));
        }
        return rows;
    }

    private List<Integer> generateRow(int row) {
        List<Integer> items = new ArrayList<>();
        items.add(1);
        int ans = 1;
        for (int col=1; col<row; col++) {
            ans = ans * (row-col);
            ans = ans / col;
            items.add(ans);
        }
        return items;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}