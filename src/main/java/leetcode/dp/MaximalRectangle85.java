package leetcode.dp;

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */

/**
 * The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j).
 *
 * All the 3 variables left, right, and height can be determined by the information from previous row, and also information from the current row. So it can be regarded as a DP solution. The transition equations are:
 *
 * left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
 *
 * right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
 *
 * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
 *
 * height(i,j) = 0, if matrix[i][j]=='0'
 */

import java.util.Arrays;

public class MaximalRectangle85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        Arrays.fill(left , 0);
        int[] right = new int[n];
        Arrays.fill(right , n - 1);
        int[] height = new int[n];
        Arrays.fill(height , 0);
        int ret = 0;

        for (int i = 0 ; i < m ; ++i) {
            int curLeft = 0 , curRight = n - 1;
            //determine height[]
            for (int j = 0 ; j < n ; ++j) {
                if (matrix[i][j] == '1') {
                    ++height[j];
                } else {
                    height[j] = 0;
                }
            }
            //determine left
            for (int j = 0 ; j < n ; ++j) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(curLeft , left[j]);
                } else {
                    left[j] = 0; /**
                     这里写成0很有意思 因为对于下一层来说上面这层对应这个点是 0 所以我就不用考虑这一层了所以左边界不应该收到上一层的限制
                     */
                    curLeft = j + 1;
                }
            }
            //determine right
            for (int j = n - 1 ; j >= 0 ; --j) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(curRight , right[j]);
                } else {
                    right[j] = n - 1;
                    curRight = j - 1;
                }
            }
            //find temporary max of ret
            for (int j = 0 ; j < n ; ++j) {
                ret = Math.max(ret , height[j] * (right[j] - left[j] + 1));
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        MaximalRectangle85 mr = new MaximalRectangle85();
        /*
        [
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
         */
        System.out.println(mr.maximalRectangle(new char[][] {
                {'1' , '0' , '1' , '0' , '0'},
                {'1' , '0' , '1' , '1' , '1'},
                {'1' , '1' , '1' , '1' , '1'},
                {'1' , '0' , '0' , '1' , '0'}
        }));
    }
}
