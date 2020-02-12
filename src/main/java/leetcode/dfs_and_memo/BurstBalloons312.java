package leetcode.dfs_and_memo;

/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

import java.util.Arrays;

public class BurstBalloons312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        for (int[] d: dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        int[] arr = new int[n + 2];
        arr[0] = arr[arr.length - 1] = 1;
        for (int i = 0 ; i < n; ++i) {
            arr[i + 1] = nums[i];
        }
        return dfs(1, arr.length - 2, arr, dp);
    }

    private int dfs(int i, int j, int[] nums, int[][] dp) {
        if (i > j) return 0;

        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }

        int tempMax = 0;
        for (int k = i; k <= j; ++k) {
            tempMax = Math.max(tempMax, dfs(i , k - 1, nums, dp)
                    + nums[k] * nums[i - 1] * nums[j + 1]
                    + dfs(k + 1, j, nums, dp));
        }

        return dp[i][j] = tempMax;
    }
}
