//Given two strings s and t, return true if t is an anagram of s, and false 
//otherwise. 
//
// 
// Example 1: 
//
// 
// Input: s = "anagram", t = "nagaram" 
// 
//
// Output: true 
//
// Example 2: 
//
// 
// Input: s = "rat", t = "car" 
// 
//
// Output: false 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 5 * 10⁴ 
// s and t consist of lowercase English letters. 
// 
//
// 
// Follow up: What if the inputs contain Unicode characters? How would you 
//adapt your solution to such a case? 
//
// Related Topics Hash Table String Sorting 👍 12859 👎 425

package com.kshitijpatil.leetcode.editor.en;

public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        for (char sc : s.toCharArray()) {
            freq[sc-'a']++;
        }
        for (char tc : t.toCharArray()) {
            freq[tc-'a']--;
            if (freq[tc-'a'] < 0) return false;
        }

        for (int i=0; i<26; i++) {
            if (freq[i] != 0) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
anagramt

a   :   0
n   :   0
g   :   0
r   :   0
m   :   0
t   :   1

nagaramm


 */