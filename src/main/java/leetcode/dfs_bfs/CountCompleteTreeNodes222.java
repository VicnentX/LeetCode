package leetcode.dfs_bfs;

public class CountCompleteTreeNodes222 {
    public int countNodes(TreeNode root) {
        int leftDepth = countLeft(root);
        int rightDepth = countRight(root);
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int countRight(TreeNode root) {
        int dep = 0;
        while(root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int countLeft(TreeNode root) {
        int dep = 0;
        while(root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }
}
