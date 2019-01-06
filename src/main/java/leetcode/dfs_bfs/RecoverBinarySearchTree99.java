package leetcode.dfs_bfs;

/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 */

/**
 * This question appeared difficult to me but it is really just a simple in-order traversal! I got really frustrated when other people are showing off Morris Traversal which is totally not necessary here.
 *
 * Let's start by writing the in order traversal:
 *
 * private void traverse (TreeNode root) {
 *    if (root == null)
 *       return;
 *    traverse(root.left);
 *    // Do some business
 *    traverse(root.right);
 * }
 * So when we need to print the node values in order, we insert System.out.println(root.val) in the place of "Do some business".
 *
 * What is the business we are doing here?
 * We need to find the first and second elements that are not in order right?
 *
 * How do we find these two elements? For example, we have the following tree that is printed as in order traversal:
 *
 * 6, 3, 4, 5, 2
 *
 * We compare each node with its next one and we can find out that 6 is the first element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.
 *
 * Really, what we are comparing is the current node and its previous node in the "in order traversal".
 *
 * Let us define three variables, firstElement, secondElement, and prevElement. Now we just need to build the "do some business" logic as finding the two elements. See the code below:
 */

public class RecoverBinarySearchTree99 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        dfs(root);
        int value = first.val;
        first.val = second.val;
        second.val = value;
    }

    private void dfs (TreeNode node) {
        if (node == null) return ;

        dfs(node.left);
        if (first == null && pre.val > node.val) first = pre;
        if (first != null && pre.val > node.val) second = node;
        pre = node;
        dfs(node.right);
    }
}
