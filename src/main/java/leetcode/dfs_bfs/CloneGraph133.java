package leetcode.dfs_bfs;
/*
Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the nodes in its neighbors.


OJ's undirected graph serialization (so you can understand error output):
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.


As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.


Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.

Seen this question in a real interview before?

 */

import java.util.*;

public class CloneGraph133 {
    public static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };

    public class Solution {
        private Map<Integer , UndirectedGraphNode> map = new HashMap<>();
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            return cloneDFS(node);
        }
        private UndirectedGraphNode cloneDFS (UndirectedGraphNode node) {
            if (node == null) return null;

            if (map.containsKey(node.label)) {
                return map.get(node.label);
            }

            UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
            map.put(copy.label , copy);
            for (UndirectedGraphNode un : node.neighbors) {
                copy.neighbors.add(cloneDFS(un));
            }
            return copy;
        }
    }

    /**
     * bfs
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {
        if (root == null) return null;

        // use a queue to help BFS
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(root);

        // use a map to save cloned nodes
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        // clone the root
        map.put(root, new UndirectedGraphNode(root.label));

        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();

            // handle the neighbors
            for (UndirectedGraphNode neighbor : node.neighbors) {
                if (!map.containsKey(neighbor)) {
                    // clone the neighbor
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    // add it to the next level
                    queue.add(neighbor);
                }

                // copy the neighbor
                map.get(node).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(root);
    }
}
