package leetcode.dfs_bfs;

/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
 */

/**
 * stringbuilder is not like string when dfs
 * so !!
 * MUST TO TRACKBACK
 * !!
 */

public class SumRoottoLeafNumbers129 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    private int ret = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root , new StringBuilder());
        return ret;
    }
    private void dfs (TreeNode root , StringBuilder sum) {
        sum.append(root.val);
        if (root.left == null && root.right == null) {
            ret += Integer.valueOf(sum.toString());
        }
        if (root.left != null) {
            dfs(root.left , sum);
            sum.deleteCharAt(sum.length() - 1);
        }
        if (root.right != null) {
            dfs(root.right , sum);
            sum.deleteCharAt(sum.length() - 1);
        }
    }
}
