package leetcode.dfs_bfs;

/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */

public class SymmetricTree101 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || dfs(root.left , root.right);
    }

    private boolean dfs (TreeNode left , TreeNode right) {
        if (left == null || right == null) return left == right;
        return left.val == right.val && dfs(left.left , right.right) && dfs(left.right , right.left);
    }
}
