//Given a string containing digits from 2-9 inclusive, return all possible 
//letter combinations that the number could represent. Return the answer in any order. 
//
//
// A mapping of digits to letters (just like on the telephone buttons) is given 
//below. Note that 1 does not map to any letters. 
// 
// 
// Example 1: 
//
// 
//Input: digits = "23"
//Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// Example 2: 
//
// 
//Input: digits = ""
//Output: []
// 
//
// Example 3: 
//
// 
//Input: digits = "2"
//Output: ["a","b","c"]
// 
//
// 
// Constraints: 
//
// 
// 0 <= digits.length <= 4 
// digits[i] is a digit in the range ['2', '9']. 
// 
//
// Related Topics Hash Table String Backtracking 👍 19471 👎 1052

package com.kshitijpatil.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        List<String> comb = solution.letterCombinations("4");
        System.out.println(comb);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private String digits;
    private char[][] dial = {
            {},
            {},
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };
    List<String> combinations;
    public List<String> letterCombinations(String digits) {
        combinations = new ArrayList<>();
        this.digits = digits;
        f(0,new StringBuilder());
        return combinations;
    }

    private void f(int i, StringBuilder number) {
        if (i == digits.length()) {
            if (number.length() != 0) {
                combinations.add(number.toString());
            }
            return;
        }
        for (char ch: dial[digitAt(i)]) {
            f(i+1, number.append(ch));
            number.deleteCharAt(number.length()-1);
        }
    }

    private int digitAt(int index) {
        return digits.charAt(index) - '0';
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*
23

f(0,[])
    f(1,[a])
        f(2,[ad])
        f(2,[ae])
        f(2,[af])
    f(1,[b])
        f(2,[bd])
        f(2,[be])
        f(2,[bf])
    f(1,[c])
        f(2,[cd])
        f(2,[ce])
        f(2,[cf])

 */