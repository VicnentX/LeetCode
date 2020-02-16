package leetcode.ListNode_or_TreeNode;

public class FindMaxValueInATree {
    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int max(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        int leftMax = max(root.left);
        int rightMax = max(root.right);
        return Math.max(root.val, Math.max(leftMax, rightMax));
    }
}
