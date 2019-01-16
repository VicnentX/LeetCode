package leetcode.SlideWindow_TwoPointers;
/*
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
 */

import com.apple.eawt.SystemSleepListener;

/**
 * The idea is always keep an max-product-window less than K;
 * Every time shift window by adding a new number on the right(j), if the product is greater than k, then try to reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
 * Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
 * example:
 * for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))
 *         (6)
 *      (2, 6)
 *   (5, 2, 6)
 */

public class SubarrayProductLessThanK713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ret = 0;
        if (nums == null || nums.length == 0) return ret;
        int n = nums.length , prod = 1;
        for (int i = 0 , j = 0 ; j < n ; ++j) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i++];
            }
            ret += j - i + 1;
        }
        return ret;
    }

    public static void main (String[] args) {
        SubarrayProductLessThanK713 sp = new SubarrayProductLessThanK713();
        System.out.println(sp.numSubarrayProductLessThanK(new int[] {10, 5, 2, 6} , 100));
    }
}
