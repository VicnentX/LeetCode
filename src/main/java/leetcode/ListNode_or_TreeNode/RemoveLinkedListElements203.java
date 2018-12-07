package leetcode.ListNode_or_TreeNode;

/*
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
 */

public class RemoveLinkedListElements203 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode removeElement(ListNode head , int val){
        //remove the heading elelments whose value is val
        while(head != null && head.val == val){
            head = head.next;
        }
        ListNode node = head;
        //skip the elements whose value is val in the rest of list
        while(node != null && node.next != null){
            while(node.next != null && node.next.val == val){
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }
}
