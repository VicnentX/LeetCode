package leetcode.ListNode_or_TreeNode;

public class ReorderList143 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public void reorderList(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return ;
        }
        //方法一 ： 这种方法我是用stack来做，时间复杂度是好的 但是用了stack就很花时间
        // ListNode node = head;
        // Stack<ListNode> stack = new Stack<>();
        // while(node != null){
        //     stack.push(node);
        //     node = node.next;
        // }
        // node = head;
        // while(node != stack.peek()){
        //     ListNode tem = node.next;
        //     node.next = stack.pop();
        //     if(node.next.next == tem) break;
        //     node.next.next = tem;
        //     node = tem;
        // }
        // node.next = null;
        // return ;

        //第二种方法：
        //three steps to do : split merge and merge
        //1
        if(head == null || head.next == null){
            return ;
        }
        ListNode slow = head , fast = head ;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //now slow is the end of first list .
        ListNode secondHead = slow.next;
        //set null after the end of the first list;
        slow.next = null;
        //now reverse the second list
        secondHead = reverse(secondHead);
        //right now I have two lists
        ListNode firstHead = head;
        while(secondHead != null){
            ListNode node1 = firstHead.next;
            ListNode node2 = secondHead.next;
            firstHead.next = secondHead;
            secondHead.next = node1;
            firstHead = node1;
            secondHead = node2;
        }
    }
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode pre = head , cur = head.next;
        while(cur != null){
            ListNode tem = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tem;
        }
        head.next = null;
        return pre;
    }
}
