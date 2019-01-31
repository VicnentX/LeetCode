package leetcode.dfs_bfs;


/*
Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:






We should return its max depth, which is 3.



Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 */

import java.util.*;
public class MaximumDepthofNaryTree559 {
    public class Node {
        int val;
        List<Node> children;
        Node(){}
        Node(int x , List<Node> chs) {
            val = x;
            children = chs;
        }
    }
    public int maxDepth(Node root) {
        int level = 0;
        if (root == null) return level;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0  ; i < n ; ++i) {
                Node cur = queue.poll();
                for (Node child : cur.children) {
                    queue.add(child);
                }
            }
            ++level;
        }
        return level;
    }
}
