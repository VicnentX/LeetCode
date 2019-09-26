package Akuna;

import java.util.Arrays;

/**
 * given a array, start from index = 0; end at the last index;
 * k is the longest step you can arrive every time.
 * get the max sum of the process
 */

public class ArrayJourney {
    public int getMaxSum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return Integer.MIN_VALUE;
        }

        final int N = nums.length;
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= k && i - j >= 0; ++j) {
                dp[i] = Math.max(dp[i], nums[i] + dp[i - j]);
            }
        }
        return dp[N - 1];
    }

    public static void main(String[] args) {
        ArrayJourney arrayJourney = new ArrayJourney();
        //37
        System.out.println(arrayJourney.getMaxSum(new int[] {10,2,-10,5,20}, 2));
        //5
        System.out.println(arrayJourney.getMaxSum(new int[] {10,-20,-5}, 2));
        //0
        System.out.println(arrayJourney.getMaxSum(new int[] {3,-4,-3,-5,0}, 2));
    }
}
