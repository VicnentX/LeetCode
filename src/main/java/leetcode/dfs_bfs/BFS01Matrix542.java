package leetcode.dfs_bfs;

/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1:
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2:
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 */

import java.util.*;

public class BFS01Matrix542 {
    private Queue<int[]> queue = new LinkedList<>();
    private int[][] dirs = new int[][]{{-1 , 0} , {1 , 0} , {0 , -1} , {0 , 1}};
    public int[][] updateMatrix1(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[] {i , j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int level = 1;
        while (!queue.isEmpty()) {
            Queue<int[]> tem = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                findNextLevel(point[0] , point[1] , matrix , m , n , tem , level);
            }
            queue = tem;
            ++level;
        }
        return matrix;
    }

    private void findNextLevel (int i , int j , int[][] matrix , int m , int n , Queue<int[]> tem , int level) {
        for (int[] dir : dirs) {
            if (i + dir[0] >= 0 && i + dir[0] < m && j + dir[1] >= 0 && j + dir[1] < n) {
                if (matrix[i + dir[0]][j + dir[1]] == Integer.MAX_VALUE) {
                    matrix[i + dir[0]][j + dir[1]] = level;
                    tem.add(new int[] {i + dir[0] , j + dir[1]});
                }
            }
        }
    }

    //BFS
    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[] {i , j});
                }
            }
        }
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size ; ++i) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    findNext(cur[0] + dir[0] , cur[1] + dir[1] , matrix , queue , level);
                }
            }
            ++level;
        }

        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                matrix[i][j] = -matrix[i][j];
            }
        }
        return matrix;
    }
    private void findNext (int i , int j , int[][] matrix , Queue<int[]> queue , int level) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= 0) return ;
        matrix[i][j] = -level;
        queue.add(new int[] {i , j});
    }



}
