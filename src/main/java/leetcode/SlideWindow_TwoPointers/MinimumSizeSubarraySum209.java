package leetcode.SlideWindow_TwoPointers;

/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class MinimumSizeSubarraySum209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int j = 0;
        int n = nums.length;
        int ret = Integer.MAX_VALUE , sum = nums[0];
        while (j >= i && j < n) {
            if (sum >= s) {
                ret = Math.min(ret , j - i + 1);
                sum -= nums[i];
                ++i;
            } else {
                ++j;
                if (j < n) sum += nums[j];
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}
