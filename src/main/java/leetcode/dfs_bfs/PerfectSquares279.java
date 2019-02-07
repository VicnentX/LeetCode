package leetcode.dfs_bfs;

/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

import java.util.*;
public class PerfectSquares279 {
    private int ret = Integer.MAX_VALUE;

    /*
    dfs is too slow
    */
    public int numSquaresDFS(int n) {
        dfs(n , 0);
        return ret;
    }
    private void dfs (int n , int level) {
        if (n == 0) {
            ret = Math.min(ret , level);
            return ;
        }
        for (int i = (int)Math.sqrt(n) ; i >= 1 ; --i) {
            dfs(n - i * i , level + 1);
        }
    }

    //DP

    public int numSquaresDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp , Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1 ; i <= n ; ++i) {
            for (int j = 1 ; j * j <= i ; ++j) {
                dp[i] = Math.min(dp[i] , dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }


    //


    public static void main (String[] args) {
        PerfectSquares279 ps = new PerfectSquares279();
        System.out.print(ps.numSquaresDFS(58));
    }
}
