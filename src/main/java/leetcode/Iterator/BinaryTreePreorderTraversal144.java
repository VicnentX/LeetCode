package leetcode.Iterator;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?

Seen this question in a real interview before?

 */

import java.util.*;

public class BinaryTreePreorderTraversal144 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            while (cur != null) {
                ret.add(cur.val);
                stack.push(cur.right);
                cur = cur.left;
            }
        }
        return ret;
    }
}
