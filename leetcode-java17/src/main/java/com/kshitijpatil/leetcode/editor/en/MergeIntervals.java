//Given an array of intervals where intervals[i] = [starti, endi], merge all 
//overlapping intervals, and return an array of the non-overlapping intervals that 
//cover all the intervals in the input. 
//
// 
// Example 1: 
//
// 
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
// 
//
// 
// Constraints: 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics Array Sorting 👍 23198 👎 840

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

public class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(iv -> iv[START]));
        Deque<int[]> st = new ArrayDeque<>();
        st.push(intervals[0]);
        for (int[] current : intervals) {
            if (st.peek()[END] >= current[START]) {
                int[] last = st.pop();
                int start = Math.min(last[START], current[START]);
                int end = Math.max(last[END], current[END]);
                st.push(new int[]{start,end});
            } else {
                st.push(current);
            }
        }
        int n = st.size();
        int[][] merged = new int[n][2];
        for (int i=n-1; i>=0; i--) {
            merged[i] = st.pop();
        }
        return merged;
    }

    private static final int START = 0;
    private static final int END = 1;
}
//leetcode submit region end(Prohibit modification and deletion)

}