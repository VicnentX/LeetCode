package CatSlavage;

/*
给你钱的种类 问你可以有几种换钱的方法
 */

public class CoinChangesII518 {
    public int findChangesWaysCnt(int amount, int[] coins) {
        //dp[i] means that how many ways to sum to i
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin: coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
