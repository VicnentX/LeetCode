package leetcode.array;


/*
Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).



Example 1:

Input: text = "abcabcabc"
Output: 3
Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
Example 2:

Input: text = "leetcodeleetcode"
Output: 2
Explanation: The 2 substrings are "ee" and "leetcodeleetcode".


Constraints:

1 <= text.length <= 2000
text has only lowercase English letters.
 */


import java.util.HashSet;
import java.util.Set;


/*
这题要求其实是由两个组起来 不能是3个或者更多
 */

public class DistinctEchoSubstrings1316 {
    public int distinctEchoSubstrings(String text) {
        //注意这边单用ret的话，可能出现重复的情况，所以这里用set
        //int ret = 0;
        Set<String> set = new HashSet<>();
        int n = text.length();
        for (int i = 0 ; i < n; ++i) {
            for (int j = i + 2; j <= n; ++j) {
                String s = text.substring(i, j);
                if (isIsConcatenation2(s)) {
                    System.out.println(s);
                    set.add(s);
                }
            }
        }
        return set.size();
    }

    public boolean isIsConcatenation2(String s) {
        int[] z = getZarr(s);
        int n = z.length;
        for (int i = 1 ; i < n ; ++i) {
            if (z[i] + i == n && z[i] % i == 0) {
                return true;
            }
        }
        return false;
    }

    // Fills Z array for given string str
    private int[] getZarr(String str) {
        int[] Z = new int[str.length()];
        int n = str.length();

        // [L,R] make a window which matches with
        // prefix of s
        int L = 0, R = 0;

        for (int i = 1; i < n; ++i) {

            // if i>R nothing matches so we will calculate.
            // Z[i] using naive way.
            if (i > R) {

                L = R = i;

                // R-L = 0 in starting, so it will start
                // checking from 0'th index. For example,
                // for "ababab" and i = 1, the value of R
                // remains 0 and Z[i] becomes 0. For string
                // "aaaaaa" and i = 1, Z[i] and R become 5

                while (R < n && str.charAt(R - L) == str.charAt(R))
                    R++;

                Z[i] = R - L;
                R--;

            } else {

                // k = i-L so k corresponds to number which
                // matches in [L,R] interval.
                int k = i - L;

                // if Z[k] is less than remaining interval
                // then Z[i] will be equal to Z[k].
                // For example, str = "ababab", i = 3, R = 5
                // and L = 2
                if (Z[k] < R - i + 1)
                    Z[i] = Z[k];

                    // For example str = "aaaaaa" and i = 2, R is 5,
                    // L is 0
                else {


                    // else start from R and check manually
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R))
                        R++;

                    Z[i] = R - L;
                    R--;
                }
            }
        }
        return Z;
    }
}
