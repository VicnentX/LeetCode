package leetcode.dp;

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


import java.util.*;
public class MinimumPathSum64 {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        int sum = 0;
        for (int i = 1 ; i <= n ; ++i) {
            sum += grid[0][i - 1];
            dp[i] = sum;
        }
        for (int i = 1 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                dp[j + 1] = Math.min(dp[j + 1] + grid[i][j] , dp[j] + grid[i][j]);
            }
        }
        return dp[n];
    }

    public static void main (String[] args) {
        MinimumPathSum64 mp = new MinimumPathSum64();
        System.out.println(mp.minPathSum(new int[][] {{1,3,1},{1,5,1},{4,2,1}}));
    }
}
