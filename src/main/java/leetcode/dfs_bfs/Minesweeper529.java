package leetcode.dfs_bfs;

/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.


Example 1:

Input:

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output:

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:



Note:

The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
Seen this question in a real interview before?

 */
import java.util.*;
public class Minesweeper529 {
    public char[][] updateBoard(char[][] b, int[] click) {
        if (b.length == 0 || b[0].length == 0) return b;
        int m = b.length;
        int n = b[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (b[i][j] == 'M') {
                b[i][j] = 'X';
            } else if (b[i][j] == 'E'){
                List<int[]> tem = checkPoint(i , j , b , m , n);
                if (b[i][j] == 'B') {
                    queue.addAll(tem);
                }
            }
        }
        return b;
    }
    private List<int[]> checkPoint(int r , int c , char[][] b , int m , int n) {
        int cnt = 0;
        List<int[]> tem = new ArrayList<>();
        for (int i = r - 1 ; i <= r + 1 ; ++i) {
            for (int j = c - 1 ; j <= c + 1 ; ++j) {
                if (i >= 0 && i < m && j >= 0 && j < n) {
                    tem.add(new int[] {i , j});
                    if (b[i][j] == 'M' || b[i][j] == 'X') {
                        ++cnt;
                    }
                }
            }
        }
        if (cnt != 0) {
            b[r][c] = (char)(cnt + '0');
        } else {
            b[r][c] = 'B';
        }

        return tem;
    }

    //BFS
    public char[][] updateBoardBFS(char[][] board, int[] click) {
        if (board.length == 0 || board[0].length == 0) return board;
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if (board[i][j] == 'M') {
                board[i][j] = 'X';
            } else {
                int mine = findMines(i , j , board , m , n);
                board[i][j] = mine == 0 ? 'B' : (char)(mine + '0');
                if (board[i][j] == 'B') {
                    addQueue(i , j , board , m , n , queue);
                }
            }
        }
        return board;
    }
    private int findMines(int r , int c , char[][] board , int m , int n) {
        int ret = 0;
        for (int i = r - 1 ; i <= r + 1 ; ++i) {
            for (int j = c - 1 ; j <= c + 1 ; ++j) {
                if (i < 0 || i >= m || j < 0 || j >= n) continue;
                if (board[i][j] == 'X' || board[i][j] == 'M') {
                    ++ret;
                }
            }
        }
        return ret;
    }
    private void addQueue(int r , int c , char[][] board , int m , int n , Queue<int[]> queue) {
        for (int i = r - 1 ; i <= r + 1 ; ++i) {
            for (int j = c - 1 ; j <= c + 1 ; ++j) {
                if (i < 0 || i >= m || j < 0 || j >= n) continue;
                if (board[i][j] == 'M') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'E'){
                    board[i][j] = 'Q';//mark as having been add to queue
                    queue.add(new int[] {i , j});
                }
            }
        }
    }
}
