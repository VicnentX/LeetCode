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


import java.net.StandardSocketOptions;
import java.util.*;

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


    /*
    // Hey Yibei

    I use a 2D String array to store one of the longest subsequence between two substring
    (PS: this is only store one of them. If the question need to get all the possible longest common subsequences,
    we can use a 2D array of List<String> ,ie. the element in the 2D array is List<String>)

    so everytime I get what dp[i][j] is, I will update preString[i][j] accordingly. That makes sense, right?!

    then the result should be preString[m][n]

     */

    public String longestCommonSubsequenceString(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        String[][] preString = new String[m + 1][n + 1];
        for(String[] row: preString) {
            Arrays.fill(row, "");
        }

        for (int i = 0 ; i <= m ; ++i) {
            for (int j = 0 ; j <= n ; ++j) {
                if (i == 0 || j == 0) {
                    //do nothing
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    preString[i][j] = preString[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    preString[i][j] = preString[i - 1][j].length() > preString[i][j - 1].length() ?
                            preString[i - 1][j] : preString[i][j - 1];
                }
            }
        }
        return preString[m][n];
    }


    /*
    dfs is optional method but it is not efficient
     */
    String retDfs = "";
    public String lcsDfs(String s, String t) {
        dfs(0, 0, s, t, "");
        return retDfs;
    }

    private void dfs(int i, int j, String s, String t, String cur) {
        if (i == s.length() || j == t.length()) {
            if (cur.length() > retDfs.length()) {
                retDfs = cur;
            }
            return;
        }

        if (s.charAt(i) == t.charAt(j)) {
            dfs(i + 1, j + 1, s, t, cur + s.charAt(i));
        } else {
            dfs(i + 1, j, s, t, cur);
            dfs(i, j + 1, s, t, cur);
        }
    }

    /*
    this is output all the possible LCS

    here I use hashset since lcs[i][j - 1] and lcs[i - 1][j] will have some overlapping
     */
    public Set<String> lcsAllPossibleString(String s, String t) {
        int m = s.length();
        int n = t.length();
        Set<String>[][] lcs = new HashSet[m + 1][n + 1];
        for(Set<String>[] rows: lcs) {
            Arrays.fill(rows, new HashSet<>(Arrays.asList("")));
        }

        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 || j == 0) {
                    //do nothing
                } else if (s.charAt(i - 1) == t.charAt(j - 1)){
                    lcs[i][j] = buildNewArray(lcs[i - 1][j - 1], s.charAt(i - 1));
                } else {
                    if (lcs[i - 1][j].iterator().next().length() > lcs[i][j - 1].iterator().next().length()) {
                        lcs[i][j] = new HashSet<>(lcs[i - 1][j]);
                    } else if (lcs[i - 1][j].iterator().next().length() < lcs[i][j - 1].iterator().next().length()) {
                        lcs[i][j] = new HashSet<>(lcs[i][j - 1]);
                    } else {
                        lcs[i][j] = new HashSet<>();
                        lcs[i][j].addAll(new HashSet<>(lcs[i - 1][j]));
                        if (lcs[i][j - 1].iterator().next().length() != 0) {
                            lcs[i][j].addAll(new HashSet<>(lcs[i][j - 1]));
                        }
                    }
                }
            }
        }

        return lcs[m][n];
    }

    private Set<String> buildNewArray(Set<String> array, char c) {
        Set<String> ret = new HashSet<>();
        for(String pre: array) {
            ret.add(pre + c);
        }
        return ret;
    }


    public static void main(String[] args) {
        LongestCommonSubsequence1143 lc = new LongestCommonSubsequence1143();
        System.out.println("the longest common subsequence length is  : ");
        System.out.println(lc.longestCommonSubsequence("dasdajgfjgjd;kjljklj", "dasfdafahgdffgljkjlk;jk"));

        System.out.println("the longest common subsequence length is  : ");
        System.out.println(lc.longestCommonSubsequenceString("hello worlaaad", "woddddrld champion"));

        System.out.println("the longest common subsequence length is  : ");
        System.out.println(lc.lcsDfs("hello worlaaad", "woddddrld champion"));

        System.out.println("the longest common subsequence length is  : ");
        Set<String> ret = lc.lcsAllPossibleString("hello worlaaad", "woddddrld champion");
        for(String s: ret) {
            System.out.println(s);
        }
    }
}
