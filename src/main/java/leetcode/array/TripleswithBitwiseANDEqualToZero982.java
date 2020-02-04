package leetcode.array;

/*
Given an array of integers A, find the number of triples of indices (i, j, k) such that:

0 <= i < A.length
0 <= j < A.length
0 <= k < A.length
A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.


Example 1:

Input: [2,1,3]
Output: 12
Explanation: We could choose the following i, j, k triples:
(i=0, j=0, k=1) : 2 & 2 & 1
(i=0, j=1, k=0) : 2 & 1 & 2
(i=0, j=1, k=1) : 2 & 1 & 1
(i=0, j=1, k=2) : 2 & 1 & 3
(i=0, j=2, k=1) : 2 & 3 & 1
(i=1, j=0, k=0) : 1 & 2 & 2
(i=1, j=0, k=1) : 1 & 2 & 1
(i=1, j=0, k=2) : 1 & 2 & 3
(i=1, j=1, k=0) : 1 & 1 & 2
(i=1, j=2, k=0) : 1 & 3 & 2
(i=2, j=0, k=1) : 3 & 2 & 1
(i=2, j=1, k=0) : 3 & 1 & 2


Note:

1 <= A.length <= 1000
0 <= A[i] < 2^16
 */


/**
 * 先把两两的结果存下来，然后再和第三个比较，
 * 这样从n3 到了 n2 + n * max(nums) 因为&之后数字只会变小
 */
public class TripleswithBitwiseANDEqualToZero982 {
    public int countTriplets(int[] nums) {
        int[] count = new int[2 << 16];
        int max = 0;
        int ret = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, nums[i]);
            for (int j = 0; j < n; ++j) {
                count[nums[i] & nums[j]]++;
            }
        }
        for (int num: nums) {
            for (int k = 0; k <= max; ++k) {
                if ((num & k) == 0) {
                    ret += count[k];
                }
            }
        }
        return ret;
    }
}
