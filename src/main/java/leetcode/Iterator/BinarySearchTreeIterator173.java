package leetcode.Iterator;

/*
侧过来看就是dfs的深度优先 左子树就是深度相当于

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */

import java.util.*;

public class BinarySearchTreeIterator173 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    private Stack<TreeNode> stack;
    private int cur;

    public BinarySearchTreeIterator173(TreeNode root) {
        stack = new Stack<>();
        if (root != null) stack.push(root);
    }

    public int next() {
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            while (node != null) {
                stack.push(node);
                TreeNode tem = node.left;
                node.left = null;
                node = tem;
            }
            node = stack.pop();
            int cur = node.val;
            if (node.right != null) stack.push(node.right);
            return cur;
        }
        return -1;
    }

    public boolean hasnext() {
        return !stack.isEmpty();
    }
}

/*
class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    @return the next smallest number
    public int next() {
        TreeNode cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return ret;
    }

        @return whether we have a next smallest number
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
 */
