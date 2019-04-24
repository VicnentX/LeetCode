package Amazon.cv;

/*
preorder
也可以用iterator来做 通过stack
 */

import java.util.*;

public class preorder {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    public void printbypreoder (TreeNode root) {
        if (root == null) return ;
        System.out.print(root.val + " ");
        printbypreoder(root.left);
        printbypreoder(root.right);
    }
}
