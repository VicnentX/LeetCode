package leetcode.ListNode_or_TreeNode;
/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 */

public class PartitionList86 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode fd = new ListNode (-1);
        ListNode ft = fd;
        ListNode sd = new ListNode (-1);
        ListNode st = sd;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                ft.next = cur;
                ft = ft.next;
            } else {
                st.next = cur;
                st = st.next;
            }
            cur = cur.next;
        }
        ft.next = sd.next;
        st.next = null;
        return fd.next;
    }
}
