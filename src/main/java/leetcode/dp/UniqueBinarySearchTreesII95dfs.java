package leetcode.dp;

/*

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

import java.util.*;

public class UniqueBinarySearchTreesII95dfs {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        //ret[i] means the list of trees that is constricted by 1 ,2 ,3...,i
        List<TreeNode>[] ret = new List[n + 1];
        ret[0] = new ArrayList<>();
        if (n == 0) return ret[0];

        ret[0].add(null);

        for (int i = 1 ; i <= n ; ++i) { //i means from 1  to i
            ret[i] = new ArrayList<>();
            for (int j = 0 ; j < i ; ++j) {
                List<TreeNode> leftTrees = ret[j];
                List<TreeNode> rightTrees = ret[i - j - 1]; //ret后的是指node的个数
                for (TreeNode leftSubTree : leftTrees) {
                    for (TreeNode rightSubTree : rightTrees) {
                        TreeNode root = new TreeNode(j + 1);
                        root.left = leftSubTree;
                        root.right = clonePlusOffset(rightSubTree , j + 1);
                        ret[i].add(root);
                    }
                }
            }
        }
        return ret[n];
    }

    private TreeNode clonePlusOffset (TreeNode node , int offset) {
        if (node == null) return null;
        TreeNode root = new TreeNode(node.val + offset);
        root.left = clonePlusOffset(node.left , offset);
        root.right = clonePlusOffset(node.right , offset);
        return root;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesII95dfs ub = new UniqueBinarySearchTreesII95dfs();
        System.out.println(ub.generateTrees(3));
    }
}
