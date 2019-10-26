package google.google2020.google_加油少年_VO;

/*
给一个matrix，有0 1 -1，从[0][0]这个点开始，
找到-1的路径，要求返回一条最短路径，
-1的位置可能在任何位置,每次可以走8个方向
[1,0,0,1
0,1,1,0
1,-1,0,1]

 */

import java.util.ArrayList;
import java.util.List;

/**
 * 1 can be access , 0 can not.
 * 这题bfs会从逻辑上面快一点 从复杂度上面是一样的。
 * 两种都不难
 *
 *
 * clarification :
 * how many -1 in the grid ?
 * start point is always valid?
 *
 */

public class SearchNeg1in8Dirs {

    List<int[]> shortest = new ArrayList<>();

    public List<int[]> findShortestPath(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        dfs(0, 0, M, N, grid, new int[M][N], new ArrayList<>());
        return shortest;
    }

    private void dfs(int i, int j, final int M, final int N, int[][] grid, int[][] visited, List<int[]> path) {

        visited[i][j] = 1;
        path.add(new int[] {i , j});

        if (grid[i][j] == -1) {
            if (shortest.size() == 0 || path.size() < shortest.size()) {
                shortest = new ArrayList<>(path);
            }
            visited[i][j] = 0;
            path.remove(path.size() - 1);
            return;
        }

        int[][] dirs = new int[][] {{-1,0}, {-1,1}, {0,1}, {1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            /**
             * 记住这里的判断条件千万不能选 == 1 而是 ！= 0 ，不然永远到不了-1这个点
             */
            if(x >= 0 && x < M && y >= 0 && y < N && visited[x][y] == 0 && grid[x][y] != 0) {
                dfs(x, y, M, N, grid, visited, path);
            }
        }

        visited[i][j] = 0;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        SearchNeg1in8Dirs searchNeg1in8Dirs = new SearchNeg1in8Dirs();
        for (int[] pathNode:
                searchNeg1in8Dirs.findShortestPath(new int[][] {{1,0,0,1},{0,1,1,0},{1,-1,0,1}})) {
            System.out.println(pathNode[0] + " _ " + pathNode[1]);
        }
    }

}
