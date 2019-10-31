package google.google2020.google_加油少年_OnS;

/*
m*n的matrix，0代表白色，1代表灰色.
找出所有白色正方形的数量。[[0 ,0 ,0], [0, 0, 0], [0,0,1]]
返回 11. 有8个边长为1 的，三个边长为2的。
我白板写完之后还有很多时间，就在chromebook上又写了一遍
 */

/**
 * dp[i][j] 代表在以i, j这一格为右下角的正方形边长。
 * 如果这一格的值也是1，那这个正方形的边长就是他的上面，左手边，和斜上的值的最小边长 +1。
 * 因为如果有一边短了缺了，都构成不了正方形。
 */
public class MatrixCnt {
    private int getSqaureCnt(int[][] grid) {
        //fill dp
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (grid[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        //get the result
        int allSquareCnt = 0;
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                allSquareCnt += dp[i][j];
            }
        }

        return allSquareCnt;
    }

    public static void main(String[] args) {
        MatrixCnt matrixCnt = new MatrixCnt();
        System.out.println(matrixCnt.getSqaureCnt(new int[][] {{0,0,0},{0,0,0},{0,0,1}}));
    }
}
