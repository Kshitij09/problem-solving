//Given an integer array nums and an integer k, return the k most frequent 
//elements. You may return the answer in any order. 
//
// 
// Example 1: 
// Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
// 
// Example 2: 
// Input: nums = [1], k = 1
//Output: [1]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// k is in the range [1, the number of unique elements in the array]. 
// It is guaranteed that the answer is unique. 
// 
//
// 
// Follow up: Your algorithm's time complexity must be better than O(n log n), 
//where n is the array's size. 
//
// Related Topics Array Hash Table Divide and Conquer Sorting Heap (Priority 
//Queue) Bucket Sort Counting Quickselect 👍 18146 👎 716

package com.kshitijpatil.leetcode.editor.en;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }
        Queue<Map.Entry<Integer, Integer>> minFreq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );
        for (var entry : freq.entrySet()) {
            minFreq.offer(entry);
            if (minFreq.size() > k) minFreq.poll();
        }
        int[] top = new int[k];
        for (int i=k-1; i>=0 && !minFreq.isEmpty(); i--) {
            top[i] = minFreq.poll().getKey();
        }
        return top;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*

1 1 1 2 2 3




* */