package leetcode.dfs_bfs;

/*
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:







We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]


Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
Seen this question in a real interview before?

 */
import java.util.*;
public class NaryTreeLevelOrderTraversal429 {
    public class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> tem = new ArrayList<>();
            for (int i = 0 ; i < n ; ++i) {
                Node cur = queue.poll();
                tem.add(cur.val);
                for (Node node : cur.children) {
                    queue.add(node);
                }
            }
            ret.add(tem);
        }

        return ret;
    }


}
