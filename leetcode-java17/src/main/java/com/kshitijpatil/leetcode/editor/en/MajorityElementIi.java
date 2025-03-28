//Given an integer array of size n, find all elements that appear more than ⌊ n/
//3 ⌋ times. 
//
// 
// Example 1: 
//
// 
//Input: nums = [3,2,3]
//Output: [3]
// 
//
// Example 2: 
//
// 
//Input: nums = [1]
//Output: [1]
// 
//
// Example 3: 
//
// 
//Input: nums = [1,2]
//Output: [1,2]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
// Follow up: Could you solve the problem in linear time and in O(1) space? 
//
// Related Topics Array Hash Table Sorting Counting 👍 10203 👎 461

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementIi {
    public static void main(String[] args) {
        Solution solution = new MajorityElementIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int firstCount=0, secondCount=0;
        Integer first=null, second=null;
        for (int num: nums) {
            if ((first == null || firstCount == 0) && (second == null || num != second)) {
                first = num;
                firstCount=1;
            } else if ((second == null || secondCount == 0) && num != first) {
                second = num;
                secondCount=1;
            } else if (num == first) {
                firstCount++;
            } else if (num == second) {
                secondCount++;
            } else {
                firstCount--;
                secondCount--;
            }
        }
        int verifiedFirstCount=0, verifiedSecondCount=0;
        for (int num: nums) {
            if (num == first) {
                verifiedFirstCount++;
            }
            if (second != null && num == second) {
                verifiedSecondCount++;
            }
        }
        int threshold = nums.length/3;
        List<Integer> answer = new ArrayList<>();
        if (verifiedFirstCount > threshold) {
           answer.add(first);
        }
        if (verifiedSecondCount > threshold) {
            answer.add(second);
        }

        if (answer.size() == 2 && answer.get(1) < answer.get(0)) {
            int tmp = answer.get(1);
            answer.set(1, answer.get(0));
            answer.set(0, tmp);
        }
        return answer;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/*
3 2 3
    i

fc=2
f=3
sc=1
s=2

 */