//Given an array of integers heights representing the histogram's bar height 
//where the width of each bar is 1, return the area of the largest rectangle in the 
//histogram. 
//
// 
// Example 1: 
// 
// 
//Input: heights = [2,1,5,6,2,3]
//Output: 10
//Explanation: The above is a histogram where width of each bar is 1.
//The largest rectangle is shown in the red area, which has an area = 10 units.
// 
//
// Example 2: 
// 
// 
//Input: heights = [2,4]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= heights.length <= 10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
//
// Related Topics Array Stack Monotonic Stack 👍 18082 👎 319

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] nse = nextSmallerElements(heights);
        int[] pse = previousSmallerElements(heights);
        int n = heights.length;
        int maxArea = 0;
        for (int i=0; i<n; i++) {
            int height = heights[i];
            int width = (i-pse[i]) + (nse[i]-i) - 1;
            maxArea = Math.max(maxArea, width*height);
        }
        return maxArea;
    }

    int[] nextSmallerElements(int[] ht) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = ht.length;
        int[] nse = new int[n];
        for (int i=n-1; i>=0; i--) {
            while (!st.isEmpty() && ht[st.peek()] >= ht[i]) st.pop();
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nse;
    }

    int[] previousSmallerElements(int[] ht) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = ht.length;
        int[] pse = new int[n];
        for (int i=0; i<n; i++) {
            while (!st.isEmpty() && ht[st.peek()] >= ht[i]) st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}