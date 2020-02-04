package leetcode.dfs_bfs;

/*
Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:



Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
Example 2:



Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
Example 3:

Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025
Example 4:

Input: root = [1,1]
Output: 1


Constraints:

Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].
 */


/**
 * Write a sub function s(TreeNode root) to get the sum of a sub tree.
 * s is short for sub and sum.
 *
 * First pass, get the toal sum.
 * Now we have the right total sum of the whole tree.
 * Second pass, find the biggest product.
 */

public class MaximumProductofSplittedBinaryTree1339 {
    private class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    double ret = 0;
    int mod = 1000000007;

    public int maxProduct(TreeNode root) {
        double sum = getSum(root);
        dfs(root, sum);
        return (int)(ret % (int)(1e9 + 7));
    }

    private double getSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    private double dfs(TreeNode root, double sum) {
        if (root == null) return 0;
        double temp = root.val + dfs(root.left, sum) + dfs(root.right, sum);
        ret = Math.max(ret, temp * (sum - temp));
        return temp;
    }
}
