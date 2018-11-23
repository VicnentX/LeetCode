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
        if(root == null) return ret;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.add(root);
        int level = 0;
        while(!dq.isEmpty()){
            List<Integer> tem = new ArrayList<>();
            Deque<TreeNode> next = new LinkedList<>();
            int len = dq.size();
            for(int i = 0 ; i < len ; ++i){
                if(level % 2 == 0){
                    TreeNode node = dq.removeLast();
                    tem.add(node.val);
                    if(node.left != null){
                        next.add(node.left);
                    }
                    if(node.right != null){
                        next.add(node.right);
                    }
                }else{
                    TreeNode node = dq.removeLast();
                    tem.add(node.val);
                    if(node.right != null){
                        next.add(node.right);
                    }
                    if(node.left != null){
                        next.add(node.left);
                    }
                }
            }
            ++level;
            dq = next;
            ret.add(tem);
        }
        return ret;
    }
}
