package leetcode.dp;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

import java.util.Arrays;

public class BestTimetoBuyandSellStockIII123 {
    public int maxProfitMethod1(int[] prices) {
        //dp[k] means that at kth times the max profit;
        //min[k] means the min between the min[k] and  prices[i] - dp[k - 1] ;
        //we could know the dp[k] = the max between dp[k] and prices - min ; 这里相当于 s2 - minOF(b2 - (s1 - b1))
        //这里的思路很神奇
        int maxTrans = 2;
        int[] min = new int[maxTrans + 1];
        int[] dp = new int[maxTrans + 1];
        Arrays.fill(min , prices[0]);
        for (int i = 1 ; i < prices.length ; ++i) {
            for (int k = 1 ; k < maxTrans ; ++k) {
                min[k] = Math.min(min[k] , prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k] , prices[i] - min[k]);
            }
        }
        return dp[maxTrans - 1];
    }

    public int maxProfitMethod2(int[] prices) {
        //dp[k] means that at kth times the max profit;
        //min[k] means the min between the min[k] and  prices[i] - dp[k - 1] ;
        //we could know the dp[k] = the max between dp[k] and prices - min ; 这里相当于 s2 - minOF(b2 - (s1 - b1))
        //这里的思路很神奇
        int maxTrans = 2;
        int[] min = new int[maxTrans + 1];
        int[] dp = new int[maxTrans + 1];
        Arrays.fill(min , Integer.MAX_VALUE);//fill with MAX_VALUE
        for (int i = 0 ; i < prices.length ; ++i) {//so here should begin from 0
            for (int k = 1 ; k < maxTrans ; ++k) {
                min[k] = Math.min(min[k] , prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k] , prices[i] - min[k]);
            }
        }
        return dp[maxTrans - 1];
    }


    public static void main (String[] args) {
        BestTimetoBuyandSellStockIII123 bt = new BestTimetoBuyandSellStockIII123();
        System.out.println(bt.maxProfitMethod1(new int[] {7,6,4,3,1}));
        System.out.println(bt.maxProfitMethod2(new int[] {1,2,3,4,5}));
    }
}
