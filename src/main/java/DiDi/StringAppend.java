package DiDi;

/*
第三题是给一个String array，
然后找出最长的拼接字符串S的长度，
S里不能有重复字母，
比如{abc, cba, def, gha, gi}可以拼出abcdefgi，
那么就返回8。可以直接dfs暴力，题目说效率不作要求
 */

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.*;

class Solution {

    private int longest = 0;

    public int solution(String[] A) {
        // write your code in Java SE 8
        //map key is the index of the input for dfs, value is array whose size = 26 to record the letter of this word
        Map<Integer, int[]> map = new HashMap<>();
        int index = 0;
        OUT:
        for (String s: A) {
            if (s.length() <= 26) {
                int[] temp = new int[26];
                for (char c: s.toCharArray()) {
                    temp[c - 'a']++;
                    if (temp[c - 'a'] == 2) continue OUT;
                }
                map.put(index, temp);
                index++;
            }
        }

        dfs(0, map.size(), new int[26], 0, map);

        return longest;
    }

    private void dfs(int i, int n, int[] curArray, int curLen, Map<Integer, int[]> map) {

        longest = Math.max(longest, curLen);

        if (i == n) {
            return;
        }
        //not choose ith string
        dfs(i + 1, n, curArray, curLen, map);
        //try to use ith string
        int[] add = map.get(i);
        int[] sum = new int[26];
        int cnt = 0;
        for (int j = 0; j < 26; ++j) {
            int temp = curArray[j] + add[j];
            if ( temp >= 2) return;
            sum[j] = temp;
            if (temp == 1) cnt++;
        }
        dfs(i + 1, n, sum, cnt, map);
    }
}

