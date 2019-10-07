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
    }
}
