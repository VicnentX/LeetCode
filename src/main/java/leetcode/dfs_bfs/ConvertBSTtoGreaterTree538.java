package leetcode.dfs_bfs;

/*
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */
public class ConvertBSTtoGreaterTree538 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    private void dfs (TreeNode root) {
        if (root == null) return ;
        dfs(root.right);
        root.val += sum;
        sum = root.val;
        dfs(root.left);
    }

}
