package com.kshitijpatil.leetcode.editor.en;

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
// Related Topics String Stack 👍 25743 👎 1877

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private lateinit var st: CharArray
    private var top = -1

    fun isValid(s: String): Boolean {
        st = CharArray(s.length)
        for (c in s) {
            if (isStackEmpty() || c.isOpening()) {
                push(c)
                continue
            }
            val lastOpening = pop()
            val isMatching = when (lastOpening) {
                '[' -> c == ']'
                '(' -> c == ')'
                '{' -> c == '}'
                else -> false
            }
            if (!isMatching) return false
        }
        return isStackEmpty()
    }

    fun push(c: Char) {
        st[++top] = c
    }

    fun pop(): Char {
        if (top < 0) throw NoSuchElementException()
        return st[top--]
    }

    fun isStackEmpty(): Boolean {
        return top == -1
    }

    fun Char.isOpening(): Boolean {
        return this == '(' || this == '{' || this == '['
    }
}
//leetcode submit region end(Prohibit modification and deletion)


fun main() {
    val solution = Solution()
}