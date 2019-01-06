package leetcode.dfs_bfs;

/*
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */

/**
 * 这题我有两种方法 第一种是我自己想的 先用preorder把list存起来 一个一个连起来 记得把left制为空 空间复杂度是O（n）
 *
 * 第二种方法就是用post－order 然后从bottom开始链接起来 这种方法空间复杂度是O（1）；
 */

import java.util.*;

public class FlattenBinaryTreetoLinkedList114 {

    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private List<TreeNode> ret;
    public void flattenPreOrder(TreeNode root) {
        ret = new ArrayList<>();
        dfs (root);
        for (int i = 1 ; i < ret.size() ; ++i) {
            root.left = null;
            root.right = ret.get(i);
            root = ret.get(i);
        }
    }

    private void dfs (TreeNode node) {
        if (node == null) return;
        ret.add(node);
        dfs(node.left);
        dfs(node.right);
    }

    private TreeNode pre = null;
    public void flattenPostOrder(TreeNode root) {
        if (root == null) return;
        flattenPostOrder(root.right);
        flattenPostOrder(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

}
