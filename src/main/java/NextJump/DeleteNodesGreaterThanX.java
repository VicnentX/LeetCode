package NextJump;

/*
given a singly linked list, remove nodes greater than X
 */

public class DeleteNodesGreaterThanX {

    public class ListNode {
        ListNode next;
        int val;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode deleteGreaterNode(ListNode head, int x) {
        ListNode newHead = new ListNode(-1);
        ListNode newPointer = newHead;

        while (head != null) {
            while(head != null && head.val > x) {
                head = head.next;
            }
            newPointer.next = head;
        }

        return newHead.next;
    }

}
