package bloomreach.bloomreach_加油少年_VO;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
Seen this question in a real interview before?

 */


/**
 * 这里可以得到最长单词的长度 这样可以实际上加速一些 因为j就从i - maxlen开始就行
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak139 {

    public boolean wordBreak(String s, Set<String> wordDict) {

        //reach[j] means s from 0 - j-1 could be met
        boolean[] reach = new boolean[s.length() + 1];
        reach[0] = true;

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (reach[j] && wordDict.contains(s.substring(j, i))) {
                    reach[i] = true;
                    break;
                }
            }
        }

        return reach[s.length()];
    }

    public static void main(String[] args) {
        WordBreak139 wordBreak139 = new WordBreak139();
        //true
        System.out.println(wordBreak139.wordBreak("applepenapple", new HashSet<>(Arrays.asList("apple", "pen"))));
        //false
        System.out.println(wordBreak139.wordBreak("catsandog", new HashSet<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
    }

}
