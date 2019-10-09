package Amazon.full_time2020.final_round_加油少年_coding;

/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */


/**
 * For every node,
 * length of longest path which pass it
 * = MaxDepth of its left subtree + MaxDepth of its right subtree.
 *
 * 就是找出任意两个点的距离 然后取最大值
 *
 */

public class DiameterofBinaryTree543 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }
}
