package leetcode.Iterator;

/*
Given an n-ary tree, return the preorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its preorder traversal as: [1,3,5,6,2,4].



Note:

Recursive solution is trivial, could you do it iteratively?
 */

import java.util.*;

public class NaryTreePreorderTraversal589 {
    public class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> ret = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.empty()) {
            Node cur = stack.pop();
            ret.add(cur.val);
            if (!cur.children.isEmpty()) {
                for (int i = cur.children.size() - 1 ; i >= 0 ; --i) {
                    stack.push(cur.children.get(i));
                }
            }
        }
        return ret;
    }
}
