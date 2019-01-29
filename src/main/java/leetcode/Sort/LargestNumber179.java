package leetcode.Sort;
/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
 */

import java.util.*;

public class LargestNumber179 {
    public String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        int i = 0;
        for (int num : nums) {
            numStr[i++] = String.valueOf(num);
        }
        Arrays.sort(numStr , (a , b) -> (b + a).compareTo(a + b) );

        if (numStr[0].charAt(0) == '0') return "0";

        StringBuilder ret = new StringBuilder();
        for (String num : numStr) {
            ret.append(num);
        }
        return ret.toString();
    }
}
