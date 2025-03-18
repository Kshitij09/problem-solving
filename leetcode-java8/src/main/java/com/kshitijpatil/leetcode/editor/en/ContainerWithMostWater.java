//You are given an integer array height of length n. There are n vertical lines 
//drawn such that the two endpoints of the iᵗʰ line are (i, 0) and (i, height[i]).
// 
//
// Find two lines that together with the x-axis form a container, such that the 
//container contains the most water. 
//
// Return the maximum amount of water a container can store. 
//
// Notice that you may not slant the container. 
//
// 
// Example 1: 
// 
// 
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,
//3,7]. In this case, the max area of water (blue section) the container can 
//contain is 49.
// 
//
// Example 2: 
//
// 
//Input: height = [1,1]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related Topics Array Two Pointers Greedy 👍 30810 👎 1950

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        int i=0, j=height.length-1;
        int maxArea = 0;
        while (i<j) {
            int currArea = (j-i) * (Math.min(height[i], height[j]));
            maxArea = Math.max(maxArea, currArea);
            if (height[i] == height[j]) {
                i++;
                j--;
            } else if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*

8 2 100 8

 */