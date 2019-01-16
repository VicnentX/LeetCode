package leetcode.SlideWindow_TwoPointers;

/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
 */

public class PalindromeLinkedList234 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head , slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        //reverse 2th part
        slow = reverse(slow);

        //fast as the head of 1th part
        fast = head;

        while (slow != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse (ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode nextNode = node.next;
            node.next = pre;
            pre = node;
            node = nextNode;
        }
        return pre;
    }
}
