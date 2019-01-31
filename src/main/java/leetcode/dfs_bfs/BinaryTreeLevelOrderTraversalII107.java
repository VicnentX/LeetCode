package leetcode.dfs_bfs;

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
Seen this question in a real interview before?

 */

import java.util.*;

public class BinaryTreeLevelOrderTraversalII107 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
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
            ret.add(0 , values);
            queue = tem;
        }
        return ret;
    }

    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs (root , 0 , ret);
        return ret;
    }

    private void dfs (TreeNode root , int level , List<List<Integer>> ret) {
        if (root == null) {
            return ;
        }

        if (level == ret.size()) {
            ret.add(0 , new ArrayList<>());
        }

        dfs (root.left , level + 1 , ret);
        dfs (root.right , level + 1 , ret);
        ret.get(ret.size() - level - 1).add(root.val);
    }


}
