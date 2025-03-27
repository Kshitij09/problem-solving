//Given a string s, find the length of the longest substring without duplicate 
//characters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s consists of English letters, digits, symbols and spaces. 
// 
//
// Related Topics Hash Table String Sliding Window 👍 41488 👎 2002

package com.kshitijpatil.leetcode.editor.en;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] windowContains = new boolean[127];
        int longest = 0;
        int start = 0, strlen=s.length();
        char[] schar = s.toCharArray();
        for (int end = 0; start <= end && end < strlen; end++) {
            if (windowContains[schar[end]]) {
                while (start < strlen && schar[start] != schar[end]) {
                    windowContains[schar[start]] = false;
                    start++;
                }
                start++;
            }
            longest = Math.max(longest, end - start + 1);
            windowContains[schar[end]] = true;
        }
        return longest;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/*
pwwkew
  e
 s
p=false
w=true

 */