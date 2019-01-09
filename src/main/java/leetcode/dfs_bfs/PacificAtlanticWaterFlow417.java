package leetcode.dfs_bfs;

/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

import java.util.*;

public class PacificAtlanticWaterFlow417 {
    int[][] visited;

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> ret = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        int m = matrix.length;
        int n = matrix[0].length;

        visited = new int[m][n];

        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (connectAtlantic(i , j , matrix , Integer.MAX_VALUE) && connectPacific(i , j , matrix , Integer.MAX_VALUE)) {
                    ret.add(new int[] {i , j});
                }
            }
        }
        return ret;
    }
    private boolean connectAtlantic (int i , int j , int[][] matrix , int pre) {
        if (i >= matrix.length || j >= matrix[0].length) return true;
        if (i < 0 || j < 0) return false;

        if (visited[i][j] == 1) {
            return false;
        }

        if (matrix[i][j] > pre) {
            return false;
        }

        visited[i][j] = 1;
        if (connectAtlantic(i - 1, j , matrix , matrix[i][j])
                || connectAtlantic(i + 1, j , matrix , matrix[i][j])
                || connectAtlantic(i , j - 1, matrix , matrix[i][j])
                || connectAtlantic(i , j + 1, matrix , matrix[i][j])) {
            visited[i][j] = 0;
            return true;
        }
        visited[i][j] = 0;
        return false;
    }
    private boolean connectPacific (int i , int j , int[][] matrix , int pre) {
        if (i < 0 || j < 0) return true;
        if (i >= matrix.length || j >= matrix[0].length) return false;

        if (visited[i][j] == 1) {
            return false;
        }

        if (matrix[i][j] > pre) {
            return false;
        }

        visited[i][j] = 1;

        if (connectPacific(i - 1, j , matrix , matrix[i][j])
                || connectPacific(i + 1, j , matrix , matrix[i][j])
                || connectPacific(i , j - 1, matrix , matrix[i][j])
                || connectPacific(i , j + 1, matrix , matrix[i][j])) {
            visited[i][j] = 0;
            return true;
        }
        visited[i][j] = 0;
        return false;
    }
}
