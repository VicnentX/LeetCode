package leetcode.array;

/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

 */

import java.util.*;

public class FindAllNumbersDisappearedinanArray448 {
    public List<Integer> findDisappearedNumbersMethod1(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) return ret;
        for (int i = 0 ; i < nums.length ; ++i) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(i , nums[i] - 1 , nums);
            }
        }
        for (int i = 0 ; i < nums.length ; ++i) {
            if (nums[i] != i + 1) ret.add(i + 1);
        }
        return ret;
    }
    private void swap (int a , int b , int[] nums) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }

    public List<Integer> findDisappearedNumbersMethod2(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) return ret;
        for (int k : nums) {
            int val = Math.abs(k) - 1;
            if (nums[val] > 0) nums[val] = -nums[val];
        }
        for (int i = 0 ; i < nums.length ; ++i) {
            if (nums[i] > 0) ret.add(i + 1);
        }
        return ret;
    }

    public static void main (String[] args) {
        FindAllNumbersDisappearedinanArray448 fa = new FindAllNumbersDisappearedinanArray448();
        System.out.println(fa.findDisappearedNumbersMethod1(new int[] {4,3,2,7,8,2,3,1}));
        System.out.println(fa.findDisappearedNumbersMethod2(new int[] {4,3,2,7,8,2,3,1}));
    }
}
