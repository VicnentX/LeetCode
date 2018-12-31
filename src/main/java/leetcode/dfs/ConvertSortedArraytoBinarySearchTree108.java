package leetcode.dfs;

/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */

/**
 * it must be binary tree
 */

public class ConvertSortedArraytoBinarySearchTree108 {
    public static class TreeNode {
        int val ;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        TreeNode head = dfs (nums , 0 , nums.length - 1);
        return head;
    }

    private TreeNode dfs (int[] nums , int i , int j) {
        if (i > j) return null;
        int mid = i + (j - i) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs (nums , i , mid - 1);
        node.right = dfs (nums , mid + 1 , j);
        return node;
    }
}
