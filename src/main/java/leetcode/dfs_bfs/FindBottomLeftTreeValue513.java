package leetcode.dfs_bfs;

/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
 */

public class FindBottomLeftTreeValue513 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    private int ret , level;
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        ret = root.val;
        level = 0;
        dfs(root , 0);
        return ret;
    }
    private void dfs (TreeNode root , int depth) {
        if (root.left == null && root.right == null) {
            if (depth > level) {
                ret = root.val;
                level = depth;
            }
            return;
        }
        if (root.left != null) dfs(root.left , depth + 1);
        if (root.right != null) dfs(root.right , depth + 1);
    }
}
