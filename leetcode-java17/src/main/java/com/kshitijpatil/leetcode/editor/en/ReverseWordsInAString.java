//Given an input string s, reverse the order of the words. 
//
// A word is defined as a sequence of non-space characters. The words in s will 
//be separated by at least one space. 
//
// Return a string of the words in reverse order concatenated by a single space.
// 
//
// Note that s may contain leading or trailing spaces or multiple spaces 
//between two words. The returned string should only have a single space separating the 
//words. Do not include any extra spaces. 
//
// 
// Example 1: 
//
// 
//Input: s = "the sky is blue"
//Output: "blue is sky the"
// 
//
// Example 2: 
//
// 
//Input: s = "  hello world  "
//Output: "world hello"
//Explanation: Your reversed string should not contain leading or trailing 
//spaces.
// 
//
// Example 3: 
//
// 
//Input: s = "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single 
//space in the reversed string.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s contains English letters (upper-case and lower-case), digits, and spaces ' 
//'. 
// There is at least one word in s. 
// 
//
// 
// Follow-up: If the string data type is mutable in your language, can you 
//solve it in-place with O(1) extra space? 
//
// Related Topics Two Pointers String 👍 9265 👎 5331

package com.kshitijpatil.leetcode.editor.en;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAString().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    char[] answer;
    int front;
    public String reverseWords(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        answer = new char[n];
        front = n-1;
        int start = 0, end = 0;
        while (end < n) {
            while (start < n && sc[start] == ' ') start++;
            end=start;
            while (end < n && sc[end] != ' ') end++;
            addToLast(sc, start, end-1);
            start = end;
        }
        if (front < 0) front = 0;
        while (front<n && isBlank(answer[front])) front++;
        return new String(answer, front, n-front);
    }

    private boolean isBlank(char c) {
        return c == ' ' || c == '\u0000';
    }

    private void addToLast(char[] source, int start, int end) {
        for (int i=end; front >= 0 && i>=start; i--) {
            answer[front--] = source[i];
        }
        if (front > 0) {
            answer[front--] = ' ';
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}