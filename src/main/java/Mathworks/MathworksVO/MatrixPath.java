package Mathworks.MathworksVO;

/*
我遇见的题是matrix从左上到右下记录不同path数量的那道题，最后的输出要求modulo，用dp的话一定要用long，同时每一步相加的时候modulo，否则会overflow，就这样
1. 在0 1matrix里面找从左上走到右下角 0是block 1是能走的 一共有多少条path 要分析time和space complexity

 */

public class MatrixPath {

    private int pathCnt = 0;
    int[][] dirs = new int[][] {{1,0}, {0, -1}, {-1, 0}, {0, 1}};

    public int countAllPath(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        dfs(grid, 0, 0, M - 1, N - 1, new int[M][N], M, N);
        return pathCnt;
    }

    private void dfs(int[][] grid, int curX, int curY, int endX, int endY, int[][] visited, final int M, final int N) {
        visited[curX][curY] = 1;

        if (curX == endX && curY == endY) {
            pathCnt++;
            visited[curX][curY] = 0;
            return;
        }

        for (int[] dir: dirs) {
            int i = curX + dir[0];
            int j = curY + dir[1];
            if (i >= 0 && i < M && j >= 0 && j < N && grid[i][j] == 1 && visited[i][j] == 0) {
                dfs(grid, i, j, endX, endY, visited, M, N);
            }
        }

        visited[curX][curY] = 0;
    }

    public static void main(String[] args) {
        MatrixPath matrixPath = new MatrixPath();
        //7
        System.out.println(matrixPath.countAllPath(new int[][] {{1,1,1}, {1,1,1}, {0,1,1}}));
    }

}
