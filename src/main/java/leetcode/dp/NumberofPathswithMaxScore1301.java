package leetcode.dp;

/*
You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.

You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

In case there is no path, return [0, 0].



Example 1:

Input: board = ["E23","2X2","12S"]
Output: [7,1]
Example 2:

Input: board = ["E12","1X1","21S"]
Output: [4,2]
Example 3:

Input: board = ["E11","XXX","11S"]
Output: [0,0]


Constraints:

2 <= board.length == board[i].length <= 100
 */

import java.util.List;

public class NumberofPathswithMaxScore1301 {
    //you can take to get that maximum sum, taken modulo 10^9 + 7.
    //这是一个dp 浪费点空间就是grid大小，每个element是一个二位数组。第一位是到这里收集到的最多钱，第二位是路线
    int mod = 1000000007;

    public int[] pathsWithMaxScore(List<String> grid) {
        int m = grid.size();
        int n = grid.get(0).length();

        //init dp
        int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 0; i <= n; ++i) {
            dp[m][i] = new int[]{0, 0};
        }
        for (int i = 0; i <= m; ++i) {
            dp[i][n] = new int[]{0, 0};
        }
        //special point
        dp[m][n] = new int[]{0, 1};


        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                char c = grid.get(i).charAt(j);
                //can not be accessed
                if (c == 'X') {
                    dp[i][j] = new int[]{0,0};
                } else {
                    int maxValue = getValue(i, j, dp);
                    int mostPath = getPath(i, j, dp, maxValue);
                    if (mostPath == 0) {
                        dp[i][j] = new int[]{0,0};
                        continue;
                    }
                    if (c == 'S' || c == 'E') {
                        dp[i][j] = new int[]{maxValue, mostPath};
                    } else {
                        dp[i][j] = new int[]{(maxValue + Character.getNumericValue(c)) % mod, mostPath};
                    }
                }
            }
        }

        return dp[0][0];
    }

    private int getPath(int i, int j, int[][][] dp, int maxValue) {
        int ret = 0;
        if (dp[i + 1][j + 1][0] == maxValue) ret = (ret + dp[i + 1][j + 1][1]) % mod;
        if (dp[i][j + 1][0] == maxValue) ret = (ret + dp[i][j + 1][1]) % mod;
        if (dp[i + 1][j][0] == maxValue) ret = (ret + dp[i + 1][j][1]) % mod;
        return ret;
    }

    private int getValue(int i, int j, int[][][] dp) {
        return Math.max(dp[i + 1][j + 1][0], Math.max(dp[i + 1][j][0], dp[i][j + 1][0]));

    }
}
