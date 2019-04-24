package leetcode.Sort;


// this method is called " counting sort"

/*
We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)



Example 1:

Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (r0, c0) to other cells are: [0,1]
Example 2:

Input: R = 2, C = 2, r0 = 0, c0 = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
Example 3:

Input: R = 2, C = 3, r0 = 1, c0 = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].


Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C
Seen this question in a real interview before?

 */

public class MatrixCellsInDistanceOrder1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[] counter = new int[R + C + 1];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int dist = Math.abs(r - r0) + Math.abs(c - c0);
                counter[dist + 1] += 1;
            }
        }

        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }

        int[][] ans = new int[R * C][];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int dist = Math.abs(r - r0) + Math.abs(c - c0);
                ans[counter[dist]] = new int[] { r, c };
                counter[dist]++;
            }
        }

        return ans;
    }

    public static void main (String[] args) {
        MatrixCellsInDistanceOrder1030 mc = new MatrixCellsInDistanceOrder1030();
        for (int[] cell: mc.allCellsDistOrder(3,4,1,1)) {
            System.out.println(cell[0] + " " + cell[1]);
        }
    }
}
