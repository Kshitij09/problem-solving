//Given an array nums of size n, return the majority element. 
//
// The majority element is the element that appears more than ⌊n / 2⌋ times. 
//You may assume that the majority element always exists in the array. 
//
// 
// Example 1: 
// Input: nums = [3,2,3]
//Output: 3
// 
// Example 2: 
// Input: nums = [2,2,1,1,1,2,2]
//Output: 2
// 
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//Follow-up: Could you solve the problem in linear time and in 
//O(1) space?
//
// Related Topics Array Hash Table Divide and Conquer Sorting Counting 👍 20639 
//👎 718

package com.kshitijpatil.leetcode.editor.en;

public class MajorityElement {
    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        int element=-1, count=0;
        for (int num : nums) {
            if (count == 0) {
                element = num;
                count++;
            } else if (num == element) {
                count++;
            } else {
                count--;
            }
        }
        int verifiedCount = 0;
        for (int num : nums) {
            if (element == num) verifiedCount++;
        }
        return (verifiedCount > nums.length/2) ? element : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}