package leetcode.ListNode_or_TreeNode;

/*
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:

Input:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7


Note: The merging process must start from the root nodes of both trees.

Seen this question in a real interview before?

 */

public class MergeTwoBinaryTrees617 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1 , TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int value = t1 == null ? t2.val : t2 == null ? t1.val : t1.val + t2.val;
        TreeNode root = new TreeNode (value);
        if (t1 == null) {
            root.left = mergeTrees(null , t2.left);
            root.right = mergeTrees(null , t2.right);
        } else if (t2 == null) {
            root.left = mergeTrees(t1.left , null);
            root.right = mergeTrees(t1.right , null);
        } else {
            root.left = mergeTrees(t1.left , t2.left);
            root.right = mergeTrees(t1.right , t2.right);
        }
        return root;
    }
}
