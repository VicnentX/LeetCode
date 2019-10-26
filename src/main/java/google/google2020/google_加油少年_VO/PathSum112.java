package google.google2020.google_加油少年_VO;

/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class PathSum112 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(0, root, sum);
    }

    private boolean dfs(int sum, TreeNode root, int tar) {
        if (root.left == null && root.right == null) {
            return sum + root.val == tar;
        }

        if (root.left != null) {
            if (dfs(sum + root.val, root.left, tar)) return true;
        }
        if (root.right != null) {
            if (dfs(sum + root.val, root.right, tar)) return true;
        }

        return false;
    }

}
