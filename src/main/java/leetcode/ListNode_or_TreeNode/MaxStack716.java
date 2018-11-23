package leetcode.ListNode_or_TreeNode;

/*
716. Max Stack
DescriptionHintsSubmissionsDiscussSolution
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */

public class MaxStack716 {

    class Node{
        int val;
        int max;
        Node pre;
        Node next;
        public Node(int val , int max){
            this.val = val;
            this.max = max;
        }
    }

    Node head;
    Node tail;

    /** initialize your data structure here. */
    public MaxStack716(){
        head = new Node(Integer.MAX_VALUE , Integer.MAX_VALUE);
        tail = new Node(Integer.MIN_VALUE , Integer.MIN_VALUE);
        head.next = tail;
        tail.pre = head;
    }

    public void push(int x) {
        int max = Math.max(x , head.next.max);
        Node node = new Node(x , max);
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
    }

    public int pop() {
        Node cur = head.next;
        head.next = cur.next;
        cur.next.pre = head;
        return cur.val;
    }

    public int top() {
        return head.next.val;
    }

    public int peekMax() {
        return head.next.max;
    }

    public int popMax() {
        int max = head.next.max;
        Node cur = head.next;
        while(cur.val != max){
            cur = cur.next;
        }
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        Node update = cur.pre;
        while(update != head){
            update.max = Math.max(update.val , update.next.max);
            update = update.pre;
        }
        return max;//这边不能写head.next.max
    }
}
