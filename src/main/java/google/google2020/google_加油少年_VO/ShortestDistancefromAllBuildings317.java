package google.google2020.google_加油少年_VO;

/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

Seen this question in a real interview before?

 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这题就是从每个1的点出发 bfs找到所有（到不了的话就是部分）0s的距离
 * 我要几个举证
 * canReach 代表有几个点可以到
 * disSum 代表所有（或者部分1）到这点的距离之和
 * 每次bfs都生成一个新的visited
 */

public class ShortestDistancefromAllBuildings317 {

    private int[][] canReach, disSum;

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        final int M = grid.length;
        final int N = grid[0].length;
        canReach = new int[M][N];
        disSum = new int[M][N];
        int OnesCnt = 0;

        //fill canReach and disSum
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    OnesCnt++;
                    bfs(new int[] {i, j}, grid, M, N);
                }
            }
        }

        //check ret
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0 && canReach[i][j] == OnesCnt && disSum[i][j] < min) {
                    min = disSum[i][j];
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void bfs(int[] enterPoint, int[][] grid, final int M, final int N) {
        int[][] visited = new int[M][N];
        int[][] dirs = new int[][] {{0,-1},{0,1},{1,0},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(enterPoint);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.remove();
                int x = cur[0];
                int y = cur[1];
                //update canreach and disSum
                canReach[x][y] ++;
                disSum[x][y] += level;

                for (int[] dir: dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx >= 0 && xx < M && yy >= 0 && yy < N && grid[xx][yy] == 0 && visited[xx][yy] == 0) {
                        visited[xx][yy] = 1;
                        queue.add(new int[] {xx, yy});
                    }
                }
            }
            level++;
        }
    }
}
