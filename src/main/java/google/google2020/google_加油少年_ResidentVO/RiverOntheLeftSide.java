package google.google2020.google_加油少年_ResidentVO;

/*
一个N*N的沙盘（矩阵），每个格子有一个数字代表格子的高度，
沙盘左边有一条河，河的高度已知为H，
求被淹没后的沙盘的样子（被淹没的地方的高度变成河的高度）
 */

public class RiverOntheLeftSide {

    public int[][] solve(int[][] grid, int riverHeight) {
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] visited = new int[M][N];
        for (int i = 0; i < M; ++i) {
            if (riverHeight > grid[i][0]) {
                dfs(i, 0, M, N, grid, visited, riverHeight);
            }
        }
        return grid;
    }

    private int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

    private void dfs(int i, int j, final int M, final int N, int[][] grid, int[][] visited, int riverHight) {
        visited[i][j] = 1;
        grid[i][j] = riverHight;
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N && visited[x][y] == 0 && grid[x][y] < riverHight) {
                dfs(x, y, M, N, grid, visited, riverHight);
            }
        }
    }

    public static void main(String[] args) {
        RiverOntheLeftSide riverOntheLeftSide = new RiverOntheLeftSide();
        int[][] grid = riverOntheLeftSide.solve(new int[][] {{1,5,1},{5,4,4},{3,1,1}}, 4);
        for (int[] rows: grid) {
            for (int i: rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
