package leetcode.dp;

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
}
