package leetcode.array;

/*
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.



Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]


Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
 */

/**
 * //map 's key is the sum , value is the amount of this sum;
 * //if a[i to j] % k == a[i to m] % k so that we can know a[j + 1 to m] can be divisible by k
 *
 * About the problems - sum of contiguous subarray , prefix sum is a common technique.
 * Another thing is if sum[0, i] % K == sum[0, j] % K, sum[i + 1, j] is divisible by by K.
 * So for current index j, we need to find out how many index i (i < j) exit that has the same mod of K.
 * Now it easy to come up with HashMap <mod, frequency>
 *
 * Time Complexity: O(N)
 * Space Complexity: O(K)
 */

import java.util.*;

public class SubarraySumsDivisiblebyK974 {
    public int subarraysDivByK(int[] A, int K) {
        //map 's key is the sum , value is the amount of this sum;
        //if a[i to j] % k == a[i to m] % k so that we can know a[j + 1 to m] can be divisible by k
        Map<Integer , Integer> map = new HashMap<>();
        map.put(0 , 1);
        int residue = 0 , ret = 0;
        for (int num : A) {
            residue = (residue + num) % K;
            if (residue < 0) residue += K;
            ret += map.getOrDefault(residue , 0);
            map.put(residue , map.getOrDefault(residue , 0) + 1);
        }
        return ret;
    }
}
