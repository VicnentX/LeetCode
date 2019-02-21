package leetcode.dfs_bfs;

/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
 */

import java.util.*;

public class CheapestFlightsWithinKStops787 {
    public int findCheapestPrice(int n, int[][] flights, int start, int end, int k) {
        if (start == end) return 0;
        if (flights.length == 0) return -1;

        //store minimum sum of price to index i
        int[] visited = new int[n];
        Arrays.fill(visited , Integer.MAX_VALUE);
        //map (depart , list of (dest , price))
        Map<Integer , List<int[]>> map = new HashMap<>();
        for (int[] num : flights) {
            map.putIfAbsent(num[0] , new ArrayList<>());
            map.get(num[0]).add(new int[] {num[1] , num[2]});
        }
        //queue element is (current site , minimum sum of price to this site)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start , 0});
        //level is the amount of stops that flight has made
        int level = 0;
        //ret is the result for the problem
        int ret = Integer.MAX_VALUE;

        while (!queue.isEmpty() && level <= k) {
            int size = queue.size();
            for (int i = 0 ; i < size ; ++i) {
                int[] cur = queue.poll();
                if (map.containsKey(cur[0])) {
                    for (int[] next : map.get(cur[0])) {
                        int sumPrice = cur[1] + next[1];
                        if (next[0] == end) {
                            ret = Math.min(ret , sumPrice);
                            continue;
                        }
                        if (sumPrice < visited[next[0]]) {
                            visited[next[0]] = sumPrice;
                            queue.add(new int[] {next[0] , sumPrice});
                        }
                    }
                }
            }
            ++level;
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public static  void main (String[] args) {
        CheapestFlightsWithinKStops787 cf = new CheapestFlightsWithinKStops787();
        //output 500
        System.out.println(cf.findCheapestPrice(3 , new int[][] {{0,1,100} , {1,2,100} , {0,2,500}} , 0 , 2 , 0));
        //output 200
        System.out.println(cf.findCheapestPrice(3 , new int[][] {{0,1,100} , {1,2,100} , {0,2,500}} , 0 , 2 , 1));
    }
}
