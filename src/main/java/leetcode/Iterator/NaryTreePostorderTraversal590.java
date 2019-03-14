package leetcode.Iterator;

/*
Given an n-ary tree, return the postorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its postorder traversal as: [5,6,3,2,4,1].


Note:

Recursive solution is trivial, could you do it iteratively?
 */

import java.util.*;

public class NaryTreePostorderTraversal590 {
    public class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root , ret);
        return ret;
    }
    private void dfs(Node root , List<Integer> ret) {
        if (root  == null) return;
        for (Node node : root.children) {
            dfs(node , ret);
        }
        ret.add(root.val);
    }

}
