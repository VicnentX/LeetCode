package leetcode.Number;

/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.



Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]


Constraints:

10 <= low <= high <= 10^9
Seen this question in a real interview before?

 */

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits1291 {
    public List<Integer> sequentialDigits(int low, int high) {
        int minDigit = Integer.toString(low).length();
        int maxDigit = Integer.toString(high).length();
        List<Integer> ret = new ArrayList<>();
        for (int len = minDigit; len <= maxDigit; ++len) {
            int sum = 0;
            for (int i = 1; i <= 9 ; ++i) {
                sum = sum * 10 + i;
                if (i >= len) {
                    if (sum >= low && sum <= high) {
                        ret.add(sum);
                    }
                    sum %= Math.pow(10, len - 1);
                }
            }
        }
        return ret;
    }
}
