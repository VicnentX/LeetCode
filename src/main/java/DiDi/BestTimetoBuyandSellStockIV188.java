package DiDi;

/*
Say you have an array for which the i-th element is the price of a given stock on day i.

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

/**
 *  * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
 *  * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *  *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 *                  所以上面可以看出 下面的程序是先算这个max(dp[i-1, jj] - prices[jj])
 *                                                 然后 再来 跟新它 ！！！这个点写的时候要注意
 *  * dp[0, j] = 0; 0 transactions makes 0 profit
 *  * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 */


public class BestTimetoBuyandSellStockIV188 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        final int N = prices.length;
        if (k >= N / 2) {
            int ret = 0;
            for (int i = 1; i < N; ++i) {
                ret += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ret;
        }

        //dp
        int[][] dp = new int[k + 1][N];
        for (int i = 1; i <= k; ++i) {
            int tempMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < N; ++j) {
                dp[i][j] = Math.max(dp[i][j - 1] , prices[j] + tempMax);
                tempMax = Math.max(tempMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][N - 1];
    }
}
