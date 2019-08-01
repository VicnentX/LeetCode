package leetcode.UnionFind;

/*
There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.



Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation:
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation:
There is no way to connect all cities even if all edges are used.


Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
 */

/**
 * We use Kruskal’s algorithm to generate a minimum spanning tree for the graph. Use Union-Find to detect cycle.
 *
 * Idea is simple:
 *
 * Sort edges to no-descresing order
 * Pick the smallest edge that does not form a cycle
 * Repeat until MST is formed and every node is connected.
 * Implemented Union-Find with path comression to improve efficiency.
 *
 * There are tons of materials online about the proof of correctness and analysis of this algorithm. Feel free to check them around.
 *
 * Hope this helps.
 */

import java.util.Arrays;

public class ConnectingCitiesWithMinimumCost1135 {
    public int minimumCost(int N, int[][] connections) {
        int ret = 0;
        int cnt = N;
        Arrays.sort(connections, (a,b) -> a[2] - b[2]);
        int[] roots = new int[N + 1];
        for (int i = 0 ; i <= N ; ++i) {
            roots[i] = i;
        }
        for (int[] pair : connections) {
            if (cnt == 1) break;
            int p1 = find(roots, pair[0]);
            int p2 = find(roots , pair[1]);
            if (p1 != p2) {
                roots[p1] = p2;
                ret += pair[2];
                --cnt;
            }
        }
        return cnt == 1 ? ret : -1;
    }

    private int find(int[] roots , int id) {
        if (roots[id] == id) return id;
        roots[id] = find(roots , roots[id]);
        return roots[id];
    }

    public static void main(String[] args) {

    }
}
