package google.google2020.google_加油少年_ResidentVO;

/*
Two Linked Lists are identical
when they have same data and arrangement of data is also same.
For example Linked lists a (1->2->3) and b(1->2->3) are identical. .
Write a function to check if the given two linked lists are identical.
 */

import java.util.List;

public class IdenticalLinkedLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }

    }

    public boolean isIdentical(ListNode node1, ListNode node2) {
        while(node1 != null && node2 != null) {
            if (node1.val != node2.val) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1 == null && node2 == null;
    }
}
