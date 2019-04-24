package leetcode.BitManipulation;


/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

/*
Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:

In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups. XOR numbers in each group, we can find a number in either group.

Complexity:

Time: O (n)

Space: O (1)

A Corner Case:

When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min(). Therefore, the value of diff after executing diff &= -diff is still numeric_limits<int>::min(). The answer is still correct.
 */

public class SingleNumberIII260 {
    public int[] singleNum (int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }

        //get the last set bit
        diff &= -diff;

        //divided into two group according to diff
        int[] ret = new int[] {0 , 0};
        for (int num : nums) {
            if ((num & diff) == 0) {
                ret[0] ^= num;
            } else {
                ret[1] ^= num;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        SingleNumberIII260 sn = new SingleNumberIII260();
        for(int a : sn.singleNum(new int[] {1,1,5,6,3,3})) {
            System.out.println(a);
        }
    }
}
