//Given an array of integers nums and an integer k, return the total number of 
//subarrays whose sum equals to k. 
//
// A subarray is a contiguous non-empty sequence of elements within an array. 
//
// 
// Example 1: 
// Input: nums = [1,1,1], k = 2
//Output: 2
// 
// Example 2: 
// Input: nums = [1,2,3], k = 3
//Output: 2
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
//
// Related Topics Array Hash Table Prefix Sum 👍 22915 👎 739

package com.kshitijpatil.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count=0;
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        int runningSum=0;
        for (int num : nums) {
            runningSum += num;
            int extra = runningSum - k;
            if (prefix.containsKey(extra)) {
                count += prefix.get(extra);
            }
            prefix.merge(runningSum, 1, Integer::sum);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
1 3 2 2   | 4
1 4 6 8

1
 */