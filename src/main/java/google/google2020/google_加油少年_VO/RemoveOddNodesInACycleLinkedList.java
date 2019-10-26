package google.google2020.google_加油少年_VO;

/*

1. Remove odd nodes in a cyclic linked list
Given a cyclic linked list, remove all the odd nodes.
Assume there is no duplicate value,
and the last one "1" means pointing back to the first node

Ex.
1 -> 3 -> 6 -> 7 -> 2 -> 9 -> 1
return 1 -> 6 -> 2 -> 1

 */

import java.util.HashSet;
import java.util.Set;

public class RemoveOddNodesInACycleLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode removeOdd(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;

        while (cur != null) {
            set.add(cur.val);
            if (set.contains(cur.next.next.val)) break;
            cur.next = cur.next.next;
            cur = cur.next;
        }

        return dummy.next;
    }

}
