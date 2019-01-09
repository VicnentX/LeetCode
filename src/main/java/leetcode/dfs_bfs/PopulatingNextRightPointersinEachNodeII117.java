package leetcode.dfs_bfs;

/*
Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
 */

/**
 * it looks like BFS
 */
public class PopulatingNextRightPointersinEachNodeII117 {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left , right , next;
        TreeLinkNode (int x) {
            val = x;
            next = null;
        }
    }

    public void connect(TreeLinkNode root) {
        if (root == null) return ;
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode pre = dummy;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }
    }
}
