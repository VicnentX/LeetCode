package Amazon.cv;

/*
reverse linked list
 */

public class reverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }
    public ListNode reverseList (ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
