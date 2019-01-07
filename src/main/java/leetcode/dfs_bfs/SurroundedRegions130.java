package leetcode.dfs_bfs;
/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

Seen this question in a real interview before?

 */

/**
 * the space could be O(1) if it is to use '1' as visited
 * First, check the four border of the matrix. If there is a element is
 * 'O', alter it and all its neighbor 'O' elements to '1'.
 *
 * Then ,alter all the 'O' to 'X'
 *
 * At last,alter all the '1' to 'O'
 *
 * For example:
 *
 *          X X X X           X X X X             X X X X
 *          X X O X  ->       X X O X    ->       X X X X
 *          X O X X           X 1 X X             X O X X
 *          X O X X           X 1 X X             X O X X
 */

public class SurroundedRegions130 {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return ;
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0 ; i < m ; ++i) {
            dfs(i , 0 , board , visited , m , n);
            dfs(i , n - 1 , board , visited , m , n);
        }
        for (int j = 0 ; j < n ; ++j) {
            dfs(0 , j , board , visited , m , n);
            dfs(m - 1 , j , board , visited , m , n);
        }
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (visited[i][j] == 0 && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void dfs (int i , int j , char[][] board , int[][] visited , int m , int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == 1 || board[i][j] == 'X') return;
        visited[i][j] = 1;
        dfs(i - 1 , j , board , visited , m , n);
        dfs(i + 1 , j , board , visited , m , n);
        dfs(i , j - 1 , board , visited , m , n);
        dfs(i , j + 1 , board , visited , m , n);
    }
}
