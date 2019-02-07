package COEN379;

/*
Proof:
    this problem could be proved in TWO ways as below.
(1) For n elements , each element's whole life cycle includes 4 cost :
push into s1 , pop out of s1 , push into s2 , pop out of s2.
    There will be totally 2n operations (n push and n pop) when we enqueue n elements and dequeue n element.
    So the amortized cost of each enqueue and each dequeue is
        total cost / total ops
    =   (4 * n) / (2 * n)
    =   2
    (Q.E.D)

(2) There is FOUR situation to be discussed.
    (a) push when s1 is empty => the cost is 1;
    (b) push when s1 is not empty => the cost is 1;
    (c) pop when s2 is not empty => the cost is 1;
    (d) pop when s2 is empty =>
        we assume potential function Pt = 2*St ; (St means the amount of element in s1 at time i)
        C'i = Ci + delta(P)
            = [s * S(at time i - 1) + 1] + [2 * S(at time i) - 2 * S(at time i - 1)]
            = [s * S(at time i - 1) + 1] + [0 - 2 * S(at time i - 1)]
            = 1
    (Q.E.D)
 */


import java.util.*;

public class HW3_TwoStacksToRealizeQueue17_3_6 {
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

    //average time complexity is O(1)
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
    //average time complexity is O(1)
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
        HW3_TwoStacksToRealizeQueue17_3_6 ts = new HW3_TwoStacksToRealizeQueue17_3_6();
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
