package leetcode.dfs_bfs;

/*
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)



Example 1:



Input: [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have and average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have and average of 6 / 1 = 6.
For the node with value = 1 we have and average of 1 / 1 = 1.
So the answer is 6 which is the maximum.


Note:

The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */


class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        this.val = x;
    }
}
public class MaximumAverageSubtree1120 {
    private double max = 0;
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return max;
    }
    //return value is [sumNumber , sumCnt]
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[] {0 , 0};
        int[] lefts = dfs(node.left);
        int[] rights = dfs(node.right);
        int sumNumber = lefts[0] + rights[0] + node.val;
        int sumCnt = lefts[1] + rights[1] + 1;
        max = Math.max(max , sumNumber * 1.0 / sumCnt);
        return new int[] {sumNumber, sumCnt};
    }
}
