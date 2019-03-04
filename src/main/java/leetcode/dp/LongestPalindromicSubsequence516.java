package leetcode.dp;


/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
Seen this question in a real interview before?

 */
/*
dp[i][j]: the longest palindromic subsequence's length of substring(i, j), here i, j represent left, right indexes in the string
State transition:
dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
Initialization: dp[i][i] = 1
 */

public class LongestPalindromicSubsequence516 {
    public int longestPalindromeSubseq(String s) {
        if(s.length() == 0 || s == null) return 0;
        //dp
        int[][] dp = new int[s.length()][s.length()];
        for(int i = s.length() - 1 ; i >= 0 ; --i){
            dp[i][i] = 1;
            for(int j = i + 1 ; j < s.length() ; ++j){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j] , dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public int longestPalindromeSubseqMY(String s) {
        int n = s.length();
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 1 ; i <= n ; ++i) {
            for (int j = n ; j >= i ; --j) {
                int c1 = s.charAt(i - 1);
                int c2 = s.charAt(j - 1);
                if (i == j) {
                    //dp[i][j] = Math.max(dp[i - 1][j + 1] + 1 , Math.max(dp[i][j + 1] , dp[i - 1][j]));
                    dp[i][j] = Math.max(dp[i - 1][j + 1] + 1 , dp[i][j + 1]);
                }else if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j + 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j + 1]);
                }
            }
        }
        int ret = 0;
        for (int i = 1 ; i <= n ; ++i) {
            ret = Math.max(ret , dp[i][i]);
        }
        return ret;
    }
}
