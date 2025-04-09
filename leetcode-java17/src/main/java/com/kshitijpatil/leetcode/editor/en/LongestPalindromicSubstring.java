//Given a string s, return the longest palindromic substring in s. 
//
// 
// Example 1: 
//
// 
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: "bb"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consist of only digits and English letters. 
// 
//
// Related Topics Two Pointers String Dynamic Programming 👍 30562 👎 1887

package com.kshitijpatil.leetcode.editor.en;

import java.util.Arrays;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        var ans = solution.longestPalindrome("baabd");
        System.out.println(ans);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private String input;
    public String longestPalindrome(String s) {
        int maxStart=0, maxEnd=0;
        this.input = s;
        int maxLen = 0;
        // odd palindromes
        for (int i=0; i<s.length(); i++) {
            int expansion = expand(i,i) - 1;
            if (2*expansion+1 > maxLen) {
                maxLen = 2*expansion+1;
                maxStart = i-expansion;
                maxEnd = i+expansion;
            }
        }

        // even palindromes
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                int expansion = expand(i-1, i);
                if (2*expansion > maxLen) {
                    maxLen = 2*expansion;
                    maxStart = i-expansion;
                    maxEnd = i+expansion-1;
                }
            }
        }
        return s.substring(maxStart, maxEnd+1);
    }

    private int expand(int i, int j) {
        int length = 0;
        while (i>=0 && j<input.length()) {
            if (input.charAt(i) != input.charAt(j)) break;
            length++;
            i--;
            j++;
        }
        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/*

a b c c b d

d b c c b a

 */