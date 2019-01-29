package leetcode.HARD;

/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.
 */

/*
Suppose you have n pigeons with labels and you put them into m holes
based on their label with each hole of the same size.
Why bother putting pigeons into holes?
Because you want to disregard the distance between pigeons within each one hole.

Only when at least one hole is empty
can we disregard the distance between pigeons within each one hole
and
compute the maximum gap solely by the distance between pigeons in adjacent holes.
We make sure that at least one hole is empty by using m=n-1
(i.e. n-2 pigeons in n-1 holes => at least one hole is empty).
 */

import java.util.Arrays;

public class MaximumGap164 {
    public int maximumGap(int[] nums) {
        int ret = 0;
        if (nums.length <= 1) return ret;
        int min = nums[0] , max = nums[0];
        for (int num : nums) {
            max = Math.max(max , num);
            min = Math.min(min , num);
        }
        int n = nums.length;
        double gap = (double)(max - min) / (n - 1);
        int[] bucketMax = new int[n];
        int[] bucketMin = new int[n];
        Arrays.fill(bucketMax , Integer.MIN_VALUE);
        Arrays.fill(bucketMin , Integer.MAX_VALUE);

        for (int num : nums) {
            int index = (int) ((num - min) / gap);
            bucketMax[index] = Math.max(bucketMax[index] , num);
            bucketMin[index] = Math.min(bucketMin[index] , num);
        }

        int pre = min;
        ret = Integer.MIN_VALUE;
        for (int i = 0 ; i < n ; ++i) {
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            ret = Math.max(ret , bucketMin[i] - pre);
            pre = bucketMax[i];
        }
        return ret;
    }

    public static void main (String[] args) {
        MaximumGap164 mg = new MaximumGap164();
        System.out.println(mg.maximumGap(new int[] {1,1,1,1,1,1,3,6,6,6,6}));
    }
}
