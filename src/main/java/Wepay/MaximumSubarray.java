package Wepay;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Seen this question in a real interview before?

 */

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ret = dp[0];
        for (int i = 1 ; i < n ; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i] , nums[i]);
            ret = Math.max(ret , dp[i]);
        }
        return ret;
    }
}
