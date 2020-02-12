package leetcode.binary_search;

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */

public class KthSmallestElementinaSortedMatrix378 {
    public int kthSmallest(int[][] grid, int k) {
        int n = grid.length;
        int start = grid[0][0];
        int end = grid[n - 1][n - 1] + 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int ans = 0;
            for (int[] row: grid) {
                ans += upperBound(row, mid);
            }
            if (ans >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private int upperBound(int[] nums, int k) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }


}
