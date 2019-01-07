package leetcode.dfs_bfs;

/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

public class LongestIncreasingPathinaMatrix329 {
    public int longestIncreasingPath(int[][] matrix) {
        int ret = 0;
        if (matrix.length == 0 || matrix[0].length == 0) return ret;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] mem = new int[m][n];
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                ret = Math.max(ret , dfs(i , j , m , n , matrix , mem , Integer.MIN_VALUE));
            }
        }
        return ret;
    }

    private int dfs (int i , int j , int m , int n , int[][] matrix , int[][] mem , int pre) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;
        if (matrix[i][j] <= pre) return 0;
        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        int len =  1 + Math.max(dfs(i - 1 , j , m , n , matrix , mem , matrix[i][j]) ,
                Math.max(dfs(i + 1 , j , m , n , matrix , mem , matrix[i][j]) ,
                        Math.max(dfs(i , j - 1, m , n , matrix , mem , matrix[i][j]) ,
                                dfs(i , j + 1, m , n , matrix , mem , matrix[i][j]) )));
        mem[i][j] = len;
        return len;
    }

    public static void main (String[] args) {
        LongestIncreasingPathinaMatrix329 li = new LongestIncreasingPathinaMatrix329();
        System.out.println(li.longestIncreasingPath(new int[][] {
                {3,4,5},
                {3,2,6},
                {2,2,1}
        }));
    }
}
