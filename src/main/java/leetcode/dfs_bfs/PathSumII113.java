package leetcode.dfs_bfs;

/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
Seen this question in a real interview before?

 */

import java.util.*;

public class PathSumII113 {
    public static class TreeNode {
        int val;
        TreeNode left  , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(root , 0 , sum , new ArrayList<Integer>() , ret);
        return ret;
    }

    private void dfs (TreeNode root , int cur , int sum , List<Integer> path , List<List<Integer>> ret) {
        if (root == null) {
            return ;
        }

        cur += root.val;
        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (cur == sum) {
                ret.add(new ArrayList<>(path));
            }
            return ;
        }

        if (root.left != null) {
            dfs(root.left , cur , sum , path , ret);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            dfs(root.right , cur , sum , path, ret);
            path.remove(path.size() - 1);
        }
    }
}
