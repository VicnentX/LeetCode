package Amazon.full_time2020.final_round_加油少年_coding;

/*
Try 1 through 9 for each cell.
The time complexity should be 9 ^ m
(m represents the number of blanks to be filled in),
since each blank can have 9 choices. Details see comments inside code.
 */

public class SudokuSolver37 {
    public boolean solveSudoku(char[][] board) {
        if(board == null || board.length == 0) {
            return false;
        }
        //try to fill the board
        return dfs(board);
    }

    public boolean dfs (char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; ++c) {
                        if (isValidNumber(board, i, j, c)) {
                            board[i][j] = c;
                            if (dfs(board)) {       //dfs
                                return true;
                            } else {
                                board[i][j] = '.';  //restore the board
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidNumber(char[][] board, int row, int col, char c) {
        int regionRow = 3 * (row / 3);  //region start row
        int regionCol = 3 * (col / 3);    //region start col
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; //check row
            if (board[row][i] == c) return false; //check column
            if (board[regionRow + i / 3][regionCol + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

}
