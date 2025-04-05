//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']
//', determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// Every close bracket has a corresponding open bracket of the same type. 
// 
//
// 
// Example 1: 
//
// 
// Input: s = "()" 
// 
//
// Output: true 
//
// Example 2: 
//
// 
// Input: s = "()[]{}" 
// 
//
// Output: true 
//
// Example 3: 
//
// 
// Input: s = "(]" 
// 
//
// Output: false 
//
// Example 4: 
//
// 
// Input: s = "([])" 
// 
//
// Output: true 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of parentheses only '()[]{}'. 
// 
//
// Related Topics String Stack 👍 25483 👎 1866

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        solution.isValid("()");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            switch (c) {
                case '{', '(', '[':
                    st.push(c);
                    break;
                case '}':
                    if (st.isEmpty() || st.pop() != '{') return false;
                    break;
                case ')':
                    if (st.isEmpty() || st.pop() != '(') return false;
                    break;
                case ']':
                    if (st.isEmpty() || st.pop() != '[') return false;
                    break;
            }
        }

        return st.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}