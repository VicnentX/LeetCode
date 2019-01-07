package leetcode.dfs_bfs;

/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
Seen this question in a real interview before?

 */

/*
[[2,1,1],[2,3,1],[3,4,1]]
4
2
[[1,2,1],[2,3,7],[1,3,4],[2,1,2]]
3
1
[[1,5,66],[3,5,55],[4,3,29],[1,2,9],[3,4,10],[3,1,3],[2,3,78],[1,4,98],[4,5,21],[5,2,19],[5,1,76],[4,1,65],[3,2,27],[5,3,23],[5,4,12],[2,1,36],[4,2,75],[2,4,11],[1,3,30],[2,5,8]]
5
1
--------
2
4
30
 */

import java.util.*;

public class NetworkDelayTime743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        if (times.length < n - 1) return -1;
        int[] ret = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            ret[i] = Integer.MAX_VALUE;
        }

        Map<Integer , List<int[]>> map = new HashMap<>();
        for (int[] pair: times) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0] , new ArrayList<>());
            }
            map.get(pair[0]).add(new int[] {pair[1] , pair[2]});
        }
        dfs(map , ret , k , 0 );
        int min = 0;
        for (int i = 0 ; i < n ; ++i) {
            min = Math.max(min , ret[i]);
        }
        return min;
    }

    private void dfs (Map<Integer , List<int[]>> map , int[] ret , int sourse , int len ) {
        if (len >= ret[sourse - 1]) return;
        ret[sourse - 1] = len;
        if (map.containsKey(sourse)) {
            for (int[] destAndLen : map.get(sourse)) {
                dfs(map , ret , destAndLen[0] , len + destAndLen[1]);
            }
        }
    }

    public static void main (String[] args) {
        NetworkDelayTime743 nd = new NetworkDelayTime743();
        System.out.println(nd.networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}} , 4 ,2));
    }
}
