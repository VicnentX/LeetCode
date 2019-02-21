package leetcode.Iterator;

/*
Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.



Example:

Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // return 1
iterator.next(); // return 2
iterator.next(); // return 3
iterator.hasNext(); // return true
iterator.hasNext(); // return true
iterator.next(); // return 4
iterator.hasNext(); // return false


Notes:

Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d vector when next() is called.


Follow up:

As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */

import java.util.*;

public class Flatten2DVector251 {
    private Stack<int[]> stack;
    private int n;
    private int index;

    public Flatten2DVector251(int[][] v) {
        n = v.length;
        stack = new Stack<>();
        for (int i = n - 1 ; i >= 0 ; --i) {
            stack.push(v[i]);
        }
    }

    public int next() {
        return stack.peek()[index];
    }

    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (index < stack.peek().length) {
                System.out.println("changdu = " + stack.peek().length);
                return true;
            }
            stack.pop();
            index = 0;
        }
        return false;
    }

    public static void main (String[] args) {
        Flatten2DVector251 fv = new Flatten2DVector251(new int[][] {{1,1} , {2,2}});
        System.out.println(fv.hasNext());
        System.out.println(fv.next());
        System.out.println(fv.next());
        System.out.println(fv.next());
        System.out.println(fv.next());
    }
}
