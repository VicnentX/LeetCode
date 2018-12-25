package leetcode.dp;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

import java.util.Arrays;

/**
 * //dp[k] means that at kth times the max profit;
 *         //min[k] means the min between the min[k] and  prices[i] - dp[k - 1] ;
 *         //we could know the dp[k] = the max between dp[k] and prices - min ; 这里相当于 s2 - minOF(b2 - (s1 - b1))
 *         //这里的思路很神奇
 */

public class BestTimetoBuyandSellStockIV188 {
    public int maxProfit(int k, int[] prices) {

        if (prices == null || prices.length == 0 || k <= 0) return 0;

        if (k >= prices.length / 2) {
            int ret = 0 ;
            for (int i = 1 ; i < prices.length ; ++i) ret += (prices[i] - prices[i - 1]);
            return ret;
        }

        int[] min = new int[k + 1];
        int[] dp = new int[k + 1];
        Arrays.fill(min , prices[0]);
        for (int i = 1 ; i < prices.length ; ++i) {
            for (int j = 1 ; j <= k ; ++j) {
                min[j] = Math.min(min[j] , prices[i] - dp[j - 1]);
                dp[j] = Math.max(dp[j] , prices[i] - min[j]);
            }
        }
        return dp[k];
    }

    public static void main (String[] args) {
        BestTimetoBuyandSellStockIV188 bt = new BestTimetoBuyandSellStockIV188();
        System.out.println(bt.maxProfit(2 , new int[] {3,2,6,5,0,3}));
    }
}
