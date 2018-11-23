package leetcode.ListNode_or_TreeNode;
/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */
import java.util.*;

public class FindLeavesOfBinaryTree366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(root , ret);
        return ret;
    }

    private int dfs(TreeNode root , List<List<Integer>> ret){
        if(root == null) return -1;
        int level = 1 + Math.max(dfs(root.left , ret) , dfs(root.right , ret));
        if(ret.size() < level + 1){//here is a point that i cannot put  != here because it will add more empty list on the ret;
            ret.add(new ArrayList<>());
        }
        ret.get(level).add(root.val);
        return level;
    }
}
