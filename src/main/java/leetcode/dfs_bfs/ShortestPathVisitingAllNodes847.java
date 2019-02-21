package leetcode.dfs_bfs;

/*
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.



Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]


Note:

1 <= graph.length <= 12
0 <= graph[i].length < graph.length
Seen this question in a real interview before?

 */

import java.util.*;

public class ShortestPathVisitingAllNodes847 {
    //time complexity: n*2^n
    //space complexity : n*2^n
    //queue element is pair(node , state) state means the nodes have been visited
    //visited[node][state] = 1 means pair(node , state) have been visited
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int target = (1 << n) - 1;
        System.out.println(target);
        Queue<int[]>  queue = new LinkedList<>();
        int[][] visited = new int[n][1 << n];
        for (int i = 0 ; i < n ; ++i) {
            queue.add(new int[] {i , 1 << i});
        }
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size ; ++i) {
                int[] cur = queue.poll();
                int node = cur[0];
                int state = cur[1];
                if (state == target) return level;
                if (visited[node][state] == 1) continue;
                visited[node][state] = 1;
                for (int next : graph[node]) {
                    queue.add(new int[] {next , state | 1 << next});
                }
            }
            ++level;
        }
        return -1;
    }

    public static void main (String[] args) {
        ShortestPathVisitingAllNodes847 sp = new ShortestPathVisitingAllNodes847();
        //4
        System.out.println(sp.shortestPathLength(new int[][] {{1} , {0,2,4} , {1,3,4} , {2} , {1,2}}));
        //4
        System.out.println(sp.shortestPathLength(new int[][] {{1,2,3},{0},{0},{0}}));
    }
}
