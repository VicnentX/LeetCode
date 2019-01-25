package leetcode.Sort;


/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */


public class SortList148 {
    public class ListNode {
        int val ;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if(head == null|| head.next == null) return head;
        ListNode slow = head , fast = head , firstHead = head , pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow;
        pre.next = null;

        ListNode left = sortList(firstHead);
        ListNode right = sortList(secondHead);

        return Merge(left , right);
    }

    private ListNode Merge (ListNode left , ListNode right) {
        ListNode dummy = new ListNode (-1) , head = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                head.next = left;
                left = left.next;
            } else {
                head.next = right;
                right = right.next;
            }
            head = head.next;
        }
        while (left != null) {
            head.next = left;
            left = left.next;
            head = head.next;
        }
        while (right != null) {
            head.next = right;
            right = right.next;
            head = head.next;
        }
        return dummy.next;
    }

}
