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
// Related Topics Hash Table String Sliding Window 👍 41463 👎 2001

package com.kshitijpatil.leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        String[] testcases = {"dddd","abcda","aabdf","aghaytuaftyur","","aca"};
        for (String t : testcases) {
            System.out.println(solution.lengthOfLongestSubstring(t));
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        Set<Character> window = new HashSet<>();
        char[] str = s.toCharArray();
        int maxLen = 0;
        for (int end=0; end < s.length(); end++) {
            if (window.contains(str[end])) {
                while (start < str.length && str[start] != str[end]) {
                    window.remove(str[start]);
                    start++;
                }
                start++; // move past repeating character
            }
            maxLen = Math.max(maxLen, end - start + 1);
            window.add(str[end]);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
p w w k e w
    [
        ]
m=3

w,k,e



a a a a a
  [
  ]
m=1

a,


a b g a h s a t r s a
            [
                    ]
m=5

a,t,r,s
 */