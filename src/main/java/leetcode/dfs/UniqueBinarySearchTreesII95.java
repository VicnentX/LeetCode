package leetcode.dfs;

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

public class UniqueBinarySearchTreesII95 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        return dfs(1 , n);
    }

    private List<TreeNode> dfs(int start , int end) {
        List<TreeNode> trees = new ArrayList<>();

        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int i = start ; i <= end ; ++i) { // i means the node root
            List<TreeNode> leftTree = dfs(start , i - 1);
            List<TreeNode> rightTree = dfs(i + 1 , end);

            for (TreeNode leftSubTree : leftTree) {
                for (TreeNode rightSubTree : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubTree;
                    root.right = rightSubTree;
                    trees.add(root);
                }
            }
        }

        return trees;
    }

    public static void main (String[] args) {
        UniqueBinarySearchTreesII95 ub = new UniqueBinarySearchTreesII95();
        System.out.println(ub.generateTrees(3));
    }
}
