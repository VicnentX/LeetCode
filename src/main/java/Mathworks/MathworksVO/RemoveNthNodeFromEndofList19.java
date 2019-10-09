package Mathworks.MathworksVO;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 *
 * Seen this question in a real interview before?
 */


/**
 * clarify:
 *
 * is n is always valid against the length of this linkedList?
 */

public class RemoveNthNodeFromEndofList19 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    //one pass method
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode slow = newHead, fast = newHead;

        //move fast in front so that gap between slow and fast = n
        for (int i = 1; i <= n + 1; ++i) {
            fast = fast.next;
        }

        //move fast to end, and see where slow is
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        //skip the node next to slow now
        slow.next = slow.next.next;
        return newHead.next;
    }
}
