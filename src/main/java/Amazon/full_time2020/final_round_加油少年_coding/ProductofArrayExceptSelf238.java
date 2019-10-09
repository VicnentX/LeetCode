package Amazon.full_time2020.final_round_加油少年_coding;

/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductofArrayExceptSelf238 {
    //ret is the result
    //first for loop res[i] means product of all the number which index < i
    //second for loop res[i] is the result = all left product * all right product

    /**
     *
     *
     * clarify :
     *      is there any 0 in the array?
     *  这种方法还可以avoid element = 0 的情况
     *
     *
     * @param nums
     * @return
     */


    public int[] productExceptSelf(int[] nums) {

        final int N = nums.length;
        int[] ret = new int[N];
        ret[0] = 1;

        for (int i = 1; i < N; ++i) {
            ret[i] = ret[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = N - 1; i >= 0; --i) {
            ret[i] *= right;
            right *= nums[i];
        }

        return ret;

    }
}
