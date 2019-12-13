package leetcode.binary_search;

/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */

public class SplitArrayLargestSum410 {
    public int splitArray(int[] nums, int m) {
        int start = Integer.MIN_VALUE;
        int end = 0;
        for (int num: nums) {
            start = Math.max(start, num);
            end += num;
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(mid, nums) <= m) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (count(start, nums) <= m) {
            return start;
        }
        return end;
    }

    private int count(int target, int[] nums) {
        int sum = 0;
        int cnt = 0;
        for (int num: nums) {
            sum += num;
            if (sum > target) {
                cnt++;
                sum = num;
            }
        }
        return cnt + 1;
    }

    public static void main(String[] args){
        SplitArrayLargestSum410 sa = new SplitArrayLargestSum410();
        System.out.println(sa.splitArray(new int[]{1,4,4} , 3));
    }
}
