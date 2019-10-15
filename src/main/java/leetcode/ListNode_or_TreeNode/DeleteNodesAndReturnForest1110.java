package leetcode.ListNode_or_TreeNode;

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.



Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]


Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */

/**
 *  这题dfs函数有返回值
 *  但是在主函数没有使用
 *
 *  dfs这个返回值是为什么把那些在set里面的node改成null
 */

import java.util.*;

public class DeleteNodesAndReturnForest1110 {

    private List<TreeNode> ret = new ArrayList<>();
    Set<Integer> set;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        set = new HashSet<>();
        for (int i: to_delete) {
            set.add(i);
        }
        dfs(root, true);
        return ret;
    }

    private TreeNode dfs(TreeNode root, boolean isRoot) {
        if (root == null) return null;
        boolean isDeleted = set.contains(root.val);
        if (isRoot && !isDeleted) ret.add(root);
        root.left = dfs(root.left, isDeleted);
        root.right = dfs(root.right, isDeleted);
        return isDeleted ? null : root;
    }


}
