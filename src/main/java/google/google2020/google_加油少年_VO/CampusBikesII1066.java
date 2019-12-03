package google.google2020.google_加油少年_VO;

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

public class CampusBikesII1066 {
    /**
     * The DFS solution is pretty straight forward, try assign each bike to each worker.
     * Time Complexy: O(n * m !), n is number of workers, m is number of bikes
     *
     * Ususally, when input size <= 10, O(n!) can be accepeted. When input size <= 12, we probably need do some pruning. if the test case is not strong, or problem designer wants to allow this techonolgy (dfs + pruning) to pass. we can luckly get a AC.(For my experenice in LeetCode, when problem is tagged as Medium, this kind solution can be passed)
     *
     * For this problem, we add a very simple but effective condition:
     * @param workers
     * @param bikes
     * @return
     */
    private int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(new boolean[bikes.length], workers, 0, bikes, 0);
        return min;
    }
    public void dfs(boolean[] visit, int[][] workers, int i, int[][] bikes, int distance) {
        if (i >= workers.length) {
            min = Math.min(distance, min);
            return ;
        }
        if (distance > min) {
            return ;
        }
        for (int j = 0; j < bikes.length; j++) {
            if (!visit[j]) {
                visit[j] = true;
                dfs(visit, workers, i + 1, bikes, distance + dis(bikes[j], workers[i]));
                visit[j] = false;
            }
        }

    }
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }


    /**
     * perfect DP
     */
    public int assignBikesBianry(int[][] workers, int[][] bikes) {
        int[] dp = new int[1 << bikes.length];
        return dfs(workers, bikes, 0, 0, dp);
    }

    private int dfs(int[][] w, int[][] b, int workIndex, int state, int[] dp) {
        if (workIndex == w.length) return 0;
        if (dp[state] > 0) return dp[state];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < b.length; i++) {
            //have intersection
            if ((state & (1 << i)) != 0) continue;
            //have no intersection
            res = Math.min(res, Math.abs(w[workIndex][0] - b[i][0]) + Math.abs(w[workIndex][1] - b[i][1])
                    + dfs(w,b,workIndex + 1, state | (1<<i), dp));
        }
        return dp[state] = res;
    }
}
