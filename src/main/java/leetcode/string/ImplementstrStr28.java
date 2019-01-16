package leetcode.string;

/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */

public class ImplementstrStr28 {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int n = haystack.length();
        int m = needle.length();
        OUT:
        for (int i = 0 ; i < n - m + 1; ++i) {
            if (haystack.charAt(i) == needle.charAt(i - i)) {
                int start = i;
                for (i = start ; i - start < m ; ++i) {
                    if (haystack.charAt(i) != needle.charAt(i - start)) {
                        i = start;
                        continue OUT;
                    }
                }
                return start;
            }
        }
        return -1;
    }
}
