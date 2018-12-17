package leetcode.Number;

public class SetMatrixZero73 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ;
        //do not use extra space
        boolean hasZeroFirstRow = false , hasZeroFirstCol = false;

        int m = matrix.length;
        int n = matrix[0].length;
        //check if there is zero in the row;
        for (int i = 0 ; i < n ; ++i) {
            if (matrix[0][i] == 0) {
                hasZeroFirstRow = true;
                break;
            }
        }
        //check if there is zero in the col;
        for (int i = 0 ; i < m ; ++i) {
            if (matrix[i][0] == 0) {
                hasZeroFirstCol = true;
                break;
            }
        }
        //set first row and first col corresponding 0 when scan the rest of matrix
        for (int i = 1 ; i < m ; ++i) {
            for (int j = 1 ; j < n ; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //
        for (int i = 1 ; i < m ; ++i) {
            for (int j = 1 ; j < n ; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // set the first row;
        if (hasZeroFirstRow) {
            for (int i = 0 ; i < n ; ++i) {
                matrix[0][i] = 0;
            }
        }
        // set the first col;
        if (hasZeroFirstCol) {
            for (int i = 0 ; i < m ; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}
