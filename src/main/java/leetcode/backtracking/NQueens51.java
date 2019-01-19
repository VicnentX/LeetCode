package leetcode.backtracking;

/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

import java.util.*;


/**
 * 这题的难点就是dia的变更 不单单是1和0 因为只有一的话 有些点会被覆盖两次 但是后面那次的backtracking会直接把答案设置为0 其实这个点还不可以被占用。！！！
 */

public class NQueens51 {
    int[][] dia;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        if (n <= 0) return ret;
        int[] visited = new int[n];
        dia = new int[n][n];
        dfs (new StringBuilder() , 0 , n , visited , ret);
        return ret;
    }

    private void dfs (StringBuilder path , int index , int n , int[] visited , List<List<String>> ret) {
        if (index == n) {
            List<String> sol = new ArrayList<>();
            for (int i = 0 ; i < n ; ++i) {
                int pos = path.charAt(i) - '0';
                StringBuilder tem = new StringBuilder();
                for (int j = 0 ; j < pos ; ++j) {
                    tem.append('.');
                }
                tem.append('Q');
                for (int j = pos + 1 ; j < n ; ++j) {
                    tem.append('.');
                }
                sol.add(tem.toString());
            }
            ret.add(sol);
            return ;
        }

        for (int i = 0 ; i < n ; ++i) {
            if (visited[i] == 0 && dia[index][i] == 0) {
                visited[i] = 1;
                markdia1(index , i , n );
                markdia2(index , i , n );

                dfs (path.append(i) , index + 1 , n , visited , ret);

                visited[i] = 0;
                unmarkdia1(index , i , n );
                unmarkdia2(index , i , n );
                path.deleteCharAt(path.length() - 1);
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
