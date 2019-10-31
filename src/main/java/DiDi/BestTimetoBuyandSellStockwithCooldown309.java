package DiDi;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.
You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

/**
 * Hi,
 *
 * I just come across this problem, and it's very frustating since I'm bad at DP.
 *
 * So I just draw all the actions that can be done.
 *
 * Here is the drawing (Feel like an elementary ...)
 *
 * image
 *
 * Original image
 *
 * There are three states, according to the action that you can take.
 *
 * Hence, from there, you can now the profit at a state at time i as:
 *
 * s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
 * s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
 * s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
 * Then, you just find the maximum of s0[n] and s2[n], since they will be the maximum profit we need (No one can buy stock and left with more profit that sell right :) )
 *
 * Define base case:
 *
 * s0[0] = 0; // At the start, you don't have any stock if you just rest
 * s1[0] = -prices[0]; // After buy, you should have -prices[0] profit. Be positive!
 * s2[0] = INT_MIN; // Lower base case
 */

public class BestTimetoBuyandSellStockwithCooldown309 {

    public int maxProfit(int[] prices) {
        final int N = prices.length;
        if (N < 1) return 0;
        int[] buy = new int[N];
        int[] sell = new int[N];
        int[] coolDown = new int[N];
        buy[0] = -prices[0];
        sell[0] = 0;
        coolDown[0] = 0;

        for (int i = 0; i < N; ++i) {
            coolDown[i] = Math.max(coolDown[i - 1], sell[i - 1]);
            buy[i] = Math.max(buy[i - 1], coolDown[i - 1] - prices[i]);
            //这里是因为 我sell应该就是今天买的 不然的话今天的状态不是cooldown就是buy 所以这里没有用Math max来做选择
            sell[i] = buy[i - 1] + prices[i];
        }

        return Math.max(sell[N -1], coolDown[N - 1]);
    }

}
