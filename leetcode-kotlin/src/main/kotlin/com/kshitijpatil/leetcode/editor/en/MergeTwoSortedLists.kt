package com.kshitijpatil.leetcode.editor.en;

//You are given the heads of two sorted linked lists list1 and list2. 
//
// Merge the two lists into one sorted list. The list should be made by 
//splicing together the nodes of the first two lists. 
//
// Return the head of the merged linked list. 
//
// 
// Example 1: 
// 
// 
//Input: list1 = [1,2,4], list2 = [1,3,4]
//Output: [1,1,2,3,4,4]
// 
//
// Example 2: 
//
// 
//Input: list1 = [], list2 = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: list1 = [], list2 = [0]
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in both lists is in the range [0, 50]. 
// -100 <= Node.val <= 100 
// Both list1 and list2 are sorted in non-decreasing order. 
// 
//
// Related Topics Linked List Recursion 👍 23289 👎 2293

class MergeTwoSortedLists {
//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    class Solution {
        fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            val merged = ListNode(-1)
            var current = merged;
            var l1 = list1;
            var l2 = list2
            while (l1 != null && l2 != null) {
                if (l1.`val` <= l2.`val`) {
                    current.next = l1
                    current = current.next!!
                    l1 = l1.next
                } else {
                    current.next = l2
                    current = current.next!!
                    l2 = l2.next
                }
            }

            while (l1 != null) {
                current.next = l1
                current = current.next!!
                l1 = l1.next
            }

            while (l2 != null) {
                current.next = l2
                current = current.next!!
                l2 = l2.next
            }

            return merged.next
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {
    val solution = MergeTwoSortedLists.Solution()
}