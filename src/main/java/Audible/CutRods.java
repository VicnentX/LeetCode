package Audible;

/*
第一题切木头，地里之前有发过，不过好像说有3个case过不来吧，
这里提供一种能过的思路。题目说给一个数组lengths，每个元素代表每根木头的长度，
厂商要把木头切成一个uniform的长度saleLength，这样得到uniformRodNum根木头，
可以卖钱，单价salePrice，但是切一刀是要花费cutCost的，求能获得的最大利润。
利润 = salePrice*saleLength*unformRodNum - cutCost*cutNum。
比如，给定lengths = [26, 103, 56]，salePrice = 10, cutCost = 1，
要获得最大利润，要把木头切成saleLength = 6的小段，利润 = 10*6*30 - 1*30 = 1500
(LZ忘了例子里的数是多少，这个saleLength = 6不一定是最优解，就表示一下意思哈)。
方法：穷举。对saleLength从最长的木头到1开始穷举，求出每个情况下对利润，取最大值。
对于任意一根木头，如果木头长度小于saleLength，那这根木头没用；
如果木头长度大于saleLength，那我们就要检查，切一刀下去是赚了还是亏了，赚了就切，亏了就不切。
这样可以通过所有testcase。
 */

import java.util.List;

public class CutRods {
    /*
     * Complete the 'maxProfit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER costPerCut
     * 2. INTEGER salePrice
     * 3. INTEGER_ARRAY lengths
     */

    /**
     * 这里有个坑就是：不论怎么切 结果都是负数的话就整个扔掉！！！
     * @param costPerCut
     * @param salePrice
     * @param lengths
     * @return
     */

    public int maxProfit(int costPerCut, int salePrice, List<Integer> lengths) {
        int maxProfit = Integer.MIN_VALUE;
        int maxLen = 0;
        //get max len
        for (int len: lengths) {
            maxLen = Math.max(maxLen, len);
        }
        //check from 1 to max len
        for (int i = 1; i <= maxLen; ++i) {
            int tempMaxProfit = calculateProfit(costPerCut, salePrice, i, lengths);
            maxProfit = Math.max(maxProfit, tempMaxProfit);
        }

        return maxProfit;
    }

    private int calculateProfit(int costPerCut, int salePrice, int i, List<Integer> lengths) {
        int totalCost = 0;
        for (int len: lengths) {
            int res = len % i;
            int div = len / i;
            if (res == 0) {
                totalCost += Math.max(0, salePrice * len - costPerCut * (div - 1));
            } else {
                totalCost += Math.max(0, salePrice * 10 - costPerCut * div - salePrice * res);
            }
        }
        return totalCost;
    }
}
