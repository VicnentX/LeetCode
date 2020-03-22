package leetcode.dp;

/*
We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
 */

import java.util.Arrays;

public class MinimumSwapsToMakeSequencesIncreasing801 {
    public int minSwap(int[] a, int[] b) {
        int n = a.length;
        int[] keep = new int[n];
        Arrays.fill(keep, Integer.MAX_VALUE);
        int[] swap = new int[n];
        Arrays.fill(swap, Integer.MAX_VALUE);
        keep[0] = 0;
        swap[0] = 1;
        for(int i = 1; i < n; ++i) {
            if (a[i] > a[i - 1] && b[i] > b[i - 1]) {
                keep[i] = keep[i - 1];
                swap[i] = swap[i - 1] + 1;
            }
            if (a[i] > b[i - 1] && b[i] > a[i - 1]) {
                keep[i] = Math.min(keep[i], swap[i - 1]);
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
            }
        }
        return Math.min(swap[n - 1], keep[n - 1]);
    }
}
