package Mathworks;

import java.util.Stack;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 */

/*
Solution DP
Find the cost for the interval [i,j].
To build up the interval [i,j],
we need to split it into left subtree and sub tree,
dp[i, j] = dp[i, k] + dp[k + 1, j] + max(A[i, k]) * max(A[k + 1, j])

This solution is O(N^3) time and O(N^2) space. You think it's fine.
After several nested for loop, you get a green accepted and release a sigh.

Great practice for DP!
Then you think that's it for a medium problem, nothing can stop you.

so you didn't read and up-vote my post.
One day, you bring this solution to an interview and probably fail.


Intuition
When we build a node in the tree, we compared the two numbers a and b.
In this process,
the smaller one is removed and we won't use it anymore,
and the bigger one actually stays.

The problem can translated as following:
Given an array A, choose two neighbors in the array a and b,
we can remove the smaller one min(a,b) and the cost is a * b.
What is the minimum cost to remove the whole array until only one left?

To remove a number a, it needs a cost a * b, where b >= a.
So a has to be removed by a bigger number.
We want minimize this cost, so we need to minimize b.

b has two candidates, the first bigger number on the left,
the first bigger number on the right.

The cost to remove a is a * min(left, right).


Explanation
Now we know that, this is not a dp problem.
(Because dp solution test all ways to build up the tree, it's kinda of brute force)

With the intuition above in mind,
we decompose a hard problem into reasonable easy one:
Just find the next greater element in the array, on the left and one right.
Refer to 1019. Next Greater Node In Linked List


Complexity
Time O(N) for one pass
Space O(N) for stack in the worst cases
 */

public class MinimumCostTreeFromLeafValues1130 {
    public int mctFromLeafValues(int[] arr) {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);

        for (int num: arr) {
            while (stack.peek() < num) {
                int mid = stack.pop();
                ret += mid * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }

        while(stack.size() > 2) {
            ret += stack.pop() * stack.peek();
        }

        return ret;
    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues1130 minimumCostTreeFromLeafValues1130 = new MinimumCostTreeFromLeafValues1130();
        System.out.println(minimumCostTreeFromLeafValues1130.mctFromLeafValues(new int[] {6,2,4}));
    }
}
