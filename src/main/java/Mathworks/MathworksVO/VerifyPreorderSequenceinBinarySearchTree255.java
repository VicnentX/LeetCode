package Mathworks.MathworksVO;

/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree:

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?
 */


/**
 * clarify :
 *  preorder mean root, left, right
 *  so we can understand that put smaller one first and then put larger one after against cur node.
 */
/**
 * Kinda simulate the traversal,
 * keeping a stack of nodes (just their values) of which we're still in the left subtree.
 * If the next number is smaller than the last stack value,
 * then we're still in the left subtree of all stack nodes,
 * so just push the new one onto the stack. But before that,
 * pop all smaller ancestor values,
 * as we must now be in their right subtrees
 * (or even further, in the right subtree of an ancestor).
 * Also, use the popped values as a lower bound,
 * since being in their right subtree means we must never come across a smaller number anymore.
 */


import java.util.Stack;

public class VerifyPreorderSequenceinBinarySearchTree255 {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack<>();

        for (int p: preorder) {
            if (p < low) return false;
            while(!path.empty() && p > path.peek()) {
                low = path.pop();
            }
            path.push(p);
        }

        return true;
    }


    public boolean verifyPreordefdr(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int nums: preorder) {
            if (nums < low) return false;
            while (!stack.isEmpty() && nums > stack.peek()) {
                low = stack.pop();
            }
            stack.push(nums);
        }

        return true;
    }

    public static void main(String[] args) {
        VerifyPreorderSequenceinBinarySearchTree255 verifyPreorderSequenceinBinarySearchTree255
                = new VerifyPreorderSequenceinBinarySearchTree255();
        //false;
        System.out.println(verifyPreorderSequenceinBinarySearchTree255.verifyPreorder(
                new int[] {5,2,6,1,3}
        ));
        //true
        System.out.println(verifyPreorderSequenceinBinarySearchTree255.verifyPreorder(
                new int[] {5,2,1,3,6}
        ));
        //true
        System.out.println(verifyPreorderSequenceinBinarySearchTree255.verifyPreorder(
                new int[] {5}
        ));
        //true
        System.out.println(verifyPreorderSequenceinBinarySearchTree255.verifyPreorder(
                new int[] {5,6,7,8}
        ));
    }
}
