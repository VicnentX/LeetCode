package leetcode.KnapsackProblem;

/*
其实完全背包和多重背包都可以在 O(NW)时间内完成，
前者在视频中有讲到，后者属于超纲内容，以后有机会再和大家深入分享。
 */
public class UnboundedKnapsack {
    public int unbounded(int[] weights, int[] values, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        int n = weights.length;
        for (int i = 0; i < n; ++i) {
            for (int j = weights[i]; j <= maxWeight; ++j) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        //这里是不必装满的情况，所以直接取dp[maxWeight]
        return dp[maxWeight];
    }

    public static void main(String[] args) {
        UnboundedKnapsack unboundedKnapsack = new UnboundedKnapsack();
        //12
        System.out.println(unboundedKnapsack.unbounded(new int[]{2, 4}, new int[]{2, 5}, 11));
    }
}
