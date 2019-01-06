package leetcode.dfs_bfs;

/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */

/**
 * The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array; then locate the range for left sub-tree and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
 * 因为postorder的长度应该等于inorder的长度 所以通过inorder的左右 我就能推出对应的postorder的长度选取多长
 *
 */

import java.util.*;
public class ConstructBinaryTreefromInorderandPostorderTraversal106 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) return null;
        if (inorder.length == 1) return new TreeNode(inorder[0]);
        int n = inorder.length;
        Map<Integer , Integer> map = new HashMap<>();
        for (int i = 0 ; i < n ; ++i) {
            map.put(inorder[i] , i);
        }
        return dfs(inorder , 0 , n - 1 , postorder , 0 , n - 1 , map);
    }

    private TreeNode dfs (int[] inorder , int inStart , int inEnd , int[] postorder , int postStart , int postEnd , Map<Integer , Integer> map) {
        if (inStart > inEnd || postStart > postEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = map.get(postorder[postEnd]);
        root.left = dfs(inorder , inStart , index - 1 , postorder , postStart , postStart + index - inStart - 1 , map);
        root.right = dfs(inorder , index + 1 , inEnd , postorder , postStart + index - inStart , postEnd - 1 , map);
        return root;
    }
}
