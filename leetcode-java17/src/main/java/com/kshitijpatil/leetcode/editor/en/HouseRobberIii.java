//The thief has found himself a new place for his thievery again. There is only 
//one entrance to this area, called root. 
//
// Besides the root, each house has one and only one parent house. After a tour,
// the smart thief realized that all houses in this place form a binary tree. It 
//will automatically contact the police if two directly-linked houses were broken 
//into on the same night. 
//
// Given the root of the binary tree, return the maximum amount of money the 
//thief can rob without alerting the police. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,2,3,null,3,null,1]
//Output: 7
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
// 
//
// Example 2: 
// 
// 
//Input: root = [3,4,5,1,3,null,1]
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 10⁴]. 
// 0 <= Node.val <= 10⁴ 
// 
//
// Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 876
//5 👎 147

package com.kshitijpatil.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIii {
    private final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        Solution solution = new HouseRobberIii().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int[] ans = f(root);
        return Math.max(ans[0], ans[1]);
    }
    private int[] f(TreeNode node) {
        if (node == null) return new int[2];
        int[] left = f(node.left);
        int[] right = f(node.right);
        int robbingNode = node.val + left[NOT_ROBBING] + right[NOT_ROBBING];
        int notRobbingNode = Math.max(left[ROBBING], left[NOT_ROBBING]) + Math.max(right[ROBBING], right[NOT_ROBBING]);
        return new int[] { robbingNode, notRobbingNode };
    }
    private static final int ROBBING = 0;
    private static final int NOT_ROBBING = 1;
}
//leetcode submit region end(Prohibit modification and deletion)

}