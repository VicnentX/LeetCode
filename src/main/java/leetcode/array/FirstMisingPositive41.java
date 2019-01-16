package leetcode.array;

/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

 */

/**
 * 这题的swap很重要 当心变量的改变
 */


public class FirstMisingPositive41 {

    public int firstMissingPositive(int[] nums) {
        for (int i = 0 ; i < nums.length ; ++i) {
            //nums[i] should be swapped with nums[nums[i] - 1]
            while (nums[i]  >= 1 && nums[i]  < nums.length + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(i , nums[i] - 1 , nums);
            }
        }

        for (int i = 0 ; i < nums.length ; ++i) {
            if (nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }

    private void swap (int a , int b , int[] nums) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }

    public static void main (String[] args) {
        FirstMisingPositive41 fm = new FirstMisingPositive41();
        System.out.println(fm.firstMissingPositive(new int[] {3,4,-1,1}));
    }
}
