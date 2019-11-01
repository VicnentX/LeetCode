package google.google2020.google_加油少年_OnS;

/*
给定两个字串S, T，求最少多少个T的子序列可以组成S。比如S是"aaa", T是"bab", 答案就是3。区间动态规划。
 */

import java.util.Arrays;

/**
 *  * 这个要用一个map 就是一个二位矩阵
 *  * 记录下一个最近的这个单词出现的地方
 */

public class ConcatenateSwithT {

    public int pieceToConstruct(String T , String S) {
        final int N = T.length();
        int[][] map = new int[26][N];
        //O(26 * N) = O(N)
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(map[i], -1);
        }
        //O(N)
        for (int i = 0; i < N; ++i) {
            char cur = T.charAt(i);
            map[cur - 'a'][i] = i;
        }
        //O(26 * N) = O(N)
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

        int cnt = 1;


        int index = 0;
        for (char c: S.toCharArray()) {
            if (index == N || map[c - 'a'][index] == -1) {
                cnt++;
                index = 0;
                if (map[c - 'a'][index] == -1) {
                    System.out.println("IMPOSSIBLE");
                    return Integer.MAX_VALUE;
                }
            }
            index = map[c - 'a'][index] + 1;
        }

        return cnt;
    }

    public static void main(String[] args) {
        ConcatenateSwithT concatenateSwithT = new ConcatenateSwithT();
        System.out.println(concatenateSwithT.pieceToConstruct("bab","aaa"));
        System.out.println(concatenateSwithT.pieceToConstruct("abc","abd"));
    }

}
