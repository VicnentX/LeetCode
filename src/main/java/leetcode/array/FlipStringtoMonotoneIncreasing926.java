package leetcode.array;

/*
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.



Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.


Note:

1 <= S.length <= 20000
S only consists of '0' and '1' characters.
 */

import java.lang.reflect.Array;
import java.util.Arrays;

public class FlipStringtoMonotoneIncreasing926 {
    public int minFlipsMonoIncr(String s) {
        int n = s.length() + 2;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1 ; i < n - 1; ++i) {
            left[i] = left[i - 1] + s.charAt(i - 1)  - '0';
        }
        for (int i = n - 2; i >= 1; --i) {
            right[i] = right[i + 1] + '1' - s.charAt(i - 1);
        }

        int ret = n;
        for (int i = 0; i < n - 1; ++i) {
            ret = Math.min(ret, left[i] + right[i + 1]);
        }
        return ret;
    }

    public int solve(String s) {
        int n = s.length();
        int[] zero = new int[n];
        //initial
        Arrays.fill(zero, Integer.MAX_VALUE);
        int[] one = new int[n];
        Arrays.fill(one, Integer.MAX_VALUE);
        if (s.charAt(0) == '0') {
            zero[0] = 0;
            one[0] = 1;
        } else {
            zero[0] = 1;
            one[0] = 0;
        }
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '0') {
                zero[i] = zero[i - 1];
                one[i] = Math.min(zero[i - 1], one[i - 1]) + 1;
            } else {
                zero[i] = zero[i - 1] + 1;
                one[i] = Math.min(one[i - 1], zero[i - 1]);
            }
        }
        return Math.min(zero[n - 1], one[n - 1]);
    }
}
