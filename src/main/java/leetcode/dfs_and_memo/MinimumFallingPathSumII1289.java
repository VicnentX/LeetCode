package leetcode.dfs_and_memo;

/*
Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.



Example 1:

Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation:
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.


Constraints:

1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99
 */

import java.util.Arrays;

public class MinimumFallingPathSumII1289 {


    //这种方法会比较慢 是dp的题目这道
    private int ret = Integer.MAX_VALUE;

    public int minFallingPathSum(int[][] grid) {
        final int N = grid.length;
        int[][] record = new int[N][N];
        for (int[] row: record) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dfs(0, grid, record, -1, 0);
        return ret;
    }

    private void dfs(int row, int[][] grid, int[][] record, int preCol, int sum) {
        if (row == grid.length) {
            ret = Math.min(ret, sum);
            return;
        }

        if (row != 0) {
            if (sum >= record[row][preCol]) {
                return;
            } else {
                record[row][preCol] = sum;
            }
        }

        for (int i = 0; i < grid.length; ++i) {
            if (i != preCol) {
                dfs(row + 1, grid, record, i, sum + grid[row][i]);
            }
        }

    }

}
