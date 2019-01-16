package leetcode.SlideWindow_TwoPointers;

/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
Seen this question in a real interview before?

 */

/**
 * char[] c;
 * int start = start index string
 * int len = len of string
 * new String (c , start , len);
 */

import java.util.*;

public class SubstringwithConcatenationofAllWords30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0) return ret;

        //initialize the counts
        Map<String , Integer> counts = new HashMap<>();
        for (String str : words) {
            counts.put(str , counts.getOrDefault(str , 0) + 1);
        }

        //scan the loop;
        int n = s.length();
        int num = words.length;
        int len = words[0].length();
        char[] c = s.toCharArray();
        for (int i = 0 ; i < n - num * len + 1 ; ++i) {
            int j = 0;
            Map<String , Integer> seen = new HashMap<>();
            while(j < num) {
                String tem = new String(c, i + j * len , len);
                if (counts.containsKey(tem) && seen.getOrDefault(tem , 0) < counts.get(tem)) {
                    seen.put(tem , seen.getOrDefault(tem , 0) + 1);
                } else {
                    break;
                }
                ++j;
            }
            if (j == num) {
                ret.add(i);
            }
        }
        return ret;
    }
}
