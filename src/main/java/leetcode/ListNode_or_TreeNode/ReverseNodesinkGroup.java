package leetcode.ListNode_or_TreeNode;

/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */

/*
testHead is for calculate the mount of nodes;
newHead is used as head for each flip loop;
cur is used as pointer for cur node;
next is used to store the next node to be checked
tail is used to be the end of one group

the line of code "tail.next = cur" is to previously used to  void there is no flip in the next group
then
    tail could be newHead for the next group(if there exits next flip group)

there is flip
 */

import java.util.List;

public class ReverseNodesinkGroup {
    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null || head.next == null) return head;
        //find the amount of loop
        ListNode testHead = head;
        int cnt = 0;
        while (testHead != null) {
            ++cnt;
            testHead = testHead.next;
        }
        //times is the amount of the loop
        int times = cnt / k;
        //use a dummy to return ret;
        ListNode dummy = new ListNode (-1);
        //
        ListNode newHead = dummy;
        //this is to avoid there is no any flip loop;
        newHead.next = head;
        //cur means the current node , it should begin from head;
        ListNode cur = head;
        //ListNode cur = head;
        for (int i = 0 ; i < times ; ++i) {
            //pre is used to reverse group;
            ListNode pre = null;
            //j is used to count how many nodes we have flipped in one group
            int j = 0;
            //tail is the tail of reverse List in this loop
            ListNode tail = null;
            while (j < k) {
                if (j == 0) tail = cur;
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                ++j;
            }
            newHead.next = pre;
            tail.next = cur;
            newHead = tail;
        }
        return dummy.next;
    }
}
