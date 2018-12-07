package leetcode.ListNode_or_TreeNode;


public class LinkedListCycle141 {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }

    public boolean findCycle(ListNode head){
        ListNode a = head;
        ListNode b = head;
        while(a != null && a.next != null){
            a = a.next.next;
            b = b.next;
            if(a != b) return true;
        }
        return false;
    }
}
