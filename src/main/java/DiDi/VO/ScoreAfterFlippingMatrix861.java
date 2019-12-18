package DiDi.VO;

/*
We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.



Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39


Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
 */

/**
 * Assume A is M * N.
 *
 * A[i][0] is worth 1 << (N - 1) points, more than the sum of (A[i][1] + .. + A[i][N-1]).
 * We need to toggle all A[i][0] to 1, here I toggle all lines for A[i][0] = 0.
 * A[i][j] is worth 1 << (N - 1 - j)
 * For every col, I count the current number of 1s.
 * After step 1, A[i][j] becomes 1 if A[i][j] == A[i][0].
 * if M - cur > cur, we can toggle this column to get more 1s.
 * max(M, M - cur) will be the maximum number of 1s that we can get.
 * Time Complexity:
 * O(MN)
 */
public class ScoreAfterFlippingMatrix861 {
    public int matrixScore(int[][] A) {
        int M = A.length, N = A[0].length, res = (1 << (N - 1)) * M;
        for (int j = 1; j < N; j++) {
            int cur = 0;
            for (int i = 0; i < M; i++) cur += A[i][j] == A[i][0] ? 1 : 0;
            res += Math.max(cur, M - cur) * (1 << (N - j - 1));
        }
        return res;
    }
}
