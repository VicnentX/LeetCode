package Mathworks.MathworksVO;

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

Your algorithm should run in O(n) time and uses constant extra space.

Seen this question in a real interview before?

 */

/**
 * Put each number in its right place.
 *
 * For example:
 *
 * When we find 5, then swap it with A[4].
 *
 * At last, the first place where its number is not right, return the place + 1.
 */

public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {
        final int N = nums.length;
        //swap the array if needed
        for (int i = 0; i < N; ++i) {
            while(nums[i] > 0 && nums[i] <= N && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        //check the missing positive number
        for (int i = 0; i < N; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //all the positive from 1 to N found , return N + 1
        return N + 1;
    }

    public int firstMissingPositive3232(int[] nums) {
        final int N = nums.length;
        //swap the array if needed
        for (int i = 0; i < N; ++i) {
            while (nums[i] >= 1 && nums[i] <= N && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        //check missing positive number
        for (int i = 0; i < N; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        //all are found
        return N + 1;
    }



    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive41 firstMissingPositive41 = new FirstMissingPositive41();
        System.out.println(firstMissingPositive41.firstMissingPositive(new int[] {3,4,-1,1}));
        System.out.println(firstMissingPositive41.firstMissingPositive(new int[] {1,2,3,4}));
        System.out.println(firstMissingPositive41.firstMissingPositive(new int[] {0,1,2,0}));
    }
}
