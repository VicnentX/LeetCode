package leetcode.PriorityQueue;

/*
Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not neccesarilly a number from arr.



Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
Example 2:

Input: arr = [2,3,5], target = 10
Output: 5
Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class SumofMutatedArrayClosesttoTarget1300 {
    //这题就是用平均的方法
    public int findBestValue(int[] nums, int target) {
        int n = nums.length;
        int end = Integer.MIN_VALUE;
        int start = 0;
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for (int num: nums) {
            end = Math.max(end, num);
            sum += num;
            pq.add(num);
        }
        //这里的pq sum target 就是我需要用的
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int newSum = getSum(new PriorityQueue<>(pq), sum, mid);
            if (newSum == target) return mid;
            if (newSum < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //get result
        int a = getSum(new PriorityQueue<>(pq), sum, start);
        int b = getSum(new PriorityQueue<>(pq), sum, end);
        if (Math.abs(a - target) <= Math.abs(b - target)) {
            return start;
        } else {
            return end;
        }
    }

    private int getSum(PriorityQueue<Integer> pq, int sum, int mid) {
        while (!pq.isEmpty()) {
            int num = pq.poll();
            if (num <= mid) break;
            sum -= (num - mid);
        }
        return sum;
    }

    public static void main(String[] args) {
        SumofMutatedArrayClosesttoTarget1300 sm = new SumofMutatedArrayClosesttoTarget1300();
        //5
        System.out.println(sm.findBestValue(new int[] {2,3,5}, 10));
        //11361
        System.out.println(sm.findBestValue(new int[] {60864,25176,27249,21296,20204}, 56803));
    }
}
