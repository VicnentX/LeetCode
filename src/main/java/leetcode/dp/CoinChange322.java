package leetcode.dp;

/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */

import java.util.*;

public class CoinChange322 {
    public int coinChangeIterate1 (int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int sum = 0;
        while(++sum <= amount){
            int min = -1;
            for(int coin : coins){
                if(sum >= coin && dp[sum - coin] != -1){
                    int tem = dp[sum - coin] + 1;
                    min = min < 0 ? tem : (min < tem ? min : tem);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

    public int coinChangeIterate2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp , Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1 ; i <= amount ; ++i) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i] , dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*
    #Recursive Method:#
The idea is very classic dynamic programming:
think of the last step we take.
Suppose we have already found out the best way to sum up to amount a,
then for the last step,
we can choose any coin type which gives us a remainder r where r = a-coins[i] for all i's.
For every remainder,
go through exactly the same process as before until either the remainder is 0 or less than 0 (meaning not a valid solution).
With this idea, the only remaining detail is to store the minimum number of coins needed to sum up to r
so that we don't need to recompute it over and over again.
     */

    public int coinChangeRecursive(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }
}
