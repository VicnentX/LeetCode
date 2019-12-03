package google.google2020.google_加油少年_ResidentVO;

/*
然后是一个迷宫问题，其实应该很简单，但是一开始节奏不对，
自乱了马脚。面试官迟到 5 分钟就不想吐槽什么了。

给一个二维数组 maze[][]，0 代表通路，1 代表墙，
给你起始位置和终止位置，随便输出一条从起始位置到终止位置的路径。
 */

import java.util.ArrayList;
import java.util.List;

public class AnyReachablePathInMaze {

    private int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public List<int[]> solve(int[][] grid, int[] start, int[] end) {
        final int m = grid.length;
        final int n = grid[0].length;
        return dfs(start[0], start[1], grid, end[0], end[1], new int[m][n], m, n);
    }

    private List<int[]> dfs(int i, int j, int[][] grid, final int endX, final int endY, int[][] visited, final int m, final int n) {

        visited[i][j] = 1;

        if (i == endX && j == endY) {
            List<int[]> list = new ArrayList<>();
            list.add(new int[] {i, j});
            visited[i][j] = 0;
            return list;
        }

        for(int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && visited[x][y] == 0 && grid[x][y] == 0) {
                List<int[]> second = dfs(x, y , grid, endX, endY, visited, m, n);
                if (second.size() > 0) {
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[] {i, j});
                    list.addAll(new ArrayList<>(second));
                    visited[i][j] = 0;
                    return list;
                }
            }
        }

        //if i am here, there is no solution for this case
        visited[i][j] = 0;
        System.out.println("IMPOSSIBLE");
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        AnyReachablePathInMaze anyReachablePathInMaze = new AnyReachablePathInMaze();
        for(int[] point: anyReachablePathInMaze.solve(new int[][] {{0,0,0},{1,1,0},{0,0,0}}, new int[] {0,0}, new int[] {2,2})) {
            System.out.println(point[0] + " " + point[1]);
        }
    }
}
