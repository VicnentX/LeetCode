package google.google2020.google_加油少年_OnS;

/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */

import google.google2020.google_加油少年_VO.LongestCommonSubstring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestWordinDictionarythroughDeleting524 {
    public String findLongestWord(String s, List<String> d) {

        if (s.length() == 0 || d.size() == 0) return "";
        final int N = s.length();
        int[][] map = new int[26][N];
        //O(26 * N) = O(N)
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(map[i], -1);
        }
        //
        for (int i = 0; i < N; ++i) {
            map[s.charAt(i) - 'a'][i] = i;
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

        //get the result
        String ret = "";

        OUT:
        for (String word: d) {
            int index = 0;
            for (char c: word.toCharArray()) {
                if (index == N || map[c - 'a'][index] == -1) continue OUT;
                index = map[c - 'a'][index] + 1;
            }
            //it comes here means c is valid then compare it with ret to update ret if needed
            /**
             *   这里compareTo的用法 学习了
             */
            if (word.length() > ret.length() || word.length() == ret.length() && word.compareTo(ret) < 0) {
                ret = word;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        LongestWordinDictionarythroughDeleting524 lwd = new LongestWordinDictionarythroughDeleting524();
        List<String> words = new ArrayList<>();
        words.add("c");
        words.add("b");
        words.add("a");
        System.out.println(lwd.findLongestWord("abpcplea", words));
        System.out.println("-------------------");

        words = new ArrayList<>();
        words.add("ale");
        words.add("apple");
        words.add("monkey");
        words.add("plea");
        System.out.println(lwd.findLongestWord("abpcplea", words));
        System.out.println("-------------------");

        words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        System.out.println(lwd.findLongestWord("foobarfoobar", words));
        System.out.println("-------------------");
    }
}
