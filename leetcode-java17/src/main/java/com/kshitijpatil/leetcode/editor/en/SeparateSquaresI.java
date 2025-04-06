//You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] 
//represents the coordinates of the bottom-left point and the side length of a square 
//parallel to the x-axis. 
//
// Find the minimum y-coordinate value of a horizontal line such that the total 
//area of the squares above the line equals the total area of the squares below 
//the line. 
//
// Answers within 10⁻⁵ of the actual answer will be accepted. 
//
// Note: Squares may overlap. Overlapping areas should be counted multiple 
//times. 
//
// 
// Example 1: 
//
// 
// Input: squares = [[0,0,1],[2,2,1]] 
// 
//
// Output: 1.00000 
//
// Explanation: 
//
// 
//
// Any horizontal line between y = 1 and y = 2 will have 1 square unit above it 
//and 1 square unit below it. The lowest option is 1. 
//
// Example 2: 
//
// 
// Input: squares = [[0,0,2],[1,1,1]] 
// 
//
// Output: 1.16667 
//
// Explanation: 
//
// 
//
// The areas are: 
//
// 
// Below the line: 7/6 * 2 (Red) + 1/6 (Blue) = 15/6 = 2.5. 
// Above the line: 5/6 * 2 (Red) + 5/6 (Blue) = 15/6 = 2.5. 
// 
//
// Since the areas above and below the line are equal, the output is 7/6 = 1.166
//67. 
//
// 
// Constraints: 
//
// 
// 1 <= squares.length <= 5 * 10⁴ 
// squares[i] = [xi, yi, li] 
// squares[i].length == 3 
// 0 <= xi, yi <= 10⁹ 
// 1 <= li <= 10⁹ 
// The total area of all the squares will not exceed 10¹². 
// 
//
// Related Topics Array Binary Search 👍 124 👎 27

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SeparateSquaresI {
    public static void main(String[] args) {
        Solution solution = new SeparateSquaresI().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // ref: https://leetcode.com/problems/separate-squares-i/solutions/6431054/no-binary-search-one-pass-line-sweep-clean-fully-explained/
    public double separateSquares(int[][] squares) {
        int count = squares.length;
        List<SquareTransition> transitions = new ArrayList<>(count * 2);
        double totalArea = 0.0, halfArea;
        for (int[] sqr : squares) {
            transitions.add(new SquareTransition(sqr[Y], sqr[SIDE], true)); // entry
            transitions.add(new SquareTransition(sqr[Y]+sqr[SIDE], sqr[SIDE], false)); // exit
            totalArea += (1.0 * sqr[SIDE] * sqr[SIDE]);
        }
        halfArea = totalArea / 2.0;
        double lastY = 0.0, combinedWidth = 0.0, currArea = 0.0;
        transitions.sort(Comparator.comparingInt(SquareTransition::y));
        for (final var transition : transitions) {
            var heightDiff = transition.y - lastY;
            var areaDiff = combinedWidth * heightDiff;
            if (currArea + areaDiff >= halfArea) {
                var optimalHeightDiff = (halfArea - currArea) / combinedWidth;
                return lastY + optimalHeightDiff;
            }
            // update for next calculations
            if (transition.isEntry) {
                combinedWidth += transition.side;
            } else {
                combinedWidth -= transition.side;
            }
            currArea += areaDiff;
            lastY = transition.y;
        }
        return lastY;
    }
    private record SquareTransition(int y, int side, boolean isEntry) {}
    private static final int X = 0;
    private static final int Y = 1;
    private static final int SIDE = 2;
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*



            _____

            ______

        _____       ___
                    ___
        _____       ______
    ___
    ___             ______

 */