package leetcode.dp;

/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */

/**
 * 这题dp的长度设置有讲究
 * 同是因为有phase 所以要当心
 */

public class LongestLineofConsecutiveOneinMatrix562 {
    public int longestLine(int[][] matrix) {
        int ret = 0;
        if (matrix == null || matrix.length == 0) return ret;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] hor = new int[m + 1][n + 2];
        int[][] ver = new int[m + 1][n + 2];
        int[][] dia = new int[m + 1][n + 2];
        int[][] ant = new int[m + 1][n + 2];
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (matrix[i][j] == 1) {
                    hor[i + 1][j + 1] = 1 + hor[i][j + 1];
                    ret = Math.max(ret , hor[i + 1][j + 1]);
                    ver[i + 1][j + 1] = 1 +  ver[i + 1][j];
                    ret = Math.max(ret , ver[i + 1][j + 1]);
                    dia[i + 1][j + 1] = 1 + dia[i][j];
                    ret = Math.max(ret , dia[i + 1][j + 1]);
                    ant[i + 1][j + 1] = 1 + ant[i][j + 2];
                    ret = Math.max(ret , ant[i + 1][j + 1]);
                }
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        LongestLineofConsecutiveOneinMatrix562 ll = new LongestLineofConsecutiveOneinMatrix562();
        System.out.println(ll.longestLine(new int[][] {{0,1,1,0} , {0,1,1,0} , {0,0,0,1}}));
    }
}
