//You are a professional robber planning to rob houses along a street. Each 
//house has a certain amount of money stashed. All houses at this place are arranged 
//in a circle. That means the first house is the neighbor of the last one. 
//Meanwhile, adjacent houses have a security system connected, and it will automatically 
//contact the police if two adjacent houses were broken into on the same night. 
//
// Given an integer array nums representing the amount of money of each house, 
//return the maximum amount of money you can rob tonight without alerting the 
//police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 
//2), because they are adjacent houses.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 3: 
//
// 
//Input: nums = [1,2,3]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
//
// Related Topics Array Dynamic Programming 👍 10380 👎 170

package com.kshitijpatil.leetcode.editor.en;

import java.util.Arrays;

public class HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[] nums;
    private int n;

    public int rob(int[] nums) {
        this.nums = nums;
        n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int withFirstHouse = solveInRange(0, n-2);
        int withLastHouse = solveInRange(1,n-1);
        return Math.max(withFirstHouse, withLastHouse);
    }

    private int solveInRange(int start, int end) {
        int[] dp = new int[n];
        dp[end] = nums[end];
        for (int i=end-1; i>=start; i--) {
            int notTaking = dp[i+1];
            int taking = nums[i];
            if (i+2 <= n-1) taking += dp[i+2];
            dp[i] = Math.max(taking, notTaking);
        }
        return dp[start];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}