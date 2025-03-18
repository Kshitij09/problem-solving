//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[
//k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. 
//
// Notice that the solution set must not contain duplicate triplets. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
//Explanation: 
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//The distinct triplets are [-1,0,1] and [-1,-1,2].
//Notice that the order of the output and the order of the triplets does not 
//matter.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,1,1]
//Output: []
//Explanation: The only possible triplet does not sum up to 0.
// 
//
// Example 3: 
//
// 
//Input: nums = [0,0,0]
//Output: [[0,0,0]]
//Explanation: The only possible triplet sums up to 0.
// 
//
// 
// Constraints: 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics Array Two Pointers Sorting 👍 32459 👎 3054

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        List<List<Integer>> res = solution.threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i=0; i<n-2; i++) {
            if (i != 0) while (i<n-2 && nums[i] == nums[i-1]) i++;
            int j=i+1, k=n-1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    do j++;
                    while (j < n - 1 && nums[j] == nums[j - 1]);
                    do k--;
                    while (k > j && nums[k] == nums[k+1]);
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
-1 0 1 2 -1 -4

-4 -1 -1 0 1 2
       i j   k

-1 -1 2

 */