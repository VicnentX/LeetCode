package leetcode.dfs_bfs;


/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.



Example 1:



Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation:
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
Example 2:



Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation:
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.


Note:

0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 10
 */

import java.util.*;

public class CampusBikesII1066 {
    private int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;
        dfs(new int[n] , workers , bikes , m , n , 0 , 0);
        return min;
    }

    private void dfs(int[] visited , int[][] workers , int[][] bikes , int m , int n , int TotalDis , int level) {
        if (TotalDis >= min) return;
        if (level == m) {
            min = Math.min(min , TotalDis);
            return;
        }
        for (int i = 0 ; i < n ; ++i) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(visited , workers , bikes , m , n , TotalDis + dis(workers[level] , bikes[i]) , level + 1);
                visited[i] = 0;
            }
        }
    }

    private int dis(int[] a , int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main (String[] args) {
        CampusBikesII1066 cb = new CampusBikesII1066();
        System.out.println("总距离如下所示: ");
        System.out.println(cb.assignBikes(new int[][] {{0,0},{2,1}} , new int[][] {{1,2} , {3,3}}));
    }
}
