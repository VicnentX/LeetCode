package leetcode.dp;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */

/**
 * dp[i][j] means s.substring(i , j + 1 ) is valid palindromic substring
 */

public class LongestPalindromicSubstring5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        String ret = "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1 ; i >= 0 ; --i) {
            for (int j = i ; j < n ; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > ret.length()) {
                    ret = s.substring(i , j + 1);
                }
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        LongestPalindromicSubstring5 lp = new LongestPalindromicSubstring5();
        System.out.println(lp.longestPalindrome("babad"));
    }
}

