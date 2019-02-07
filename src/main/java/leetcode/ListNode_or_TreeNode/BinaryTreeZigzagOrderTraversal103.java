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
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> tem = new ArrayList<>();
            for (int i = 0 ; i < n ; ++i) {
                TreeNode cur = queue.poll();
                if (level % 2 == 0) {
                    tem.add(cur.val);
                } else {
                    tem.add(0 , cur.val);
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ret.add(tem);
            ++level;
        }

        return ret;
    }
}
