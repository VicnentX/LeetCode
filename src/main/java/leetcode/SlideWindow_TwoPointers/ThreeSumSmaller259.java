package leetcode.SlideWindow_TwoPointers;


/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
 */

/**
 * i think it is hard.
 */

import java.util.*;
public class ThreeSumSmaller259 {
    public int threeSumSmaller(int[] nums, int target) {
        int ret = 0;
        if (nums.length < 3) return ret;

        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0 ; i < n - 2 ; ++i) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    ret += right - left;
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return ret;
    }
}
