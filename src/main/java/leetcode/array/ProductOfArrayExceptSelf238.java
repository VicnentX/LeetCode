package leetcode.array;

/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductOfArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        //第一遍的时候 ret是算的自己左边的乘积 不包括自己
        //第二遍的时候 乘上自己右边的乘积 得到结果
        int[] ret = new int[nums.length];
        ret[0] = 1;
        for (int i = 1 ; i < nums.length ; ++i) {
            ret[i] = ret[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1 ; i >= 0 ; --i) {
            ret[i] *= right;
            right *= nums[i];
        }
        return ret;
    }

    public static void main (String[] args) {
        ProductOfArrayExceptSelf238 pa = new ProductOfArrayExceptSelf238();
        for ( int k : pa.productExceptSelf(new int[] {1,2,3,4})) {
            System.out.println(k);
        }
    }
}
