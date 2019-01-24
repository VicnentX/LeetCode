package leetcode.backtracking_TrieNode;

/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.



Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.





Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.



Example:

Input: m = 1, n = 1
Output: 9
Seen this question in a real interview before?

 */

/**
 * skip 这个函数设计很好
 */
public class AndroidUnlockPatterns351 {
    public int numberOfPatterns(int m, int n) {
        int ret = 0;
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[4][6] = skip[6][4] = skip[2][8] = skip[8][2]
                = skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        int[] visited = new int[10];
        for (int i = m ; i <= n ; ++i) {
            ret += dfs (visited , skip , 1 , i - 1) * 4;
            ret += dfs (visited , skip , 2 , i - 1) * 4;
            ret += dfs (visited , skip , 5 , i - 1);
        }
        return ret;
    }
    private int dfs (int[] visited , int[][] skip , int cur , int res) {

        if (res == 0) return 1;

        int ret = 0;
        visited[cur] = 1;
        for (int i = 1 ; i <= 9 ; ++i) {
            if (visited[i] == 0 && (skip[cur][i] == 0 || visited[skip[cur][i]] == 1)) {
                ret += dfs(visited , skip , i , res - 1);
            }
        }
        visited[cur] = 0;
        return ret;
    }
}
