package Mathworks.MathworksVO;

/**
 * this is DP problem
 *
 */

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

public class MinimumPathSum64 {
    // space is O(mn)
    //dp[i][j] mean the min step needed to reach grid[i -1][j - 1]
    public int minPathSum(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i < M + 1; ++i) dp[i][0] = Integer.MAX_VALUE;
        for (int j = 0; j < N + 1; ++j) dp[0][j] = Integer.MAX_VALUE;

        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (i == 1 && j == 1) dp[i][j] = grid[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[M][N];
    }

    public int minPathSumO1space(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        int[] dp = new int[N + 1];
        for (int i = 0; i < N + 1; ++i) dp[i] = Integer.MAX_VALUE;

        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (i == 1 && j == 1) {
                    dp[j] = grid[i - 1][j - 1];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i - 1][j - 1];
                }
            }
        }

        return dp[N];
    }

    public static void main(String[] args) {
        MinimumPathSum64 minimumPathSum64 = new MinimumPathSum64();
        System.out.println(minimumPathSum64.minPathSum(new int[][] {{1,3,1}, {1,5,1}, {4,2,1}}));
        System.out.println(minimumPathSum64.minPathSumO1space(new int[][] {{1,3,1}, {1,5,1}, {4,2,1}}));
    }
}
