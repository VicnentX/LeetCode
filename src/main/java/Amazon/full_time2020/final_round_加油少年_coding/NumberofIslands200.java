package Amazon.full_time2020.final_round_加油少年_coding;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */


/**
 * dfs
 */
public class NumberofIslands200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        final int M = grid.length;
        final int N = grid[0].length;
        int numOfIsland = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j, M, N, grid);
                    numOfIsland++;
                }
            }
        }
        return numOfIsland;
    }

    private void dfs(int i, int j, final int M, final int N, char[][] grid) {
        grid[i][j] = '2';
        int[][] dirs = new int[][]{{0,-1}, {-1,0}, {0, 1}, {1, 0}};
        for (int[] dir:dirs) {
            int neighborX = i + dir[0];
            int neighborY = j + dir[1];
            if (neighborX >= 0 && neighborX < M && neighborY >= 0 && neighborY < N && grid[neighborX][neighborY] == '1') {
                dfs(neighborX, neighborY, M, N, grid);
            }
        }
    }
}
