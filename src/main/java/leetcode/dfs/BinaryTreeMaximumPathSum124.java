package leetcode.dfs;
/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */

/**
 * 这题和ret比较的值 和 return的值不一样
 */

public class BinaryTreeMaximumPathSum124 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private int ret = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return ret;
    }
    private int dfs (TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0 , dfs(node.left));
        int right = Math.max(0 , dfs(node.right));
        int value = node.val + left + right;
        ret = Math.max(ret , value);
        return node.val + Math.max(left , right);
    }

    public static void main (String[] args) {
        BinaryTreeMaximumPathSum124 bt = new BinaryTreeMaximumPathSum124();
        TreeNode root = new TreeNode (1);
        TreeNode left = new TreeNode (2);
        TreeNode right = new TreeNode (3);
        root.left = left;
        root.right = right;
        System.out.println(bt.maxPathSum(root));
    }
}
