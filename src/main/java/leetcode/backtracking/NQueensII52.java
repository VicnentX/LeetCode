package leetcode.backtracking;

/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Seen this question in a real interview before?

 */

public class NQueensII52 {
    int[][] dia;
    int ret = 0;
    public int totalNQueens(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        int[] visited = new int[n];
        dia = new int[n][n];
        dfs (0 , n , visited);
        return ret;
    }

    private void dfs (int index , int n , int[] visited) {
        if (index == n) {
            ++ret;
            return ;
        }

        for (int i = 0 ; i < n ; ++i) {
            if (visited[i] == 0 && dia[index][i] == 0) {
                visited[i] = 1;
                markdia1(index , i , n );
                markdia2(index , i , n );

                dfs (index + 1 , n , visited);

                visited[i] = 0;
                unmarkdia1(index , i , n );
                unmarkdia2(index , i , n );
            }
        }
    }

    private void markdia1 (int index , int i , int n ) {
        while (index < n && i >= 0) {
            dia[index][i] += 1;
            ++index;
            --i;
        }
    }
    private void markdia2 (int index , int i , int n ) {
        while (index < n && i < n) {
            dia[index][i] += 1;
            ++index;
            ++i;
        }
    }
    private void unmarkdia1 (int index , int i , int n ) {
        while (index < n && i >= 0) {
            dia[index][i] = dia[index][i] > 0 ? --dia[index][i] : 0;
            ++index;
            --i;
        }
    }
    private void unmarkdia2 (int index , int i , int n ) {
        while (index < n && i < n) {
            dia[index][i] = dia[index][i] > 0 ? --dia[index][i] : 0;
            ++index;
            ++i;
        }
    }
}
