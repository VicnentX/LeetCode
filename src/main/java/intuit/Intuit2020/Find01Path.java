package intuit.Intuit2020;

import java.util.ArrayList;
import java.util.List;

public class Find01Path {

    private List<int[]> shortestPath = new ArrayList<>();
/*
都是01矩阵的问题，一共三道题，每道题都要分析时空复杂度。其中0表示可以走，-1表示墙不可以走。

第一题：给定一个点，返回矩阵中当前点上下左右的valid点。很简单
第二题：给定一个终点，问矩阵中所有0的点能不能走到终点。BFS解决
第三题：给定起点和终点，1表示treasure，找到一条从起点到终点且包含所有treasur‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍e的最短路。dfs
解决

 */

    /**
     * 都是01矩阵的问题，一共三道题，每道题都要分析时空复杂度。其中0表示可以走，-1表示墙不可以走。
     *
     * 第一题：给定一个点，返回矩阵中当前点上下左右的valid点。很简单
     *
     * @param i
     * @param j
     * @param grid
     * @return
     */

    private int[][] dirs = new int[][] {{1,0}, {0, -1}, {-1,0}, {0,1}};

    public List<int[]> findSurround0s(int i, int j, int[][] grid) {
        List<int[]> validPointList = new ArrayList<>();
        final int M = grid.length;
        final int N = grid[0].length;

        for (int[] dir: dirs) {
            // x, y describe the position of a point
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < M && y >= 0 && y <= N && grid[x][y] == 0) {
                validPointList.add(new int[] {x,y});
            }
        }

        return validPointList;
    }


    /**
     * bfs
     */
/*
第二题：给定一个终点，问矩阵中所有0的点能不能走到终点。BFS解决
 */

    public boolean isAll0sConnected(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    dfs(i, j, grid, M , N);
                }
            }
        }

        //check if there is extra 0 in the grid
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfs(int i, int j, int[][] grid, final int M, final int N) {
        grid[i][j] = 1;
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 0) {
                dfs(x, y, grid, M, N);
            }
        }
    }


    /**
     * this method is use dfs to find all the path and
     * also check out if this is valid and shortest
     */
/*
第三题：给定起点和终点，1表示treasure，找到一条从起点到终点且包含所有treasur‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍e的最短路。dfs
解决
 */

    public List<int[]> findShortestPath(int[] start, int[] end, int[][] grid) {

        final int M = grid.length;
        final int N = grid[0].length;
        //check how how many treasure there is , excluding start and end;
        int treasureCnt = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    treasureCnt++;
                }
            }
        }

        //find path and update shortestPath;
        dfs(start[0], start[1], grid, end[0], end[1],new int[M][N], treasureCnt, new ArrayList<>());
        return shortestPath;
    }

    private void dfs(int x, int y, int[][] grid, int endX, int endY,
                     int[][] visited, int remainTreasure, List<int[]> path) {
        //add to path and mark
        path.add(new int[] {x, y});
        visited[x][y] = 1;

        //get treasure if there is a treasure at xy
        if (grid[x][y] == 1) {
            remainTreasure--;
        }

        //if this point is end
        if (x == endX && y == endY) {
            if (remainTreasure == 0) {
                if (shortestPath.size() == 0 || path.size() < shortestPath.size()) {
                    shortestPath = new ArrayList<>(path);
                }
            }
            visited[x][y] = 0;
            path.remove(path.size() - 1);
            return;
        }

        for (int[] dir: dirs) {
            // x, y describe the position of a point
            int neighborX = x + dir[0];
            int neighborY = y + dir[1];
            if (neighborX >= 0 && neighborX < grid.length && neighborY >= 0 && neighborY < grid[0].length && grid[neighborX][neighborY] != -1 && visited[neighborX][neighborY] == 0) {
                dfs(neighborX, neighborY, grid, endX, endY, visited, remainTreasure , path);
            }
        }
        visited[x][y] = 0;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Find01Path treasureSearch = new Find01Path();

        List<int[]> q1result = treasureSearch.findSurround0s(1,1,new int[][]{{1,0,1}, {0,1,0}, {1,0,1}});
        for (int[] point: q1result) {
            System.out.println(point[0] + " " + point[1]);
        }

        //System.out.println(treasureSearch.isAll0sConnected(new int[][]{{1,1,1,1}, {0,0,0,0}, {0,0,1,1}, {0,1,1,1}}));



        List<int[]> ret_invalid = treasureSearch.findShortestPath(new int[] {0,0}, new int[]{2,2}, new int[][]{
                {1,1,1},
                {1,-1,1},
                {-1,-1,1}
        });
        for (int[] point: ret_invalid) {
            System.out.print(point[0] + "_" + point[1] + " + ");
        }
        System.out.println();

        //5 step
        List<int[]> ret_valid1 = treasureSearch.findShortestPath(new int[] {0,0}, new int[]{2,2}, new int[][]{
                {1,1,1},
                {0,0,1},
                {-1,-1,1}
        });
        System.out.println(ret_valid1.size());
        for (int[] point: ret_valid1) {
            System.out.print(point[0] + "_" + point[1] + " + ");
        }
        System.out.println();

        treasureSearch.shortestPath.clear();

        //7 step
        List<int[]> ret_valid2 = treasureSearch.findShortestPath(new int[] {0,0}, new int[]{2,2}, new int[][]{
                {1,1,1},
                {1,0,1},
                {-1,-1,1}
        });
        System.out.println(ret_valid2.size());
        for (int[] point: ret_valid2) {
            System.out.print(point[0] + "_" + point[1] + " + ");
        }
        System.out.println();
    }

}
