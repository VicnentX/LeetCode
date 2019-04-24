package leetcode.dp;

/*
Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.


Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.


Note:

L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
Seen this question in a real interview before?

 */

import java.util.*;

public class MaximumSumofTwoNonOverlappingSubarrays1031 {
    public int maxSumTwoNoOverlap(int[] a, int L, int M) {
        int ret = 0;
        int[] Lleft = dp(a , 0 , 1 , L);
        int[] Lright = dp(a , a.length - 1 , -1 , L);
        int[] Mleft = dp(a , 0 , 1 , M);
        int[] Mright = dp(a , a.length - 1, -1 , M);
        for (int i = 0 ; i < a.length - 1 ; ++i) {
            ret = Math.max(ret , Math.max(Lleft[i] + Mright[i + 1] , Mleft[i] + Lright[i + 1]));
        }
        return ret;
    }

    private int[] dp(int[] nums , int start , int step , int dis) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, Integer.MIN_VALUE);
        int sum = 0;
        for (int i = start ; i >= 0 && i < n ; i = i + step) {
            sum += nums[i];
            if (Math.abs(i - start) == dis - 1) {
                ret[i] = sum;
            } else if (Math.abs(i - start) > dis - 1) {
                sum -= nums[i - step * dis];
                ret[i] = Math.max(ret[i - step] , sum);
            }
        }
        return ret;
    }
}
