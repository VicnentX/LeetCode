package Amazon.full_time2020;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Seen this question in a real interview before?
 */

/*
Hi guys, this is my Java solution. I read this post, which is very helpful.

The basic idea is here:
Say we have 2 arrays, PRE and IN.
Preorder traversing implies that PRE[0] is the root node.
Then we can find this PRE[0] in IN, say it's IN[5].
Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
Recursively doing this on subarrays, we can build a tree out of it :)

Hope this helps.
 */

public class ConstructBinaryTreefromPreorderandInorderTraversal105 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0 , 0 , inorder.length - 1, preorder, inorder);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        int index = -1;
        for (int i = inStart; i <= inEnd; ++i) {
            if (inorder[i] == preorder[preStart]) {
                index = i;
                break;
            }
        }

        TreeNode node = new TreeNode(preorder[preStart]);

        node.left = helper(preStart + 1, inStart, index - 1, preorder, inorder);
        node.right = helper(preStart + (index - inStart) + 1, index + 1, inEnd, preorder, inorder);
        return node;
    }
}

