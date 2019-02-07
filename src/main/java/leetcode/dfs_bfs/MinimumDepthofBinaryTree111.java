package leetcode.dfs_bfs;

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

Seen this question in a real interview before?

 */

import java.util.*;

public class MinimumDepthofBinaryTree111 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public int minDepth_DFS(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }
    private int dfs (TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null && root.right == null) return 1;
        return 1 + Math.min(dfs(root.left) , dfs(root.right));
    }

    //BFS

    public int minDepth_BFS(TreeNode root) {
        if (root == null) return 0;
        int level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0 ; i < n ; ++i) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) return level;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ++level;
        }
        return 0;
    }


}
