//Given a string s, find the longest palindromic subsequence's length in s. 
//
// A subsequence is a sequence that can be derived from another sequence by 
//deleting some or no elements without changing the order of the remaining elements. 
//
// 
// Example 1: 
//
// 
//Input: s = "bbbab"
//Output: 4
//Explanation: One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: 2
//Explanation: One possible longest palindromic subsequence is "bb".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists only of lowercase English letters. 
// 
//
// Related Topics String Dynamic Programming 👍 9836 👎 332

package com.kshitijpatil.leetcode.editor.en;

public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] str = s.toCharArray();
        char[] rstr = reverse(str);
        return lcs(str, rstr);
    }

    private char[] reverse(char[] str) {
        char[] rev = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            rev[i] = str[str.length - 1 - i];
        }
        return rev;
    }

    private int lcs(char[] first, char[] second) {
        int[][] dp = new int[first.length + 1][second.length + 1];
        for (int i=1; i <= first.length; i++) {
            for (int j=1; j <= second.length; j++) {
                if (first[i - 1] == second[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[first.length][second.length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}