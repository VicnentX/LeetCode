package leetcode.array;

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

import java.util.*;

public class ThreeSumClosest16 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int ret = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for (int i = 0 ; i < n - 2 ; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int j = i + 1;
                int k = n - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == target) {
                        return target;
                    } else if (sum > target) {
                        --k;
                    } else {
                        ++j;
                    }
                    if (Math.abs(sum - target) < Math.abs(ret - target)) ret = sum;
                }
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        ThreeSumClosest16 ts = new ThreeSumClosest16();
        System.out.println(ts.threeSumClosest(new int[] {-1, 2, 1, -4} , 1));
    }
}
