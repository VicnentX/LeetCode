package DiDi;

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

public class BestTimetoBuyandSellStockIII123 {
    /**
     * my idea is to use two loops to figure out that two arrays
     * left[i] means the max profit one transaction can make from 0 - i
     * right[i] means the max profit one transaction can make from i - n-1
     */
    public int maxProfit(int[] prices) {
        int ret = 0;
        int temp = 0;
        if (prices == null || prices.length < 2) return ret;
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        //fill left
        int minBuy = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            minBuy = Math.min(minBuy, prices[i]);
            temp = Math.max(temp, prices[i] - minBuy);
            left[i] = temp;
        }
        //fill right
        temp = 0;
        int maxSell = prices[prices.length - 1];
        for (int i = prices.length - 1; i >= 0; i--) {
            maxSell = Math.max(maxSell, prices[i]);
            temp = Math.max(temp, maxSell - prices[i]);
            right[i] = temp;
        }
        //get result
        for (int i = 0; i < prices.length; ++i) {
            ret = Math.max(ret, left[i] + right[i]);
        }
        return ret;

    }
}
