//You are given two non-empty linked lists representing two non-negative 
//integers. The digits are stored in reverse order, and each of their nodes contains a 
//single digit. Add the two numbers and return the sum as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the 
//number 0 itself. 
//
// 
// Example 1: 
// 
// 
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
// 
//
// Example 2: 
//
// 
//Input: l1 = [0], l2 = [0]
//Output: [0]
// 
//
// Example 3: 
//
// 
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in each linked list is in the range [1, 100]. 
// 0 <= Node.val <= 9 
// It is guaranteed that the list represents a number that does not have 
//leading zeros. 
// 
//
// Related Topics Linked List Math Recursion 👍 33044 👎 6641

package com.kshitijpatil.leetcode.editor.en;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        ListNode first = ListNode.of(new int[]{9,9,9,9,9,9,9});
        ListNode second = ListNode.of(new int[]{9,9,9,9});
        System.out.println(solution.addTwoNumbers(first, second));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode tempHead = new ListNode(-1);
        ListNode temp = tempHead;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            int digit = sum % 10;
            temp.next = new ListNode(digit);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            int digit = sum % 10;
            temp.next = new ListNode(digit);
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            int digit = sum % 10;
            temp.next = new ListNode(digit);
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return tempHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}