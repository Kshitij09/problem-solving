//You are given an array of points in the X-Y plane points where points[i] = [
//xi, yi]. 
//
// Return the minimum area of a rectangle formed from these points, with sides 
//parallel to the X and Y axes. If there is not any such rectangle, return 0. 
//
// 
// Example 1: 
// 
// 
//Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
//Output: 4
// 
//
// Example 2: 
// 
// 
//Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// 1 <= points.length <= 500 
// points[i].length == 2 
// 0 <= xi, yi <= 4 * 10⁴ 
// All the given points are unique. 
// 
//
// Related Topics Array Hash Table Math Geometry Sorting 👍 2050 👎 292

package com.kshitijpatil.leetcode.editor.en;

import java.util.*;

public class MinimumAreaRectangle {
    public static void main(String[] args) {
        Solution solution = new MinimumAreaRectangle().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> x2y = new HashMap<>();
        for (int[] point : points) {
            var ys = x2y.computeIfAbsent(point[X], (k) -> new HashSet<>());
            ys.add(point[Y]);
        }
        var x2yEntries = x2y.entrySet().stream().toList();
        int minArea = Integer.MAX_VALUE;
        for (int i=0; i<x2yEntries.size(); i++) {
            var first = x2yEntries.get(i);
            var fx = first.getKey(); var fys = first.getValue();
            for (int j=i+1; j<x2yEntries.size(); j++) {
                var second = x2yEntries.get(j);
                var sx = second.getKey(); var sys = second.getValue();
                if (fys.size() < 2 || sys.size() < 2) continue;
                List<Integer> cys;
                if (fys.size() <= sys.size()) {
                    cys = new ArrayList<>(fys);
                    cys.retainAll(sys);
                } else {
                    cys = new ArrayList<>(sys);
                    cys.retainAll(fys);
                }

                Collections.sort(cys);
                for (int k=1; k<cys.size(); k++) {
                    int area = Math.abs(fx - sx) * (cys.get(k) - cys.get(k-1));
                    minArea = Math.min(minArea, area);
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

    private static final int X = 0;
    private static final int Y = 1;
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
[1,1]
[1,3]
[3,1],
[3,3],
  [2,2]

vertical line on x1
vertical line on x3

 */