package google.google2020.google_加油少年_ResidentVO;

/*
给一颗二叉树，TreeNode class 包含三个函数，
第一个函数返回左节点，第二个函数返回右节点，
第三个函数返回当前节点所有子节点个数（比如整颗树总共有10个节点，那么对于根节点来说，这个函数会返回9，也就是其所有子节点的个数）
，要求写一个算法，返回按中序遍历的第K个节点。
 */

public class KthInOrderFinder {

    int cnt = 0;

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode getLeftChildNode(TreeNode root) {
        return root.left;
    }
    public TreeNode getRightChildNode(TreeNode root) {
        return root.right;
    }
    public int cntChildrenNodes(TreeNode root) {
        cnt = 0;
        dfs(root);
        return cnt;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        cnt++;
        dfs(root.left);
        dfs(root.right);
    }

    public int solve(TreeNode root, int k) {
        int cntOfLeftNodes = cntChildrenNodes(root.left);
        if (k > cntChildrenNodes(root) + 1) return Integer.MIN_VALUE;
        if (1 + cntOfLeftNodes == k - 1) return root.val;
        if (1 + cntOfLeftNodes > k - 1) {
            return solve(root.left, k);
        } else {
            return solve(root.right, k - 1 - cntOfLeftNodes);
        }
    }
}
