package leetcode.array;

/*
Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
 */

/**
 * O(n) supposed to be perform
 */

public class ArrayPartitionI561 {
    public int arrayPairSum(int[] nums) {
        int[] arr = new int[20001];
        for (int k : nums) {
            ++arr[k + 10000];
        }
        boolean odd = true;
        int ret = 0;
        for (int i = 0 ; i < 20001 ; ++i) {
            while(arr[i] > 0) {
                if (odd) ret += (i - 10000);
                odd = !odd;
                --arr[i];
            }
        }
        return ret;
    }
}
