package leetcode.dp;


/*
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.



Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1


Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
 */

/**
 * Create auxillary horizontal and vertical arrays first
 * For example :
 * image
 *
 * Then starting from bottom right,for every i,j ; we find small=min (ver[i][j], hor[i][j]) (marked in orange) , then look at all distances in [1,small] vertically in hor array and horizontally in ver array. If values(shown in blue) are greater than small and if small is greater than curr result, then we update result
 * image
 */
public class Largest1BorderedSquare1139 {
    public int largest1BorderedSquare(int[][] grid) {
        int max = 0;
        int m = grid.length + 1;
        int n = grid[0].length + 1;
        int[][] dpr = new int[m][n];
        int[][] dpc = new int[m][n];
        for (int i = 1 ; i < m ; ++i) {
            for (int j = 1 ; j < n ; ++j) {
                if (grid[i][j] == 1) {
                    dpr[i][j] = dpr[i][j - 1] + 1;
                    dpc[i][j] = dpc[i - 1][j] + 1;
                }
            }
        }
        for (int i = m - 1; i > 0 ; --i) {
            for (int j = n - 1; j > 0 ; --j) {
                int small = Math.min(dpr[i][j] , dpc[i][j]);
                while (small > max) {
                    if (dpc[i][j - small + 1] >= small && dpr[i - small + 1][j] >= small) {
                        max = small;
                    }
                    --small;
                }
            }
        }
        return max * max;
    }
}
