package google.google2020.google_加油少年_ResidentVO;


/*

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */


import java.util.HashMap;
import java.util.Map;

public class RangeSumQuery2D_Immutable304 {

    int[][] grid;
    Map<String, Integer> map;


    public RangeSumQuery2D_Immutable304(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            grid = null;
        } else {
            final int M = matrix.length;
            final int N = matrix[0].length;
            grid = new int[M + 1][N + 1];
            map = new HashMap<>();
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    grid[i + 1][j + 1] = matrix[i][j] + grid[i + 1][j] + grid[i][j + 1] - grid[i][j];
                }
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        String key = row1 + " " + col1 + " " + row2 + " " + col2;
        if (grid == null) return -1;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int ret = grid[row2 + 1][col2 + 1] - grid[row2 + 1][col1] - grid[row1][col2 + 1] + grid[row1][col1];
        map.put(key, ret);
        return ret;
    }
}
