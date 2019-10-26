package google.google2020.google_加油少年_VO;

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

import java.util.*;

public class DeleteNodesAndReturnForest1110 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    private List<TreeNode> ret = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int i: to_delete) {
            set.add(i);
        }
        dfs(root, set, true);
        return ret;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> set, boolean canBeRoot) {
        if (root == null) return null;
        boolean deleted = set.contains(root.val);
        if (!deleted && canBeRoot) ret.add(root);
        root.left = dfs(root.left, set, deleted);
        root.right = dfs(root.right, set, deleted);
        return deleted ? null : root;
    }

}
