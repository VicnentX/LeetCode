package Amazon.full_time2020.final_round_加油少年_coding;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack155 {

    PriorityQueue<Integer> pq;
    Stack<Integer> stack;

    public MinStack155() {
        pq = new PriorityQueue<>();
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        pq.add(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int popElement = stack.pop();
            if (pq.peek() == popElement) {
                pq.poll();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return Integer.MIN_VALUE;
    }

    public int getMin() {
        if (!pq.isEmpty()) {
            return pq.peek();
        }
        return Integer.MIN_VALUE;
    }
}
