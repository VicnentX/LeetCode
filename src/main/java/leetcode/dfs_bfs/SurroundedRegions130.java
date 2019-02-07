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
    public void solve(char[][] b) {
        if (b.length == 0 || b[0].length == 0) return ;
        int m = b.length;
        int n = b[0].length;
        //mark the board
        for (int i = 0 ; i <  m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    dfs(i , j , m , n , b);
                }
            }
        }
        //change the board
        for (int i = 0 ; i <  m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (b[i][j] == '#') {
                    b[i][j] = 'O';
                } else {
                    b[i][j] = 'X';
                }
            }
        }
        return ;
    }
    private void dfs (int i , int j , int m , int n , char[][] b) {
        if (i < 0 || i >= m || j < 0 || j >= n || b[i][j] != 'O') return;
        b[i][j] = '#';
        dfs(i - 1 , j , m , n , b);
        dfs(i + 1 , j , m , n , b);
        dfs(i , j - 1 , m , n , b);
        dfs(i , j + 1 , m , n , b);
    }
}
