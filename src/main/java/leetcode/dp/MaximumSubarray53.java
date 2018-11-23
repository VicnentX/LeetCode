package leetcode.dp;

public class MaximumSubarray53 {
    public int maxSubArray(int[] nums) {
        //dp problem
        //dp[i] = max(dp[i - 1] + nums[i] , nums[i]);

        int n = nums.length;
        int[] dp = new int[n];
        int max = nums[0];
        dp[0] = nums[0];
        for(int i = 1 ; i < n ; ++i){
            dp[i] = Math.max(dp[i - 1] + nums[i] , nums[i]);
            max = Math.max(max , dp[i]);
        }
        return max;
    }
}
