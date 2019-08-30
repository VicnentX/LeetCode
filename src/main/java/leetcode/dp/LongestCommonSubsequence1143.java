package leetcode.dp;

/*
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
 */


/**
 * Intuition
 * LCS is a well-known problem, and there are similar problems here:
 *
 * 1092. Shortest Common Supersequence
 * 1062. Longest Repeating Substring
 * 516. Longest Palindromic Subsequence
 * Bottom-up DP utilizes a matrix m where we track LCS sizes for each combination of i and j.
 *
 * If a[i] == b[j], LCS for i and j would be 1 plus LCS till the i-1 and j-1 indexes.
 * Otherwise, we will take the largest LCS if we skip a charracter from one of the string (max(m[i - 1][j], m[i][j - 1]).
 * This picture shows the populated matrix for "xabccde", "ace" test case.
 * image
 *
 * Solution
 * int longestCommonSubsequence(string &a, string &b) {
 *   vector<vector<short>> m(a.size() + 1, vector<short>(b.size() + 1));
 *   for (auto i = 1; i <= a.size(); ++i)
 *     for (auto j = 1; j <= b.size(); ++j)
 *       if (a[i - 1] == b[j - 1]) m[i][j] = m[i - 1][j - 1] + 1;
 *       else m[i][j] = max(m[i - 1][j], m[i][j - 1]);
 *   return m[a.size()][b.size()];
 * }
 * Complexity Analysis
 * Runtime: O(nm), where n and m are the string sizes.
 * Memory: O(nm).
 *
 * Memory Optimization
 * You may notice that we are only looking one row up in the solution above. So, we just need to store two rows.
 *
 * int longestCommonSubsequence(string &a, string &b) {
 *   if (a.size() < b.size()) return longestCommonSubsequence(b, a);
 *   vector<vector<short>> m(2, vector<short>(b.size() + 1));
 *   for (auto i = 1; i <= a.size(); ++i)
 *     for (auto j = 1; j <= b.size(); ++j)
 *       if (a[i - 1] == b[j - 1]) m[i % 2][j] = m[(i -1) % 2][j - 1] + 1;
 *       else m[i % 2][j] = max(m[(i - 1) % 2][j], m[i % 2][j - 1]);
 *   return m[a.size() % 2][b.size()];
 * }
 * Complexity Analysis
 * Runtime: O(nm), where n and m are the string sizes.
 * Memory: O(min(n,m)).
 */

public class LongestCommonSubsequence1143 {
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0 ; i <= m ; ++i) {
            for (int j = 0 ; j <= n ; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence1143 lc = new LongestCommonSubsequence1143();
        System.out.println("the longest common subsequence length is  : ");
        System.out.println(lc.longestCommonSubsequence("dasdajgfjgjd;kjljklj", "dasfdafahgdffgljkjlk;jk"));
    }
}
