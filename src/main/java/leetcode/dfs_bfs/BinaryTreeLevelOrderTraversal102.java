package leetcode.dfs_bfs;

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
Seen this question in a real interview before?

 */

import java.util.*;

public class BinaryTreeLevelOrderTraversal102 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Deque<TreeNode> tem = new LinkedList<>();
            List<Integer> values = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                values.add(node.val);
                if (node.left != null) tem.add(node.left);
                if (node.right != null) tem.add(node.right);
            }
            ret.add(values);
            queue = tem;
        }
        return ret;
    }
}
