package leetcode.dfs_bfs;

/*
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).



Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]


Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
Seen this question in a real interview before?

 */

/**
 * Idea
 *
 * The basic idea is to do BFS and adding children from the opposite color for the curr explored node. This will ensure only the valid paths are added to q (we add <node#, incoming-edge-color-to-node> to q). So there is not need to keep track of valid paths or their lengths.
 *
 * The tricky part is to figure out avoiding loops in the BFS. Simply, skipping the visited nodes will also skip other valid paths. But if we do not skip visited nodes, obviously cycles will be a problem.
 *
 * That is why, seen maintains visited nodes with an edge color that it came from. That way, we can avoid cycles and still explore red edge adding a node and blue edge adding the same node.
 */

import java.util.*;

//1=red, 2=blue, 0=root-edge (special case)

public class ShortestPathwithAlternatingColors1129 {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<Integer>[] red_path = new ArrayList[n];
        List<Integer>[] blue_path = new ArrayList[n];
        for (int[] e : red_edges) {
            if (red_path[e[0]] == null) {
                red_path[e[0]] = new ArrayList<>();
            }
            red_path[e[0]].add(e[1]);
        }
        for (int[] e : blue_edges) {
            if (blue_path[e[0]] == null) {
                blue_path[e[0]] = new ArrayList<>();
            }
            blue_path[e[0]].add(e[1]);
        }

        Queue<int[]> q = new LinkedList<>();
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        //这里解释一下 第一个数代表点的序号， 第二个数字代表通过什么颜色的路径抵达的这个点
        q.add(new int[] {0, 0});

        int step = 0;
        Set<String> vistied = new HashSet<>();

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0 ; i < size ; ++i) {
                int[] cur = q.poll();
                String path_record = cur[0] + "fromColor" + cur[1];
                if (vistied.contains(path_record)) continue;
                vistied.add(path_record);
                if (ret[cur[0]] == -1) {
                    ret[cur[0]] = step;
                }
                if (cur[1] == 1 || cur[1] == 0) {
                    if (blue_path[cur[0]] != null) {
                        for (int next : blue_path[cur[0]]) {
                            q.add(new int[] {next , 2});
                        }
                    }
                }
                if (cur[1] == 2 || cur[1] == 0) {
                    if (red_path[cur[0]] != null) {
                        for (int next : red_path[cur[0]]) {
                            q.add(new int[] {next , 1});
                        }
                    }
                }
            }
            ++step;
        }
        return ret;
    }

    public static void main(String[] args) {
        ShortestPathwithAlternatingColors1129 sp = new ShortestPathwithAlternatingColors1129();
       int[] result = sp.shortestAlternatingPaths(3 , new int[][] {{0,1}} , new int[][] {{1,2}});
       for (int i : result) {
           System.out.print(i + " ");
       }

    }
}
