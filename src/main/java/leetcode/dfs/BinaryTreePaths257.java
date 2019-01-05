package leetcode.dfs;

/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
Seen this question in a real interview before?

 */

import java.util.*;
public class BinaryTreePaths257 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        dfs(root , "" , ret);
        return ret;
    }
    private void dfs (TreeNode root , String path , List<String> ret) {
        if (root == null) return ;
        if (root.left == null && root.right == null) {
            ret.add(path + root.val);
            return;
        }
        if (root.left != null) dfs(root.left , path + root.val + "->" , ret);
        if (root.right != null) dfs(root.right , path + root.val + "->" , ret);
    }
}
