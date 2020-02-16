package leetcode.KnapsackProblem;

public class BoundedKnapsack {
    public int solve(int[] weights, int[] values, int[] count, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        int n = weights.length;
        for (int i = 0; i < n; ++i) {
            //如果count[i] * weights[i] >= maxWeight,
            // 就相当于选不满就超标了，就相当于随便选
            if (count[i] * weights[i] >= maxWeight) {
                knapsackUnbounded(weights[i], values[i], dp, maxWeight);
                continue;
            }
            int remain = count[i];
            for (int k = 0; k <= Math.log(count[i]) - 1; ++k) {
                knapsack01(weights[i] << k, values[i] << k, dp, maxWeight);
                remain -= (1 << k);
            }
            //把剩余的remain再做一次
            knapsack01(weights[i] * remain, values[i] * remain, dp, maxWeight);
        }
        return dp[maxWeight];
    }

    private void knapsackUnbounded(int weight, int value, int[] dp, int maxWeight) {
        for (int j = weight; j <= maxWeight; ++j) {
            dp[j] = Math.max(dp[j], dp[j - weight] + value);
        }
    }

    private void knapsack01(int weight, int value, int[] dp, int maxWeight) {
        for (int j = maxWeight; j >= weight; ++j) {
            dp[j] = Math.max(dp[j], dp[j - weight] + value);
        }
    }
}
