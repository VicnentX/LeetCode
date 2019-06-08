package leetcode.array;

/*
Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.



Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.


Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1
 */

import java.util.Arrays;

public class FlipColumnsForMaximumNumberofEqualRows1072 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ret = 0;
        int[] flip = new int[n];
        for (int i = 0 ; i < m ; ++i) {
            int cnt = 0;
            for (int j = 0 ; j < n ; ++j) {
                flip[j] = 1 - matrix[i][j];
            }
            for (int k = 0 ; k < m ; ++k) {
                if (Arrays.equals(matrix[k], matrix[i]) || Arrays.equals(matrix[k], flip)) {
                    cnt++;
                }
            }
            ret = Math.max(ret , cnt);
        }
        return ret;
    }

}
