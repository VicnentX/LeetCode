package leetcode.Number;

import java.util.*;

/*333. Largest BST Subtree

        Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

        Note:
        A subtree must include all of its descendants.

        Example:

        Input: [10,5,15,1,8,null,7]

        10
        / \
        5  15
        / \   \
        1   8   7

        Output: 3
        Explanation: The Largest BST Subtree in this case is the highlighted one.
        The return value is the subtree's size, which is 3.
        */

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class RetType{
    int cnt;
    int max;
    int min;
    boolean isBST;
    public RetType(int cnt , int min , int max , boolean bst){
        this.cnt = cnt;
        this.max = max;
        this.min = min;
        this.isBST = bst;
    }

}

public class LargestBSTSubtree {

    int ret = 0;

    public int largestBSTSubtree(TreeNode root){
        helper(root);
        return ret;
    }

    private RetType helper(TreeNode root){
        if(root == null){
            return new RetType(0 , Integer.MAX_VALUE , Integer.MAX_VALUE , true);
        }

        RetType left = helper(root.left);
        RetType right = helper(root.right);

        int nums = left.cnt + 1 + right.cnt;

        if(!left.isBST || !right.isBST) return new RetType(nums , 0 , 0 , false);

        if(root.left != null && root.val <= left.max || root.right != null && root.val >= right.min){
            return new RetType(nums , 0 , 0 , false);
        }

        //current tree is BST, update max;
        ret = Math.max(ret , nums);
        return new RetType(nums , Math.min(left.min , root.val) , Math.max(right.max , root.val) , true);
        //上面要用Math.min是因为我在最底层的时候null节点也算进去，
        // 所以这里的Math.min和Math.max只在最底层的的节点和空节点有用。之后其实都是左右节点的值
    }
}
