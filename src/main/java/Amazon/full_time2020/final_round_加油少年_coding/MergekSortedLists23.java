package Amazon.full_time2020.final_round_加油少年_coding;

/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */

import java.util.PriorityQueue;

public class MergekSortedLists23 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode head = new ListNode(-1);
        ListNode cur = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        //initalize pq
        for (ListNode node: lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        //
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }

        return head.next;
    }

}
