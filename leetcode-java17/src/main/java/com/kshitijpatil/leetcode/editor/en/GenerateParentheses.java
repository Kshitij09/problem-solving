//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// 
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics String Dynamic Programming Backtracking 👍 21950 👎 1019

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        solution.generateParenthesis(3);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<String> validCombinations;
    public List<String> generateParenthesis(int n) {
        validCombinations = new ArrayList<>();
        f(0,0,n, new MiniStringBuilder(n * 2));
        return validCombinations;
    }

    private void f(int open, int close, int n, MiniStringBuilder current) {
        if (open == n && close == n) {
            validCombinations.add(current.build());
            return;
        }
        if (open > n || close > open) return;
        current.append('(');
        f(open + 1, close, n, current);
        current.deleteLastChar();
        current.append(')');
        f(open, close + 1, n, current);
        current.deleteLastChar();
    }

    private static final class MiniStringBuilder {
        char[] buffer;
        int len=0;

        MiniStringBuilder(int capacity) {
            buffer = new char[capacity];
        }

        void append(char c) {
            if (len == buffer.length) return;
            buffer[len++] = c;
        }

        void deleteLastChar() {
            if (len>0) len--;
        }

        String build() {
            return new String(buffer);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*

f(0,0,3,"")
  f(1,0,3,"(")
    f(2,0,3."((")
      f(3,0,

(
  ((
    (((
      ((((x
      ((()
        ((()(x
        ((())
          ((())(x
          ((())) ==
    (()
      (()(
        (()()
          (()()(x
          (()()) ==
        (()()(x
      (())
        (())(
          (())() ==
          (())((x
        (()))x
  ()
    ()(
      ()()
        ()()(
          ()()((x
          ()()() ==
        ()())x
      ()((
        ()(((x
        ()(()
          ()(()(x
          ()(()) ==
    ())x
)x


 */