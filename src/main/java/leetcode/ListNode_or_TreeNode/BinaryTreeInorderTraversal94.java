package leetcode.ListNode_or_TreeNode;

/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

Seen this question in a real interview before?

 */

import java.util.*;

public class BinaryTreeInorderTraversal94 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private List<Integer> ret = new ArrayList<>();
    public List<Integer> inorderTraversalDFS(TreeNode root) {
        dfs(root);
        return ret;
    }
    private void dfs (TreeNode root) {
        if (root == null) return ;
        dfs(root.left);
        ret.add(root.val);
        dfs(root.right);
    }

    public List<Integer> inorderTraversalIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ret = new ArrayList<>();
        while (root != null) {
            stack.push(root);
            TreeNode left = root.left;
            root.left = null;
            root = left;

        }
        while (!stack.empty()) {
            TreeNode tem = stack.pop();
            ret.add(tem.val);
            TreeNode right = tem.right;
            while (right != null) {
                stack.push(right);
                TreeNode left = right.left;
                right.left = null;
                right = left;
            }
        }
        return ret;
    }


    public List<Integer> inorderTraversalIteratorOpt(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ret.add(cur.val);
            cur = cur.right;
        }

        return ret;
    }

}
