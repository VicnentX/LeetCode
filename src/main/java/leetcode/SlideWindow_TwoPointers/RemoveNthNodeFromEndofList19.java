package leetcode.SlideWindow_TwoPointers;

/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

Seen this question in a real interview before?

 */

public class RemoveNthNodeFromEndofList19 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1) , slow = dummy , fast = dummy;
        dummy.next = head;
        for (int i = 0 ; i <= n ; ++i) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        //skip the node next to slow;
        slow.next = slow.next.next;

        return dummy.next;
    }
}
