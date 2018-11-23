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

public class CoinChange322 {
    public int coinChange(int[] coins, int amount) {
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
}
