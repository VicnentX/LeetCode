package leetcode.dp;

/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.



Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:



Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
 */

public class OutofBoundaryPaths576 {
    public int findPaths(int m, int n, int N, int x, int y) {
        int kmod = 1000000007;
        int[][][] dp = new int[N + 1][m + 2][n + 2];
        dp[0][x + 1][y + 1] = 1;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int ret = 0;
        for (int k = 1; k <= N; ++k) {
            for (int i = 0; i < m + 2; ++i) {
                for (int j = 0; j < n + 2; ++j) {
                    for (int[] dir : dirs) {
                        int xx = i + dir[0];
                        int yy = j + dir[1];
                        if (xx >= 1 && xx <= m && yy >= 1 && yy <= n) {
                            dp[k][i][j] = (dp[k][i][j] + dp[k - 1][xx][yy]) % kmod;
                        }
                    }
                    if (i == 0 || i == m + 1 || j == 0 || j == n + 1) {
                        ret = (ret + dp[k][i][j]) % kmod;
                    }
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        OutofBoundaryPaths576 ob = new OutofBoundaryPaths576();
        System.out.println(ob.findPaths(5, 5, 4, 1, 1));
    }
}
