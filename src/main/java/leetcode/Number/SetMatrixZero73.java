package leetcode.Number;

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */

/**
 * 这题先是检查第一列和第一行有没有零
 * 因为后面别的地方的0都会被映射到第一列和第一行 所以先要存下来
 */
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
