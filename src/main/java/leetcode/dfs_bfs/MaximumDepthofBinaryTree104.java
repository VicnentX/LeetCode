package leetcode.dfs_bfs;

/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
 */

public class MaximumDepthofBinaryTree104 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth (TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left) , maxDepth(root.right)) + 1;
    }

    public static void main (String[] args) {
        MaximumDepthofBinaryTree104 md = new MaximumDepthofBinaryTree104();
        TreeNode node1 = new TreeNode(1);
        node1.left = null;
        node1.right = null;
        System.out.println(md.maxDepth(node1));
    }
}
