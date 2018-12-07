package leetcode.ListNode_or_TreeNode;

public class ReverseLinkedList206 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public ListNode reverse(ListNode head){
        ListNode tem = null;
        while(head != null){
            ListNode next = head.next;
            head.next = tem;
            tem = head;
            head = next;
        }
        return tem;
    }
}
