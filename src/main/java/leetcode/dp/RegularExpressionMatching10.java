package leetcode.dp;

/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

public class RegularExpressionMatching10 {
    //method recursion
    public boolean isMatch(String s , String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //return 的第一种情况是p的莫两位什么也不表示，第二种情况是p的莫两位表示1或多位
            return isMatch(s , p.substring(2)) || !s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && isMatch(s.substring(1) , p);
        } else {
            //return 的就是p的第一位与s的第一位抵消
            return !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0)) && isMatch(s.substring(1) , p.substring(1));
        }
    }

    //method DP
    public boolean isMatchDP(String s , String p) {
        //dp[i][j] = true means s(0 , 1, ... , i - 1) matches p(0 , 1 , ... , j - 1)
        if (s != null && p == null) return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0 ; i <= m ; ++i) {
            for (int j = 1 ; j <= n ; ++j) {
                if(p.charAt(j - 1) == '*') {
                    //两种情况分别是 p.charAt(j - 1) p.charAt(j - 2)什么也不表示，还有就是表示1或多位
                    dp[i][j] = dp[i][j - 2] || i >= 1 && dp[i - 1][j] && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1));
                } else {
                    //p.charAt(j - 1)与s.charAt(i - 1)抵消
                    dp[i][j] = i >= 1 && dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1));
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatchTest(String s, String p) {
        if ((s != null && p == null) || (s.length() != 0 && p.length() == 0)) return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0 ; i <= m ; ++i) {
            for (int j = 1 ; j <= n ; ++j) {
                if (j >= 2 && p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || i >= 1 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];
                } else {
                    dp[i][j] = i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main (String[] args) {
        RegularExpressionMatching10 re = new RegularExpressionMatching10();
//        System.out.println(re.isMatch("mississippi" ,  "mis*is*p*."));
//        System.out.println(re.isMatchDP("mississippi" ,  "mis*is*p*."));
//        System.out.println(re.isMatchDP("aa" ,  "a*"));
        System.out.println(re.isMatchTest("aa" ,  "a*"));
    }
}
