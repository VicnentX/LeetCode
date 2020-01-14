package leetcode.dp;

/*
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.


Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
 */

public class MatrixBlockSum1314 {
    public int[][] matrixBlockSum(int[][] grid, int k) {
        //dp
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        //init dp[][]
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = grid[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1]
                        - dp[i - 1][j - 1];
            }
        }
        //get result
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int top = i - k - 1 >= 0 ? i - k - 1 : 0;
                int bottom = i + k <= m ? i + k : m;
                int left = j - k - 1 >= 0 ? j - k - 1 : 0;
                int right = j + k <= n ? j + k : n;
                grid[i - 1][j - 1] = dp[bottom][right]
                        - dp[bottom][left] - dp[top][right] + dp[top][left];
            }
        }
        return grid;
    }
}
