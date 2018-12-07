package leetcode.ListNode_or_TreeNode;

public class MergeTwoSortedList21 {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public ListNode Merge(ListNode a , ListNode b){
        if(a == null) return b;
        if(b == null) return a;
        if(a.val < b.val){
            a.next = Merge(a.next , b);
            return a;
        }else{
            b.next = Merge(a , b.next);
            return b;
        }
    }
}
