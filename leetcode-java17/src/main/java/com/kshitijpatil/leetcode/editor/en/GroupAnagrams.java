//Given an array of strings strs, group the anagrams together. You can return 
//the answer in any order. 
//
// 
// Example 1: 
//
// 
// Input: strs = ["eat","tea","tan","ate","nat","bat"] 
// 
//
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// Explanation: 
//
// 
// There is no string in strs that can be rearranged to form "bat". 
// The strings "nat" and "tan" are anagrams as they can be rearranged to form 
//each other. 
// The strings "ate", "eat", and "tea" are anagrams as they can be rearranged 
//to form each other. 
// 
//
// Example 2: 
//
// 
// Input: strs = [""] 
// 
//
// Output: [[""]] 
//
// Example 3: 
//
// 
// Input: strs = ["a"] 
// 
//
// Output: [["a"]] 
//
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] consists of lowercase English letters. 
// 
//
// Related Topics Array Hash Table String Sorting 👍 20328 👎 677

package com.kshitijpatil.leetcode.editor.en;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> groups = new HashMap<>();
        for (final var str: strs) {
            int key = frequencyHash(str);
            final var members = groups.computeIfAbsent(key, (k) -> new ArrayList<>());
            members.add(str);
        }
        return new ArrayList<>(groups.values());
    }
    private int frequencyHash(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int hash = 0, mod = 1_000_000_007;
        for (int f : freq) {
            hash = ((hash * 31) % mod + f) % mod;
        }
        return hash;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}