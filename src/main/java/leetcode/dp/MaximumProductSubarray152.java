package leetcode.dp;

/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaximumProductSubarray152 {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1 , min = 1 , ret = nums[0];
        for (int k : nums) {
            int maxCur = Math.max(k , Math.max(k * max , k * min));
            int minCur = Math.min(k , Math.min(k * max , k * min));
            max = maxCur;
            min = minCur;
            ret = Math.max(ret , max);
        }
        return ret;
    }

    public static void main (String[] args) {
        MaximumProductSubarray152 mp = new MaximumProductSubarray152();
        System.out.println(mp.maxProduct(new int[] {2,3,-2,4}));
    }
}
