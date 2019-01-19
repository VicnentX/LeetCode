package leetcode.dfs_and_memo;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

/**
 * 这里的map是为了后面多次测试所用
 */

import java.util.*;

public class WordBreakII140 {
    public List<String> wordBreak(String s, List<String> dict) {
        return dfs(s , dict , new HashMap<>());
    }

    private List<String> dfs(String s , List<String> dict , Map<String , List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> ret = new ArrayList<>();

        if (s.length() == 0) {
            ret.add("");
            return ret;
        }

        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> subList = dfs(s.substring(word.length()) , dict , map);
                for (String sub : subList) {
                    ret.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        map.put(s , ret);
        return ret;
    }

    public static void main (String[] args) {
        WordBreakII140 wb  = new WordBreakII140();
        //s = "pineapplepenapple"
        //wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        //[
        //  "pine apple pen apple",
        //  "pineapple pen apple",
        //  "pine applepen apple"
        //]
        System.out.println(wb.wordBreak(
                "pineapplepenapple" ,
                new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")))
        );
    }
}
