package leetcode.binary_search;

/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 */

/**
 * return the lower bound / upper bound of a val in a sorted array
 *
 * lower bound(x): first index of i, such that nums[i] >= x
 * upper bound(x): first index of i, such that nums[i] > x
 * nums = [1,2,2,2,4,4,5]
 * lower bound(2) = 1, lower bound(3) = 4 (actually there is no num is exactly 3)
 * upper bound(2) = 4, upper bound(5) = 7 (actually ehre is no such a num)
 */

/**
 * python 不用考虑超届问题 方便很多
 */

public class Sqrt_x_69 {
    public int mySqrt(int x) {

        double start = 0;
        //end 的初始值取得是我能得到的最大值 + 1
        double end = (double)x + 1;
        while (start < end) {
            int mid = (int)(start + (end - start) / 2);
            double ans = (double)mid * (double)mid;
            if (ans > x) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return (int)(start - 1);
    }
}
