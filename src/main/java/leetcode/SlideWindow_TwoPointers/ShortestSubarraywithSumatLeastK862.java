package leetcode.SlideWindow_TwoPointers;

/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.



Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3


Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Prepare
 * From @Sarmon:
 * "What makes this problem hard is that we have negative values.
 * If you haven't already done the problem with positive integers only,
 * I highly recommend solving it first"
 *
 * Minimum Size Subarray Sum
 *
 * Explanation
 * Calculate prefix sum B of list A.
 * B[j] - B[i] represents the sum of subarray A[i] ~ A[j-1]
 * Deque d will keep indexes of increasing B[i].
 * For every B[i], we will compare B[i] - B[d[0]] with K.
 *
 *
 * Complexity:
 * Every index will be pushed exactly once.
 * Every index will be popped at most once.
 *
 * Time O(N)
 * Space O(N)
 *
 *
 * How to think of such solutions?
 * Basic idea, for array starting at every A[i], find the shortest one with sum at leat K.
 * In my solution, for B[i], find the smallest j that B[j] - B[i] >= K.
 * Keep this in mind for understanding two while loops.
 *
 *
 * What is the purpose of first while loop?
 * For the current prefix sum B[i], it covers all subarray ending at A[i-1].
 * We want know if there is a subarray, which starts from an index, ends at A[i-1] and has at least sum K.
 * So we start to compare B[i] with the smallest prefix sum in our deque, which is B[D[0]], hoping that [i] - B[d[0]] >= K.
 * So if B[i] - B[d[0]] >= K, we can update our result res = min(res, i - d.popleft()).
 * The while loop helps compare one by one, until this condition isn't valid anymore.
 *
 *
 * Why we pop left in the first while loop?
 * This the most tricky part that improve my solution to get only O(N).
 * D[0] exists in our deque, it means that before B[i], we didn't find a subarray whose sum at least K.
 * B[i] is the first prefix sum that valid this condition.
 * In other words, A[D[0]] ~ A[i-1] is the shortest subarray starting at A[D[0]] with sum at least K.
 * We have already find it for A[D[0]] and it can't be shorter, so we can drop it from our deque.
 *
 *
 * What is the purpose of second while loop?
 * To keep B[D[i]] increasing in the deque.
 *
 *
 * Why keep the deque increase?
 * If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
 * B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.
 */

public class ShortestSubarraywithSumatLeastK862 {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int ret = n + 1;
        //dp[i] means the sum of nums[0] to nums[i - 1]
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            dp[i + 1] = dp[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i <= n; ++i) {
            while (deque.size() > 0 && dp[i] - dp[deque.getFirst()] >= k) {
                ret = Math.min(ret, i - deque.pollFirst());
            }
            //这里我的理解就是从j到i- 1这一段是小于零的话 最优解答就不会用j开始 因为这样是不如从i开始的
            while (deque.size() > 0 && dp[i] - dp[deque.getLast()] <= 0) {
                deque.pollLast();
            }
            deque.add(i);
        }

        return ret == n + 1 ? -1 : ret;
    }
}
