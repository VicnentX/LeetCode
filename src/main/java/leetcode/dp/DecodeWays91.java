package leetcode.dp;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Seen this question in a real interview before?

 */

import com.google.common.annotations.VisibleForTesting;

/**
 * this problem has some tricks and be cautious about them.
 * the main idea is to check i and check substring(i - 1 , i) means s[i - 1 - > i]
 * and be cautious about if there is s[i - 2]
 * dp[i] is the ways from 0 , 1 , ... , i
 */


public class DecodeWays91 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1 ; i < s.length() ; ++i) {
            dp[i] = s.charAt(i) == '0' ? 0 : dp[i - 1];
            if (s.charAt(i - 1) != '0'
                    && Integer.parseInt(s.substring(i - 1 , i + 1)) >= 1
                    && Integer.parseInt(s.substring(i - 1 , i + 1)) <= 26) {
                dp[i] += (i >= 2 ? dp[i -2] : 1);
            }
            if (dp[i] == 0) return 0;
        }
        return dp[s.length() - 1];
    }

    public static void main (String[] args) {
        DecodeWays91 dw = new DecodeWays91();
        System.out.println(dw.numDecodings("9"));//1
        System.out.println(dw.numDecodings("27"));//1
        System.out.println(dw.numDecodings("10"));//1

    }


}
