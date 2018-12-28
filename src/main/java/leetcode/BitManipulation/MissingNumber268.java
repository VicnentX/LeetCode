package leetcode.BitManipulation;

/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

public class MissingNumber268 {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int xor = 0;
        for (int i = 0 ; i < nums.length ; ++i) {
            xor ^= i ^ nums[i];
        }
        return xor ^ nums.length;
    }

    public static void main (String[] args) {
        MissingNumber268 mn = new MissingNumber268();
        System.out.println(mn.missingNumber(new int[] {1,0,3}));
    }
}
