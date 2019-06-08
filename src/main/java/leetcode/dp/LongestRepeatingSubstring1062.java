package leetcode.dp;

/*
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500
 */

public class LongestRepeatingSubstring1062 {
    //dp[i][j] means ending at i - 1 and j - 1
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int ret = 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1 ; i < n + 1 ; ++i) {
            for (int j = i + 1 ; j < n + 1 ; ++j) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ret = Math.max(ret , dp[i][j]);
                }
            }
        }
        return ret;
    }
}
