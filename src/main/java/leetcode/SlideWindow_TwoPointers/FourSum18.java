package leetcode.SlideWindow_TwoPointers;

/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
Seen this question in a real interview before?

 */

import java.util.*;

public class FourSum18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) return ret;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0 ; i < n - 3 ; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1 ; j < n - 2 ; ++j) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int lo = j + 1;
                int hi = n - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum == target) {
                        ret.add(new ArrayList<>(Arrays.asList(nums[i] , nums[j] , nums[lo] , nums[hi])));
                        while (lo < hi && nums[lo] == nums[lo + 1]) ++lo;
                        while (lo < hi && nums[hi] == nums[hi - 1]) --hi;
                        ++lo;
                        --hi;
                    } else if (sum < target) {
                        ++lo;
                    } else {
                        --hi;
                    }
                }
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        FourSum18 fs = new FourSum18();
        System.out.println(fs.fourSum(new int[] {0 , 0 , 0 ,0} , 0));
    }
}
