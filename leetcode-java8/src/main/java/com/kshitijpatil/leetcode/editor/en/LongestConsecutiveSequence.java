//Given an unsorted array of integers nums, return the length of the longest 
//consecutive elements sequence. 
//
// You must write an algorithm that runs in O(n) time. 
//
// 
// Example 1: 
//
// 
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
//Therefore its length is 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9
// 
//
// Example 3: 
//
// 
//Input: nums = [1,0,1,2]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics Array Hash Table Union Find 👍 21089 👎 1124

package com.kshitijpatil.leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new LongestConsecutiveSequence().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> uniqNums = new HashSet<>(nums.length);
        for (int num: nums) uniqNums.add(num);
        int maxSeq = 0, currSeq=0;
        for (int num: uniqNums) {
            if (uniqNums.contains(num-1)) continue;
            currSeq = 1;
            int last = num;
            while (uniqNums.contains(last+1)) {
                last = last+1;
                currSeq++;
            }
            maxSeq = Math.max(maxSeq, currSeq);
        }
        return maxSeq;
    }

    private Map<Integer, Integer> buildIndexLookup(int[] nums) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i=0; i<nums.length; i++) lookup.put(nums[i], i);
        return lookup;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}