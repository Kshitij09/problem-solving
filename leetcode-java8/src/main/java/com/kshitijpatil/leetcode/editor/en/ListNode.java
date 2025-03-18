package com.kshitijpatil.leetcode.editor.en;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public static ListNode of(int[] arr) {
          if (arr == null || arr.length == 0) return null;
          ListNode head = new ListNode(arr[0]);
          ListNode cur = head;
          for (int i = 1; i < arr.length; i++) {
                ListNode next = new ListNode(arr[i]);
                cur.next = next;
                cur = cur.next;
          }
          return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode temp = this;
        sb.append('[');
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) sb.append(",");
            temp = temp.next;
        }
        sb.append(']');
        return sb.toString();
    }
}