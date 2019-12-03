package google.google2020.google_加油少年_OnS;

/*
Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
 */

public class MaxChunksToMakeSorted769 {
    public int maxChunksToSorted(int[] arr) {
        //build dp: dp[i] means the largest num from index of 0 - i
        int[] dp = new int[arr.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            dp[i] = max = Math.max(max, arr[i]);
        }
        //get result
        int tempMin = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = arr.length - 1; i >=0 ; --i) {
            tempMin = Math.min(tempMin, arr[i]);
            if (i == 0) {
                cnt++;
                break;
            }
            if (tempMin > dp[i - 1]) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSorted769 maxChunksToMakeSorted769 = new MaxChunksToMakeSorted769();
        //4
        System.out.println(maxChunksToMakeSorted769.maxChunksToSorted(new int[] {1,0,2,3,4}));
    }
}
