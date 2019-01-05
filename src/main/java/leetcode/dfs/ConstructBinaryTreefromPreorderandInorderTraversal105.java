package leetcode.dfs;


/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */

/**
 * 解法
 * 相信学过数据结构的同学应该都对这道题目有深刻的印象，虽然它是二叉树的题目，但是其更多使用到的还是分治的思想。
 *
 * 对于给定的前序遍历preorder和中序遍历inorder，首先我们不难发现，这棵树的根结点其实就是preorder[0]。由于preorder和inorder是对同一棵树的遍历，我们可以知道preorder[0]在inorder中一定也存在，不妨设preorder[0]==inorder[k]。
 *
 * 由于inorder是中序遍历，所以k左边的就是根节点左子树的中序遍历、k右边的就是根节点右子树的中序遍历。
 *
 * 并且，由于我们已经知道了根节点左子树的节点数（与中序遍历长度相同），不妨设为l，我们可以知道preorder从1到l+1就是根节点左子树的前序遍历，剩下的最后一部分就是根节点右子树的前序遍历。
 *
 * 也就是说，我们可以计算出左子树、右子树的前序遍历和中序遍历，从而可以用分治的思想，将规模较大的问题分解成为两个较小的问题，然后递归的进行处理，还原左子树和右子树，最后连通根节点一起组成一棵完整的树。
 *
 * 因为过程实际上很简单~建议同代码一起参考~
 */

/*
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int len = preorder.length;
        if( len == 0)
            return null;
        return helper(preorder,0,len-1,inorder,0,len-1);


    }



    public TreeNode helper( int[] preorder,int pre_start,int pre_end,int[] inorder,int in_start,int in_end){

        if( pre_start > pre_end || in_start > in_end )
            return null;
        TreeNode node = new TreeNode(preorder[pre_start]);


        int size = 0;
        for( int i = in_start;i<=in_end && inorder[i] != preorder[pre_start];i++,size++)
            ;
        node.left = helper(preorder,pre_start+1,pre_start+size,inorder,in_start,in_start+size-1);

        node.right = helper(preorder,pre_start+size+1,pre_end,inorder,in_start+size+1,in_end);

        return node;


    }
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal105 {
    public static class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;
        int n = preorder.length;
        return dfs (preorder , 0 , n - 1 , inorder , 0 , n - 1);
    }

    private TreeNode dfs (int[] preorder , int preStart , int preEnd , int[] inorder , int inStart , int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int size = 0;
        for (int i = inStart ; i < inStart && inorder[i] != preorder[preStart] ; ++i , ++size) {
            //do nothing just count size
        }
        root.left = dfs (preorder , preStart + 1, preStart + size , inorder , inStart , inStart + size - 1);
        root.right = dfs (preorder , preStart + size + 1 , preEnd , inorder , inStart + size + 1 , inEnd);
        return root;
    }

}
