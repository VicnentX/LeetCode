package leetcode.dp;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

/**
 * 这题比Best Time to Buy and Sell Stock II多了一个cooldown的条件，就变得麻烦多了。这题是一个多阶段优化问题，首先范围缩小到广搜，贪心或者动规。因为每步之间互相牵连，贪心显然不行。广搜固然可以，不过是O(2^n)复杂度，所以我们先考虑用动规。
 *
 * 对于每一天，有三种动作，buy, sell, cooldown, sell 和 cooldown 可以合并成一种状态，因为手里最终没有股票。最终需要的结果是 sell，即手里股票卖了获得最大利润。我们可以用两个数组来记录当前持股和未持股的状态，令sell[i] 表示第i天未持股时，获得的最大利润，buy[i]表示第i天持有股票时，获得的最大利润。
 * 对于sell[i]，最大利润有两种可能，一是今天没动作跟昨天未持股状态一样，二是今天卖了股票，所以状态转移方程如下：
 * sell[i] = max{sell[i - 1], buy[i-1] + prices[i]}
 * 对于buy[i]，最大利润有两种可能，一是今天没动作跟昨天持股状态一样，二是前天卖了股票，今天买了股票，因为 cooldown 只能隔天交易，所以今天买股票要追溯到前天的状态。状态转移方程如下：
 * buy[i] = max{buy[i-1], sell[i-2] - prices[i]}
 * 最终我们要求的结果是sell[n - 1]，表示最后一天结束时，手里没有股票时的最大利润。
 * 这个算法的空间复杂度是O(n)，不过由于sell[i]仅仅依赖前一项，buy[i]仅仅依赖前两项，所以可以优化到O(1)，具体见第二种代码实现。
 */

public class BestTimetoBuyandSellStockwithCooldown309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] sell = new int[prices.length];
        int[] buy = new int[prices.length];
        sell[0] = 0;
        buy[0] = -prices[0];
        for (int i = 1 ; i < prices.length ; ++i) {
            sell[i] = Math.max(sell[i - 1] , buy[i - 1] + prices[i]);
            buy[i] = Math.max(buy[i - 1] , (i >= 2 ? sell[i - 2] : 0) - prices[i]);
        }
        return sell[prices.length - 1];
    }

    public int maxProfitO1space(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int curSell = 0; //i - 1;
        int preSell = 0; //i - 2;
        int curBuy = -prices[0]; //i - 1;
        for (int i = 1 ; i < prices.length ; ++i) {
            int temp = curSell; // cursell 存在下 到下一轮这个i－ 1 就变成 i－2了 就相当于是preSell
            curSell = Math.max(curSell , curBuy + prices[i]);
            curBuy = Math.max(curBuy , (i >= 2 ? preSell : 0) - prices[i]);
            preSell = temp;
        }
        return curSell;
    }

    /**
     * !!!!!这里方法比较清楚 s0 = s[i] s[1] = s[i - 1] ...
     * @param prices
     * @return
     */
    public int maxProfitO1spaceClear(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int b0 = -prices[0] , b1 = b0;
        int s0 = 0 , s1 = 0 , s2 = 0;
        for (int i = 1 ; i < prices.length ; ++i) {
            s0 = Math.max(s1 , b1 + prices[i]);
            b0 = Math.max(b1 , s2 - prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }



    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown309 bt = new BestTimetoBuyandSellStockwithCooldown309();
        System.out.println(bt.maxProfit(new int[] {1,2,3,0,2}));
        System.out.println(bt.maxProfitO1space(new int[] {1,2,3,0,2}));
        System.out.println(bt.maxProfitO1spaceClear(new int[] {1,2,3,0,2}));
    }
}
