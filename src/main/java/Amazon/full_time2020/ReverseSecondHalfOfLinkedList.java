package Amazon.full_time2020;

/**
 * 注意 ： 偶数的话中间那个算后面的
 */


class ListNode {
    int val;
    ListNode next;
    ListNode (int x) {
        val = x;
        next = null;
    }
}

public class ReverseSecondHalfOfLinkedList {

    public ListNode reverseSecondHalfList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode firstTail = null;
        while (fast.next != null && fast.next.next != null) {
            firstTail = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = null;
        while(slow != null) {
            ListNode next = slow.next;
            slow.next = temp;
            temp = slow;
            slow = next;
        }

        //link firstTail to secondHead(firstTail)
        firstTail.next = temp;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseSecondHalfOfLinkedList reverseSecondHalfOfLinkedList = new ReverseSecondHalfOfLinkedList();
        ListNode ret = reverseSecondHalfOfLinkedList.reverseSecondHalfList(head);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
    }
}
