package Amazon.full_time2020;


/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Seen this question in a real interview before?

 */
public class NumberofConnectedComponentsinanUndirectedGraph323 {
    public int countComponents(int n, int[][] edges) {
        //use unitFind . O(E)
        int[] roots = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            roots[i] = i;
        }

        //check edge
        for (int[] edge: edges) {
            int root1 = findRoot(edge[0], roots);
            int root2 = findRoot(edge[1], roots);
            if (root1 != root2) {
                roots[root1] = root2;
                n--;
            }
        }

        return n;
    }

    private int findRoot(int id, int[] roots) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots[id], roots);
        return roots[id];
    }

    public static void main(String[] args) {
        NumberofConnectedComponentsinanUndirectedGraph323 numberofConnectedComponentsinanUndirectedGraph323 = new NumberofConnectedComponentsinanUndirectedGraph323();
        System.out.println(numberofConnectedComponentsinanUndirectedGraph323.countComponents(5, new int[][]{{0,1}, {1,2}, {3,4}}));
        System.out.println(numberofConnectedComponentsinanUndirectedGraph323.countComponents(5, new int[][]{{0,1}, {1,2}, {2,3}, {3,4}}));
    }
}
