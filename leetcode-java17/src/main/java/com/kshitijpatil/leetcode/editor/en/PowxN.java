//Implement pow(x, n), which calculates x raised to the power n (i.e., xⁿ). 
//
// 
// Example 1: 
//
// 
//Input: x = 2.00000, n = 10
//Output: 1024.00000
// 
//
// Example 2: 
//
// 
//Input: x = 2.10000, n = 3
//Output: 9.26100
// 
//
// Example 3: 
//
// 
//Input: x = 2.00000, n = -2
//Output: 0.25000
//Explanation: 2⁻² = 1/2² = 1/4 = 0.25
// 
//
// 
// Constraints: 
//
// 
// -100.0 < x < 100.0 
// -2³¹ <= n <= 2³¹-1 
// n is an integer. 
// Either x is not zero or n > 0. 
// -10⁴ <= xⁿ <= 10⁴ 
// 
//
// Related Topics Math Recursion 👍 10558 👎 10080

package com.kshitijpatil.leetcode.editor.en;

public class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
        System.out.println(solution.myPow(4.0, -2));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        // if n is INT_MIN, converting it to +ve
        // would cause int overflow
        long N = n;
        if (n < 0) {
            x = 1/x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double ans = fastPow(x*x, n/2);
        if ((n&1)==1) ans *= x;
        return ((n<0) ? 1/ans : ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
f(2,10)
  f(4,5)
    4 * 256 * 256


* */