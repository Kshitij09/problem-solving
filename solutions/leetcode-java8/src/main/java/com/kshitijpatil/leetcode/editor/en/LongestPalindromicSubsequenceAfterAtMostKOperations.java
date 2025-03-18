//You are given a string s and an integer k. 
//
// In one operation, you can replace the character at any position with the 
//next or previous letter in the alphabet (wrapping around so that 'a' is after 'z'). 
//For example, replacing 'a' with the next letter results in 'b', and replacing 
//'a' with the previous letter results in 'z'. Similarly, replacing 'z' with the 
//next letter results in 'a', and replacing 'z' with the previous letter results in 
//'y'. 
//
// Return the length of the longest palindromic subsequence of s that can be 
//obtained after performing at most k operations. 
//
// 
// Example 1: 
//
// 
// Input: s = "abced", k = 2 
// 
//
// Output: 3 
//
// Explanation: 
//
// 
// Replace s[1] with the next letter, and s becomes "acced". 
// Replace s[4] with the previous letter, and s becomes "accec". 
// 
//
// The subsequence "ccc" forms a palindrome of length 3, which is the maximum. 
//
// Example 2: 
//
// 
// Input: s = "aaazzz", k = 4 
// 
//
// Output: 6 
//
// Explanation: 
//
// 
// Replace s[0] with the previous letter, and s becomes "zaazzz". 
// Replace s[4] with the next letter, and s becomes "zaazaz". 
// Replace s[3] with the next letter, and s becomes "zaaaaz". 
// 
//
// The entire string forms a palindrome of length 6. 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 200 
// 1 <= k <= 200 
// s consists of only lowercase English letters. 
// 
//
// Related Topics String Dynamic Programming 👍 84 👎 12

package com.kshitijpatil.leetcode.editor.en;

import java.util.Arrays;

public class LongestPalindromicSubsequenceAfterAtMostKOperations {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequenceAfterAtMostKOperations().new Solution();
        System.out.println(solution.longestPalindromicSubsequence("abced", 2));
        System.out.println(solution.longestPalindromicSubsequence("aaazzz", 4));
        System.out.println(solution.longestPalindromicSubsequence("bt", 10));
        System.out.println(solution.longestPalindromicSubsequence("gd", 1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][][] dp;
    public int longestPalindromicSubsequence(String s, int maxK) {
        int n = s.length();
        dp = new int[n][n][maxK+1];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k <= maxK; k++) {
                dp[i][i][k] = 1;
            }
        }
        char[] str = s.toCharArray();
        for (int i=n-2; i>=0; i--) {
            for (int j=i+1; j<n; j++) {
                for (int k = 0; k <= maxK; k++) {
                    int maxWithSkipping = Math.max(dp[i+1][j][k], dp[i][j-1][k]);
                    int opsToMatch = minOperationsToMatch(str, i, j);
                    int maxWithMatching = 0;
                    if (opsToMatch <= k) {
                        maxWithMatching = 2 + dp[i+1][j-1][k-opsToMatch];
                    }
                    dp[i][j][k] = Math.max(maxWithMatching, maxWithSkipping);
                }
            }
        }
        return dp[0][s.length()-1][maxK];
    }

    private int f(int i, int j, int k, char[] s) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (dp[i][j][k] != -1) return dp[i][j][k];
        int maxWithSkipping = Math.max(f(i+1,j,k,s), f(i,j-1,k,s));
        int opsToMatch = minOperationsToMatch(s, i, j);
        int maxWithMatching = 0;
        if (opsToMatch <= k) {
            maxWithMatching = 2 + f(i+1, j-1, k-opsToMatch, s);
        }
        return dp[i][j][k] = Math.max(maxWithMatching, maxWithSkipping);
    }

    private int minOperationsToMatch(char[] s, int i, int j) {
        int forward = Math.abs(s[j] - s[i]);
        int backward = 26 - forward;
        return Math.min(forward, backward);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}