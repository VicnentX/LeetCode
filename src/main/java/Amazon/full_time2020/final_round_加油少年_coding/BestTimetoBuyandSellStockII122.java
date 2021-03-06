package Amazon.full_time2020.final_round_加油少年_coding;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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

/**
 * 我这个是1，3，3，5算一笔交易 当然拆成两笔是一样的
 *
 * 用当前的值 和 后面的值比较下 然后看
 *
 */

import java.util.ArrayList;
import java.util.List;

public class BestTimetoBuyandSellStockII122 {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        final int N = prices.length;
        //这个是记录下 买点和买点 对于一笔交易
        List<Integer> buyInfo = new ArrayList<>();
        for (int i = 0; i < N - 1; ++i) {
            if (prices[i + 1] >= prices[i]) {
                totalProfit += prices[i + 1] - prices[i];
                if (buyInfo.size() == 0) {
                    buyInfo.add(i);
                    buyInfo.add(prices[i]);
                }
            } else {
                if (buyInfo.size() != 0) {
                    System.out.println("buy at day " + (buyInfo.get(0) + 1) + " in price of " + buyInfo.get(1));
                    System.out.println("sell at day " + (i + 1) + " in price of " + prices[i]);
                    buyInfo.clear();
                    System.out.println();
                }
            }
        }
        if (buyInfo.size() != 0) {
            System.out.println("buy at day " + (buyInfo.get(0) + 1) + " in price of " + buyInfo.get(1));
            System.out.println("sell at day " + N + " in price of " + prices[N - 1]);
            buyInfo.clear();
            System.out.println();
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockII122 bestTimetoBuyandSellStockII122 = new BestTimetoBuyandSellStockII122();
        //7
        System.out.println(bestTimetoBuyandSellStockII122.maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println("------------------");
        //4
        System.out.println(bestTimetoBuyandSellStockII122.maxProfit(new int[] {1,2,3,4,5}));
        System.out.println("------------------");
        //0
        System.out.println(bestTimetoBuyandSellStockII122.maxProfit(new int[] {7,6,3}));
    }
}
