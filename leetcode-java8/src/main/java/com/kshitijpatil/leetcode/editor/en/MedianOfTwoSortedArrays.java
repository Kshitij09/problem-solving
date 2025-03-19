//Given two sorted arrays nums1 and nums2 of size m and n respectively, return 
//the median of the two sorted arrays. 
//
// The overall run time complexity should be O(log (m+n)). 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
// 
//
// Example 2: 
//
// 
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
// 
//
// 
// Constraints: 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics Array Binary Search Divide and Conquer 👍 29693 👎 3338

package com.kshitijpatil.leetcode.editor.en;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int n = len1 + len2;
        int halfLen = (n+1)/2;
        int low = Math.max(0, halfLen-len2), high=Math.min(len1, halfLen);
        while (low <= high) {
            int mid1 = low + (high-low) / 2;
            int mid2 = halfLen - mid1;
            int l1 = (mid1 > 0) ? nums1[mid1-1] : Integer.MIN_VALUE;
            int r1 = (mid1 < len1) ? nums1[mid1] : Integer.MAX_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2-1] : Integer.MIN_VALUE;
            int r2 = (mid2 < len2) ? nums2[mid2] : Integer.MAX_VALUE;
            if (l1 <= r2 && l2 <= r1) {
                if ((n&1) == 0) {
                    return (Math.max(l1,l2) + Math.min(r1,r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = mid1-1;
            } else {
                low = mid1+1;
            }
        }
        throw new IllegalArgumentException(
                String.format(
                        "invalid input\nnum1:%s\nnum2:%s",
                        Arrays.toString(nums1),
                        Arrays.toString(nums2)
                )
        );
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/*

n = total length
len1 = nums1.length
len2 = nums2.length

low=max(0,n/2-len2), high=min(len1,n/2)

 1 2 5 8
  l1 r1
 3 4
  l2 r2|

1 2 3 4 = o(nlog(k))

4/2 = 2


5/2 = 2


1 2 3 4
        |
7 8 9 10 11 12

9/2

 */