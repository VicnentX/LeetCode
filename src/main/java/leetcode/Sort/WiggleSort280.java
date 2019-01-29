package leetcode.Sort;

/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
Seen this question in a real interview before?

 */

public class WiggleSort280 {
    public void wiggleSort(int[] nums) {
        if (nums.length == 0) return ;
        int n = nums.length;
        for (int i = 1 ; i < n ; ++i) {
            if (i % 2 == 1) {
                if (nums[i] < nums[i - 1]) {
                    swap(i , i - 1 , nums);
                }
            } else {
                if (nums[i] > nums[i - 1]) {
                    swap(i , i - 1 , nums);
                }
            }
        }
        return;
    }
    private void swap (int a , int b , int[] nums) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }
}
