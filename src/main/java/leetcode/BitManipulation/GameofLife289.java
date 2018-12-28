package leetcode.BitManipulation;

/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */

/**
 * first right bit means current state
 * second right bit means next state
 */
public class GameofLife289 {
    public void gameOfLife(int[][] board) {
        /*
Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        */
        if (board == null || board.length == 0) return ;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                int lives = neighbor(i , j , board);
                if ((board[i][j] & 1) == 1) {
                    if (lives  == 2 || lives  == 3) {
                        board[i][j] += 2;
                    }
                } else {
                    if (lives  == 3) board[i][j] += 2;
                }
            }
        }
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                board[i][j] >>= 1;
            }
        }
        return;
    }

    private int neighbor (int r , int c , int[][] nums) {
        int cnt = 0;
        for (int i = r - 1 ;  i <= r + 1 ; ++i) {
            for (int j = c - 1 ;  j <= c + 1 ; ++j) {
                if (i >= 0 && i < nums.length && j >= 0 && j < nums[0].length && (nums[i][j] & 1) == 1)                         ++cnt;
            }
        }
        cnt -= nums[r][c] & 1;
        return cnt;
    }
}
