package Amazon.full_time2020;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given an underected connected graph with n nodes labeled 1..n. A bridge (cut edge) is defined as an edge which, when removed, makes the graph disconnected (or more precisely, increases the number of connected components in the graph). Equivalently, an edge is a bridge if and only if it is not contained in any cycle. The task is to find all bridges in the given graph. Output an empty list if there are no bridges.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of pairs of integers representing the nodes connected by an edge.
 * Example 1:
 *
 * Input: n = 5, edges = [[1, 2], [1, 3], [3, 4], [1, 4], [4, 5]]
 *
 *
 * Output: [[1, 2], [4, 5]]
 * Explanation:
 * There are 2 bridges:
 * 1. Between node 1 and 2
 * 2. Between node 4 and 5
 * If we remove these edges, then the graph will be disconnected.
 * If we remove any of the remaining edges, the graph will remain connected.
 */

/*
The order of nodes is important!. If the input contains an edge [3, 1] you should return it as [3, 1], [1, 3] won't be accepted.
In C++ input is vector<pair<int, int>>.
In Java input is a list of custom pair object List<Pair>
 */

public class CriticalConnection {

    public static class Pair {
        int p1, p2;
        Pair(int i, int j) {
            p1 = i;
            p2 = j;
        }
    }

    private List<Pair> ret = new ArrayList<>();
    private final int NIL = -1;

    public class Graph {
        int v;
        ArrayList<Integer>[] adj;
        int time = 0;


        Graph(int v) {
            this.v = v;
            adj = new ArrayList[v];
            for (int i = 0 ; i < v ; ++i) {
                adj[i] = new ArrayList<>();
            }
        }

        void findCC(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
            visited[u] = true;
            disc[u] = low[u] = ++time;
            Iterator<Integer> iterator = adj[u].iterator();
            while (iterator.hasNext()) {
                int v = iterator.next();
                if (!visited[v]) {
                    parent[v] = u;
                    findCC(v, visited, disc, low, parent);
                    low[u] = Math.min(low[u], low[v]);
                    if (low[v] > disc[u]) {
                        ret.add(new Pair(u + 1, v + 1));
                    }
                } else if(v != parent[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }

    public List<Pair> findAllCriticalConnection(int n, List<Pair> list) {
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];

        Graph graph = new Graph(n);

        for (Pair pair: list) {
            int u = pair.p1;
            int v = pair.p2;
            graph.adj[u - 1].add(v - 1);
            graph.adj[v - 1].add(u - 1);
        }

        for (int i = 0; i < n; ++i) {
            parent[i] = NIL;
            visited[i] = false;
        }

        for (int i = 0; i < n; ++i) {
            if (visited[i] == false) {
                graph.findCC(i, visited, disc, low, parent);
            }
        }
        return new ArrayList<>(ret);
    }

    public static void main(String[] args) {
        CriticalConnection criticalConnection = new CriticalConnection();
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(1,2));
        list.add(new Pair(1,3));
        list.add(new Pair(3,4));
        list.add(new Pair(1,4));
        list.add(new Pair(4,5));
        List<Pair> ret = criticalConnection.findAllCriticalConnection(5, list);
        System.out.println(ret.size());
        for (Pair pair: ret) {
            System.out.println(pair.p1 + "  " + pair.p2);
        }
    }

}
