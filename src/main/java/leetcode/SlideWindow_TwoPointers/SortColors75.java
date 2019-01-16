package leetcode.SlideWindow_TwoPointers;

/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
Seen this question in a real interview before?

 */

public class SortColors75 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return ;
        int i = 0 , j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 1) {
                ++j;
            } else if (nums[j] == 0) {
                swap (i , j , nums);
                ++i;
                ++j;
            } else if (nums[j] == 2) {
                swap (j , k , nums);
                --k;
            }
        }
        return;
    }
    private void swap (int i , int j , int[] nums) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }

    public static void main (String[] args) {
        SortColors75 sc = new SortColors75();
        int[] nums = new int[] {2,0,2,1,1,0};
        sc.sortColors(nums);
        for (int k : nums) {
            System.out.println(k);
        }
    }
}
