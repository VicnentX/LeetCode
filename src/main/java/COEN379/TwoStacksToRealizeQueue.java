package COEN379;


import java.util.*;

public class TwoStacksToRealizeQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    private boolean isQueueEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    private int QueueSize() {
        return s1.size() + s2.size();
    }

    private void enQueue(int x) {
        s1.push(x);
        return;
    }

    //average time complexity is O(1) , worst case is O(n);
    private int deQueue() {
        if (QueueSize() == 0) {
            return Integer.MIN_VALUE;
        }
        if (s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    //average time complexity is O(1) , worst case is O(n);
    private int front() {
        if (QueueSize() == 0) {
            return Integer.MIN_VALUE;
        }
        if (s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public static void main (String[] args) {
        TwoStacksToRealizeQueue ts = new TwoStacksToRealizeQueue();
        ts.enQueue(1);
        ts.enQueue(2);
        System.out.println("is queue empty ? :  " + ts.isQueueEmpty());
        System.out.println("queue size is " + ts.QueueSize());
        ts.enQueue(3);
        System.out.println("dequeue element is " + ts.deQueue());
        System.out.println("queue size is " + ts.QueueSize());
        System.out.println("the first element is " + ts.front());
        System.out.println("queue size is " + ts.QueueSize());
    }
}
