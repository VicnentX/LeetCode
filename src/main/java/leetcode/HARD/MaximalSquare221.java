package leetcode.HARD;

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
Seen this question in a real interview before?

 */

/*
To appy DP, we define the state as the maximal size (square = size * size) of the square that can be formed till point (i, j), denoted as dp[i][j].

For the topmost row (i = 0) and the leftmost column (j = 0), we have dp[i][j] = matrix[i][j] - '0', meaning that it can at most form a square of size 1 when the matrix has a '1' in that cell.

When i > 0 and j > 0, if matrix[i][j] = '0',
then dp[i][j] = 0 since no square will be able to contain the '0' at that cell.
If matrix[i][j] = '1',
 we will have dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1,
 which means that the square will be limited
 by its left, upper and upper-left neighbors.
 */

public class MaximalSquare221 {
    public int maximalSquare(char[][] matrix) {
        int ret = 0;
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1 ; i <= m ; ++i) {
            for (int j = 1 ; j <= n ; ++j) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j] , Math.min(dp[i][j - 1] , dp[i - 1][j - 1])) + 1;
                    ret = Math.max(ret , dp[i][j]);
                }
            }
        }
        return ret * ret;
    }

    public static void main (String[] args) {
        MaximalSquare221 ms = new MaximalSquare221();
        System.out.println(ms.maximalSquare(new char[][] {{'1' , '1' , '1'} , {'1' , '1' , '1'}}));
    }
}
