package leetcode.string;

import java.util.*;

/*
For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""


Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.
 */



public class GreatestCommonDivisorofStrings1071 {
    public String gcdOfStrings(String s1, String s2) {
        String s3 = s1 + s2;
        String s4 = s2 + s1;
        if (!s3.equals(s4)) return "";
        int common_len = gcd(s1.length(), s2.length());
        return s1.substring(0, common_len);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }



//    这题酸的是最长的元string 而我下面这个方法
//    用到了z algorithm
//

    public String gcdOfStrings_Z_algorithm(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();
        TreeSet<Integer> set1 = IsIsConcatenation2(s1);
        TreeSet<Integer> set2 = IsIsConcatenation2(s2);
        set1.retainAll(set2);

        TreeSet<Integer> treeReverse = (TreeSet) set1.descendingSet();

        for (int i : treeReverse) {
            if (s1.substring(0,i).equals(s2.substring(0,i))) {
                return s1.substring(0,i);
            }
        }
        return "";
    }

    private TreeSet<Integer> IsIsConcatenation2(String s) {
        int[] z = getZarr(s);
        int n = z.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1 ; i < n ; ++i) {
            if (z[i] + i == n && z[i] % i == 0) {
                set.add(i);
            }
        }
        set.add(n);
        return set;
    }

    // Fills Z array for given string str[]
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

    public static void main (String[] args) {
        GreatestCommonDivisorofStrings1071 gc = new GreatestCommonDivisorofStrings1071();
        System.out.println(gc.gcdOfStrings("abcabc", "abcabcabc"));
    }
}
