package leetcode.dfs_bfs;

/*
//Method 1: use HashMap
//1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.
class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap();
//here can also use Map<TreeNode, TreeNode> to only store the child - parent mapping, since parent-child mapping is inherent in the tree structure
 */

/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.


Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 */


import java.util.*;
public class AllNodesDistanceKinBinaryTree863 {
    public class TreeNode {
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ret = new ArrayList<>();
        Map<TreeNode , List<TreeNode>> map = new HashMap<>();
        buildMap (root , null , map);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        while (!queue.isEmpty()) {
            int n = queue.size();
            if (k == 0) {
                for (int i = 0 ; i < n ; ++i) {
                    ret.add(queue.poll().val);
                }
                return ret;
            }
            for (int i = 0 ; i < n ; ++i) {
                TreeNode cur = queue.poll();
                for (TreeNode next : map.get(cur)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            --k;
        }
        return ret;
    }

    private void buildMap (TreeNode child , TreeNode parent , Map<TreeNode , List<TreeNode>> map) {
        if (child == null) return ;
        if (!map.containsKey(child)) {
            map.put(child , new ArrayList<>());
            if (parent != null) {
                map.get(child).add(parent);
                map.get(parent).add(child);
            }
            buildMap(child.left , child , map);
            buildMap(child.right , child , map);
        }
    }

}
