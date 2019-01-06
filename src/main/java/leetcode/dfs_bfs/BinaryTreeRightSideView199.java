package leetcode.dfs_bfs;
/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */

import java.util.*;
public class BinaryTreeRightSideView199 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> tem = new LinkedList<>();
            boolean flagAdd = true;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (flagAdd) {
                    ret.add(node.val);
                    flagAdd = false;
                }
                if (node.right != null) tem.add(node.right);
                if (node.left != null) tem.add(node.left);
            }
            queue = tem;
        }
        return ret;
    }

    /**
     * 这里dfs有不断迭代替换的过程，右边的会把左边的替换掉！
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root , ret , 0);
        return ret;
    }
    private void dfs (TreeNode root , List<Integer> ret , int depth) {
        if (root == null) return ;
        if (depth >= ret.size()) {
            ret.add(root.val);
        } else {
            ret.set(depth , root.val);
        }
        dfs(root.left , ret , depth + 1);
        dfs(root.right , ret , depth + 1);
    }
}
