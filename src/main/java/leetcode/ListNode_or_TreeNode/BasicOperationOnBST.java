package leetcode.ListNode_or_TreeNode;

public class BasicOperationOnBST {
    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    public TreeNode createBST(int[] nums) {
        TreeNode root = null;
        for (int num: nums) {
            root = insert(num, root);
        }
        return root;
    }

    public TreeNode insert(int num, TreeNode root) {
        if (root == null) return new TreeNode(num);
        if (num < root.val) {
            root.left = insert(num, root.left);
        } else {
            root.right = insert(num, root.right);
        }
        return root;
    }

    public void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.println(root.val);
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        BasicOperationOnBST basicOperationOnBST = new BasicOperationOnBST();
        TreeNode root = basicOperationOnBST.createBST(new int[] {2,4,5,6,7,9,8,3});
        basicOperationOnBST.inorderPrint(root);
    }
}
