package leetcode.dfs_bfs;

/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */

public class LongestUnivaluePath687 {
    public static class TreeNode {
        int val ;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public int longestUnivaluePath(TreeNode root) {
        int[] ret = dfs(root);
        return ret[0];
    }

    // {max local , max path }
    private int[] dfs (TreeNode root) {
        if (root == null) return new int[] {0 , 0};

        int[] lc = dfs (root.left);
        int[] rc = dfs (root.right);

        if (root.left != null && root.left.val == root.val && root.right != null && root.right.val == root.val) {
            return new int[] {Math.max(2 + lc[1] + rc[1] , Math.max(lc[0] , rc[0])) , 1 + Math.max(lc[1] , rc[1])};
        } else if (root.left != null && root.left.val == root.val) {
            return new int[] {Math.max(1 + lc[1] , Math.max(lc[0] , rc[0])) , 1 + lc[1]};
        } else if (root.right != null && root.right.val == root.val) {
            return new int[] {Math.max(1 + rc[1] , Math.max(lc[0] , rc[0])) , 1 + rc[1]};
        } else {
            return new int[] {Math.max(lc[0] , rc[0]) , 0};
        }
    }
}
