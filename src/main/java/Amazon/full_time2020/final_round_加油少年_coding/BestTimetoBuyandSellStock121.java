package Amazon.full_time2020.final_round_加油少年_coding;


/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */


/**
 * 这题就是只能买卖一次
 * 第二题可以买卖多次 我的第二题也在GitHub里面 我同事还打印出来了 比如估计1，3，3，5 我就算作一次买卖 1-5 没有拆开
 *
 * 题目是高频买卖股票题，follow up是如果允许可以买卖多次该怎么解，
 * 我想了半天只写了一个dfs的解法，
 * 按他的说法最优能到O(n),
 * 但是他想看我的thought process就让我写我说的dfs了。 dfs是不对的不用管他
 */


public class BestTimetoBuyandSellStock121 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minBuy = Integer.MAX_VALUE;

        for (int price: prices) {
            minBuy = Math.min(minBuy, price);
            maxProfit = Math.max(maxProfit, price - minBuy);
        }

        return maxProfit;
    }
}
