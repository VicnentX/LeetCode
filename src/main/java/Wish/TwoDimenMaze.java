package Wish;
/*
问了一道题目，给一个m乘n的长方形，问从左上走到右下有多少种走法？每次只能往下或者往右走
比方说

0 0
0 0

2乘2个格子，有2种走法

0 0 0
0 0 0

2乘3个格子，有3种走法

可以用dp解
 */

public class TwoDimenMaze {
    public int findWays(int m , int n){
        int ret = 0;
        if(m == 0 || n == 0){
            return ret;
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        for(int i = 1 ; i < m + 1 ; ++i){
            for(int j = 1 ; j < n + 1 ; ++j){
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return  dp[m][n];
    }
    public static void main(String[] args){
        TwoDimenMaze td = new TwoDimenMaze();
        System.out.println(td.findWays(2,2));
        System.out.println(td.findWays(2,3));
        System.out.println(td.findWays(2,4));
        System.out.println(td.findWays(3,4));
    }
}
