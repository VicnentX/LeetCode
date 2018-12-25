package leetcode.array;


/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

/**
 * keep i is different for every loop , move j and k pointer
 *
 */

import java.util.*;

public class ThreeSum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        while (i < n - 2) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (nums[j] + nums[k] == -nums[i]) {
                    ret.add(Arrays.asList(nums[i] , nums[j] , nums[k]));
                    ++j;
                    --k;
                    while (k > j && nums[j] == nums[j - 1]) {
                        ++j;
                    }
                    while (k > j && nums[k] == nums[k + 1]) {
                        --k;
                    }
                }
                else if (nums[j] + nums[k] > -nums[i]) {
                    --k;
                }
                else {
                    ++j;
                }
            }
            ++i;
            while (i < n - 2 && nums[i] == nums[i - 1]) {
                ++i;
            }
        }
        return ret;
        // Arrays.sort(num);
        // List<List<Integer>> res = new LinkedList<>();
        // for (int i = 0; i < num.length-2; i++) {
        //     if (i == 0 || (i > 0 && num[i] != num[i-1])) {
        //         int lo = i+1, hi = num.length-1, sum = 0 - num[i];
        //         while (lo < hi) {
        //             if (num[lo] + num[hi] == sum) {
        //                 res.add(Arrays.asList(num[i], num[lo], num[hi]));
        //                 while (lo < hi && num[lo] == num[lo+1]) lo++;
        //                 while (lo < hi && num[hi] == num[hi-1]) hi--;
        //                 lo++; hi--;
        //             } else if (num[lo] + num[hi] < sum) lo++;
        //             else hi--;
        //        }
        //     }
        // }
        // return res;
    }

    public static void main (String[] args) {
        ThreeSum15 ts = new ThreeSum15();
        for (List<Integer> list : ts.threeSum(new int[] {-1, 0, 1, 2, -1, -4})) {
            System.out.println(list);
        }
    }
}
