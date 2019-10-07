package Amazon.full_time2020.final_round_加油少年_coding;

/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */


import java.util.stream.IntStream;

/**
 * 如果用dfs
 * This problem is NPC,
 * so time complexity is exp O(n^k)
 * as for each number in nums we need to decide which target slot it belongs to.
 */

public class PartitionToKEqualSumSubsets698 {
    public boolean canPartitionKSubsets(int[] a, int k) {
        int sum = IntStream.of(a).sum();
        return k != 0 && sum % k == 0 && canPartited(0, a, new int[a.length], k, 0, sum / k);
    }

    private boolean canPartited(int start, final int[] nums, int[] visited, int remain, int curSum, final int target) {
        if (remain == 1) return true;
        if (curSum == target) {
            return canPartited(0, nums, visited, remain - 1, 0, target);
        }

        for (int i = start; i < nums.length; ++i) {
            if (visited[i] == 0 && curSum + nums[i] <= target) {
                visited[i] = 1;
                if (canPartited(i + 1, nums, visited, remain, curSum + nums[i], target)) {
                    return true;
                }
                visited[i] = 0;
            }
        }

        return false;
    }


}
