package leetcode.dp;

/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */

/**
 * this problem could be solved by dp
 * dp[i] represents the longest valid parentheses from index 0 - i
 * the core idea is check the i - 1
 * if(s[i] is '(') ---> dp[i] = 0;
 *
 * if s[i] is ')' -->
 *         if s[i - 1] is '(' ---> dp[i] = dp[i - 2] + 2
 *
 *         else if s[i - 1] is ')' ---->
 *                                       if s[i - dp[i - 1] - 1] == '(' ---> dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
 */

public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        int ret = 0;
        if (s == null || s.length() == 0) return ret;
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 1 ; i < n ; ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = i - dp[i - 1] - 2 >= 0 ? dp[i - 1] + 2 + dp[i - dp[i - 1] - 2] : dp[i - 1] + 2;
                    }
                }
            }
            ret = Math.max(ret , dp[i]);
        }
        return ret;
    }

    public static void main (String[] args) {
        LongestValidParentheses32 lv = new LongestValidParentheses32();
        System.out.println(lv.longestValidParentheses(")()())"));
    }
}
