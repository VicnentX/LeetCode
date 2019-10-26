package google.google2020.google_加油少年_VO;

/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths257 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        return dfs(root, new ArrayList<>(), "");
    }

    private List<String> dfs(TreeNode root, ArrayList<String> ret, String path) {

        path += (root.val + "->");

        if (root.left == null && root.right == null) {
            ret.add(path.substring(0, path.length() - 2));
            return ret;
        }
        if (root.left != null) {
            dfs(root.left, ret, path);
        }
        if (root.right != null) {
            dfs(root.right, ret, path);
        }

        return ret;
    }

}
