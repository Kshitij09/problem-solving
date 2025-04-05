//A permutation of an array of integers is an arrangement of its members into a 
//sequence or linear order. 
//
// 
// For example, for arr = [1,2,3], the following are all the permutations of 
//arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]. 
// 
//
// The next permutation of an array of integers is the next lexicographically 
//greater permutation of its integer. More formally, if all the permutations of the 
//array are sorted in one container according to their lexicographical order, 
//then the next permutation of that array is the permutation that follows it in the 
//sorted container. If such arrangement is not possible, the array must be 
//rearranged as the lowest possible order (i.e., sorted in ascending order). 
//
// 
// For example, the next permutation of arr = [1,2,3] is [1,3,2]. 
// Similarly, the next permutation of arr = [2,3,1] is [3,1,2]. 
// While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does 
//not have a lexicographical larger rearrangement. 
// 
//
// Given an array of integers nums, find the next permutation of nums. 
//
// The replacement must be in place and use only constant extra memory. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3]
//Output: [1,3,2]
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1]
//Output: [1,2,3]
// 
//
// Example 3: 
//
// 
//Input: nums = [1,1,5]
//Output: [1,5,1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics Array Two Pointers 👍 19610 👎 4895

package com.kshitijpatil.leetcode.editor.en;

public class NextPermutation {

    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int pivot;
        pivot=n-2;
        while (pivot>=0 && nums[pivot] >= nums[pivot+1]) {
            pivot--;
        }
        if (pivot >= 0) {
            int dstIndex;
            dstIndex=n-1;
            while (dstIndex > pivot && nums[dstIndex] <= nums[pivot]) {
                dstIndex--;
            }
            swap(nums, pivot, dstIndex);
        }
        reverseAfter(nums, pivot);
    }

    private static void reverseAfter(int[] nums, int startIndex) {
        startIndex++;
        int endIndex = nums.length-1;
        while (startIndex < endIndex) {
            swap(nums, startIndex, endIndex);
            startIndex++;
            endIndex--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
1237654
1273654

1255432
1525552
1552552

10543
15043

123
132

115
151
511

12243
12423


123654
126345

163245

14365
14635


126354

100654
106054
106045


100543
103045

123543
124335


 */