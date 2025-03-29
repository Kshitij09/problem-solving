//You are given a sorted array consisting of only integers where every element 
//appears exactly twice, except for one element which appears exactly once. 
//
// Return the single element that appears only once. 
//
// Your solution must run in O(log n) time and O(1) space. 
//
// 
// Example 1: 
// Input: nums = [1,1,2,3,3,4,4,8,8]
//Output: 2
// 
// Example 2: 
// Input: nums = [3,3,7,7,10,11,11]
//Output: 10
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics Array Binary Search 👍 11882 👎 213

package com.kshitijpatil.leetcode.editor.en;

public class SingleElementInASortedArray {
    public static void main(String[] args) {
        Solution solution = new SingleElementInASortedArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}