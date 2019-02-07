package leetcode.dfs_bfs;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example:

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
Seen this question in a real interview before?

 */
import java.util.*;
public class WallsandGates286 {
    public void wallsAndGates(int[][] nums) {
        int[][] dirs = {{1 , 0} , {-1 , 0} , {0 , 1} , {0 , -1}};
        if (nums.length == 0 || nums[0].length == 0) return ;
        int m = nums.length;
        int n = nums[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (nums[i][j] == 0) {
                    queue.add(new int[] {i , j});
                }
            }
        }
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size ; ++i) {
                //int[] cur = queue.poll();
                expand(queue.poll() , nums , m , n , dirs , level , queue);
            }
            ++level;
        }
        return ;
    }
    private void expand (int[] cur , int[][] nums , int m , int n , int[][] dirs , int level , Queue<int[]> queue) {
        for (int[] dir : dirs) {
            int i = cur[0] + dir[0];
            int j = cur[1] + dir[1];
            if (i < 0 || i >= m || j < 0 || j >= n || nums[i][j] != Integer.MAX_VALUE) continue;
            nums[i][j] = level;
            queue.add(new int[] {i , j});
        }
    }
}
