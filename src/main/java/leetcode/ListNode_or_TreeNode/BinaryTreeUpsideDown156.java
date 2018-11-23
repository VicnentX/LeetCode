package leetcode.ListNode_or_TreeNode;

public class BinaryTreeUpsideDown156 {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null){
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;//这个return的值一直没有变过 就是为了输出的新的tree的时候，以此作为头。
    }
}
