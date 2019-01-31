package leetcode.ListNode_or_TreeNode;

import java.util.*;
/**
 * Definition for a binary tree node.

 * }
 */

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class BinaryTreeZigzagOrderTraversal103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cnt = 0;
        while (!queue.isEmpty()) {
            List<Integer> tem = new ArrayList<>();
            int n = queue.size();
            for (int i = 0 ; i < n ; ++i) {
                TreeNode node = queue.poll();
                if (node.left !=  null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                if (cnt % 2 == 0) {
                    tem.add(node.val);
                } else {
                    tem.add(0 , node.val);
                }
            }
            ret.add(tem);
            ++cnt;
        }
        return ret;
    }
}
