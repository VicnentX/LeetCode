package leetcode.dfs;

public class SymmetricTree101 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return dfs(root.left , root.right);
    }
    private boolean dfs(TreeNode p , TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        return p.val == q.val && dfs(p.left , q.right) && dfs(p.right , q.left);
    }
}
