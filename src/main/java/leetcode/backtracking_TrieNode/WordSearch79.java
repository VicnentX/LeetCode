package leetcode.backtracking_TrieNode;

/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */

public class WordSearch79 {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0) return false;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (dfs (i , j , board , m , n , new int[m][n] , 0 , word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs (int i , int j , char[][] board , int m , int n , int[][] visited , int index , String word) {
        if (index == word.length()) return true;
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == 1 || board[i][j] != word.charAt(index)) return false;
        visited[i][j] = 1;

        if ( dfs (i + 1 , j , board , m , n ,visited , index + 1 , word) ||
                dfs (i - 1 , j , board , m , n , visited , index + 1 , word) ||
                dfs (i , j + 1, board , m , n , visited , index + 1 , word) ||
                dfs (i , j - 1, board , m , n , visited , index + 1 , word)
        ) {
            //这里不用trackbacking成visited[i][j] == 0
            return true;
        } else {
            visited[i][j] = 0;
            return false;
        }
    }
}
