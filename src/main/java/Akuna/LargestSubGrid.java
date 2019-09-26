package Akuna;

/**
 * consider a squeare filled with integer values, it can be divided into
 * square sub girds having a row and col count from 1 to the size of the initial grid. all of the
 * elements of a sub-grid can be added together to get a sub-grid sum.
 * determine the maximum size square sub-grid that has a sum less than or equal to a given integer value.
 * Return the number of rows in that sub-grid
 */

/**
 * dp
 */

public class LargestSubGrid {
    public int findlargestSubGrid(int[][] grid, int maxSum) {
        final int M = grid.length;
        final int N = grid[0].length;
        if (M != N) return 0;

        int[][] dp = new int[M + 1][N + 1];
        int[] ret = new int[M];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                dp[i + 1][j + 1] = grid[i][j] +  dp[i + 1][j] + dp[i][j + 1] - dp[i][j];
            }
        }
        for (int len = 1; len <= M; ++len) {
            int max = Integer.MIN_VALUE;
            for (int i = len; i < dp.length; ++i) {
                for (int j = len; j < dp.length; ++j) {
                    max = Math.max(max, dp[i][j] - dp[i - len][j] - dp[i][j - len] + dp[i - len][j - len]);
                }
            }
            ret[len - 1] = max;
        }

        for (int i = ret.length - 1; i >=0 ; --i) {
            if (ret[i] <= maxSum) return i + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        LargestSubGrid largestSubGrid = new LargestSubGrid();
        //3
        System.out.println(largestSubGrid.findlargestSubGrid(new int[][] {{2,2,2}, {3,3,3}, {4,4,4}}, 28));
        //2
        System.out.println(largestSubGrid.findlargestSubGrid(new int[][] {{2,2,2}, {3,3,3}, {4,4,4}}, 14));
        //1
        System.out.println(largestSubGrid.findlargestSubGrid(new int[][] {{2,2,2}, {3,3,3}, {4,4,4}}, 4));
        //0
        System.out.println(largestSubGrid.findlargestSubGrid(new int[][] {{2,2,2}, {3,3,3}, {4,4,4}}, 3));
    }
}
