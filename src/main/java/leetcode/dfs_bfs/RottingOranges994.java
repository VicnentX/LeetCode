package leetcode.dfs_bfs;

/*
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.



Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
 */

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges994 {
    public int orangesRotting(int[][] grid) {
        int[][] dirs = new int[][] {{1,0},{0,-1},{-1,0},{0,1}};
        int ret = -1;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        if (fresh == 0) return 0;

        while (!queue.isEmpty()) {
            ret++;
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                int[] cur = queue.remove();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir: dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length && grid[xx][yy] == 1) {
                        grid[xx][yy] = 2;
                        fresh--;
                        queue.add(new int[] {xx, yy});
                    }
                }
            }
        }
        System.out.println(fresh);
        return fresh == 0 ? ret : -1;
    }

    public static void main(String[] args) {
        RottingOranges994 rottingOranges994 = new RottingOranges994();
        System.out.println(rottingOranges994.orangesRotting(new int[][] {{2,1,1},{1,1,0},{0,1,1}}));
    }
}
