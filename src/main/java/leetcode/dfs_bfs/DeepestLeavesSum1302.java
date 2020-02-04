package leetcode.dfs_bfs;

/*
Given a binary tree, return the sum of values of its deepest leaves.


Example 1:



Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
 */

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum1302 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }

    //bfs
    public int deepestLeavesSum(TreeNode root) {
        int ret = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            int sum = 0;
            for (int i = 0; i < n; ++i) {
                TreeNode cur = queue.remove();
                sum += cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ret = sum;
        }
        return ret;
    }

    //dfs

    private int maxDepth = 0;
    private int ret = 0;

    public int deepestLeavesSumDFS(TreeNode root) {
         dfs(root, 0);
         return ret;
    }

    private void dfs(TreeNode root, int depth) {
        if (root.left != null) {
            dfs(root.left, depth + 1);
        }
        if (root.right != null) {
            dfs(root.right, depth + 1);
        }
        if (depth == maxDepth) {
            ret += root.val;
        }
        if (depth > maxDepth) {
            ret = root.val;
            maxDepth = depth;
        }
    }


}
