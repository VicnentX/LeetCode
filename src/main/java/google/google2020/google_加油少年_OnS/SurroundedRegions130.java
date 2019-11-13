package google.google2020.google_加油少年_OnS;

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border,
which means that any 'O' on the border of the board are not flipped to 'X'.
Any 'O' that is not on the border and
it is not connected to an 'O' on the border will be flipped to 'X'.
Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

public class SurroundedRegions130 {
    //假设1包围0
    public int[][] solve(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if ((i == 0 || i == M - 1 || j == 0 || j == N - 1) && grid[i][j] == 0) {
                    dfs(i, j, M, N, grid);
                }
            }
        }
        //convert 0 to 1 , 2 to 0
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                } else if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                }
            }
        }

        return grid;
    }

    private void dfs(int i, int j, final int M, final int N, int[][] grid) {
        grid[i][j] = 2;
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 0) {
                dfs(x, y, M, N, grid);
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions130 surroundedRegions130 = new SurroundedRegions130();
        int[][] ret = surroundedRegions130.solve(new int[][] {{1,1,1,1},{1,0,0,1},{1,1,0,1},{1,0,1,1}});
        for (int[] line: ret) {
            for (int i: line) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
