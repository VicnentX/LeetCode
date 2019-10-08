package Amazon.full_time2020.final_round_加油少年_coding;

/**
 * 第二个是去除一条边，使得图变树。
 */

/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 */

public class RedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[]{};
        //find N
        int max = 0;
        for (int[] edge: edges) {
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        //initialize the roots array;
        int[] roots = new int[max + 1];
        for (int i = 0; i < roots.length; ++i) {
            roots[i] = i;
        }
        //check redundant Connection
        for (int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            int root1 = findRoot(node1, roots);
            int root2 = findRoot(node2, roots);
            if (root1 != root2) {
                roots[root2] = root1;
            } else {
                return new int[] {node1, node2};
            }
        }
        return new int[]{};
    }

    private int findRoot(int id, int[] roots) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots[id], roots);
        return roots[id];
    }
}
