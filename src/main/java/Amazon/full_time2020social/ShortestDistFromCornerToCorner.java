package Amazon.full_time2020social;


import java.util.LinkedList;
import java.util.Queue;

/*
第二题  有n‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍ x n 的2d array 左上走到右下 中间一些地方不能走 问你最短路径
 */
public class ShortestDistFromCornerToCorner {


    /**
     * 这种算法用了bfs 当然dfs也是可以的
     * every point will be access at most most once so that the time complexity is O(mn)
     * the space complexity depends on the size of the queue. so due to the number of point, it is O(mn)
     */
    public int getShortestDis(int[][] grid) {
        int[][] dirs = new int[][] {{0,-1},{1,0},{0,1},{-1,0}};
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] visited = new int[M][N];
        int level = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0});
        visited[0][0] = 1;

        OUT:
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.remove();
                int x = cur[0];
                int y = cur[1];

                if (x == M - 1 && y == N - 1) {
                    break OUT;
                }

                //add its neighbor if possible
                for(int[] dir: dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx >= 0 && xx < M && yy >= 0 && yy < N && grid[xx][yy] == 0 && visited[xx][yy] == 0) {
                        queue.add(new int[] {xx, yy});
                        visited[xx][yy] = 1;
                    }
                }
            }
            level++;
        }

        return level;
    }
}
