//Given a signed 32-bit integer x, return x with its digits reversed. If 
//reversing x causes the value to go outside the signed 32-bit integer range [-2³¹, 2³¹ -
// 1], then return 0. 
//
// Assume the environment does not allow you to store 64-bit integers (signed 
//or unsigned). 
//
// 
// Example 1: 
//
// 
//Input: x = 123
//Output: 321
// 
//
// Example 2: 
//
// 
//Input: x = -123
//Output: -321
// 
//
// Example 3: 
//
// 
//Input: x = 120
//Output: 21
// 
//
// 
// Constraints: 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
//
// Related Topics Math 👍 13904 👎 13735

package com.kshitijpatil.leetcode.editor.en;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
//        System.out.println(solution.reverse(-213));
//        System.out.println(solution.reverse(Integer.MIN_VALUE));
//        System.out.println(solution.reverse(Integer.MAX_VALUE));
//        System.out.println(solution.reverse((int)1e9));
//        System.out.println(solution.reverse(1000));
//        System.out.println(solution.reverse(0));
//        System.out.println(solution.reverse(1));
        System.out.println(solution.reverse(1534236469));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int rev = 0;
        int overflow = Integer.MAX_VALUE / 10;
        while (x > 0) {
            int nextDigit = x % 10;
            if (rev > overflow) return 0;
            if (rev == overflow && nextDigit > 7) return 0;
            rev = rev * 10 + nextDigit;
            x = x / 10;
        }
        return rev * sign;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
123
321

2147483647

7463847412

964632435
1056389758
 */