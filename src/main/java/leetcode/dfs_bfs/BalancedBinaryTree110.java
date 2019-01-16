package leetcode.dfs_bfs;

/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
 */

/**
 * this problem is dealt with both of the boolean and int return value . it is key of this problem.
 */

public class BalancedBinaryTree110 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(dfs(root.left) - dfs(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int dfs (TreeNode root) {
        if (root == null) return 0;
        return Math.max(dfs(root.left) , dfs(root.right)) + 1;
    }
}
