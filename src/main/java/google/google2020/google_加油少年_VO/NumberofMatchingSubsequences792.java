package google.google2020.google_加油少年_VO;

/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
 */

import java.util.Arrays;

/**
 * 这个要用一个map 就是一个二位矩阵
 * 记录下一个最近的这个单词出现的地方
 *
 */

public class NumberofMatchingSubsequences792 {
    public int numMatchingSubseq(String S, String[] words) {
        final int N = S.length();
        int[][] map = new int[26][N];
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(map[i], -1);
        }

        for (int i = 0; i < N; ++i) {
            char cur = S.charAt(i);
            map[cur - 'a'][i] = i;
        }
        for (int i = 0; i < 26; ++i) {
            int pre = map[i][N - 1];
            for (int j = N - 2; j >= 0; --j) {
                if (map[i][j] == -1) {
                    map[i][j] = pre;
                } else {
                    pre = map[i][j];
                }
            }
        }

        int ret = 0;
        //check word one by one
        OUT:
        for (String word: words) {
            int index = 0;
            for (char c: word.toCharArray()) {
                if (index == N || map[c - 'a'][index] == -1) continue OUT;
                index = map[c - 'a'][index] + 1;
            }
            ret++;
        }

        return ret;
    }
}
