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

import java.util.*;

public class WordBreak139 {

    public boolean wordBreak(String s, Set<String> wordDict) {

        final int N = s.length();
        //reach[i] mean all the characters from index 0 - i-1 matched
        boolean[] reach = new boolean[N + 1];
        reach[0] = true;
        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j < i; ++j) {
                if (reach[j] && wordDict.contains(s.substring(j,i))) {
                    reach[i] = true;
                    break;
                }
            }
        }
        return reach[N];
    }

    //考我的是要输出一个可能的解答，并且我得到的是输入中间是有空格的
    //所以我应该先clarify一些问题：
        //比如dictionary里面是不是都是完整的次 会不会出现blue_h , at  这样的情况
    public List<String> solve(String s, Set<String> wordDict) {
        final int N = s.length();
        //build and initialize the combination array
        List<String>[] combination = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; ++i) {
            combination[i] = new ArrayList<>();
        }
        //reach[j] means s from 0 - j-1 could be met
        boolean[] reach = new boolean[N + 1];
        reach[0] = true;

        for (int i = 1; i <= N; ++i) {
            //avoid there is duplicate in the combination[] , but it does not matter actually
            if (s.charAt(i - 1) == ' ') continue;
            for (int j = 0; j < i; ++j) {
                //这里trim是因为有空格
                String partitionString = s.substring(j, i).trim();
                if (reach[j] && wordDict.contains(partitionString)) {
                    reach[i] = true;
                    List<String> temp = new ArrayList<>(combination[j]);
                    temp.add(partitionString);
                    combination[i] = new ArrayList<>(temp);
                    break;
                }
            }
        }

        //test combination
        for (int i = 0; i <= N; ++i) {
            for (String com: combination[i]) {
                System.out.print("this is " + i + " line : " + com + " + ");
            }
            System.out.println();
        }

        return combination[N];
    }

    public static void main(String[] args) {
        WordBreak139 wordBreak139 = new WordBreak139();
//        //true
//        System.out.println(wordBreak139.wordBreak("applepenapple", new HashSet<>(Arrays.asList("apple", "pen"))));
//        //false
//        System.out.println(wordBreak139.wordBreak("catsandog", new HashSet<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
//        //
//        List<String> ret = wordBreak139.solve("applepenapple", new HashSet<>(Arrays.asList("apple", "pen")));
//        for (String s: ret) {
//            System.out.println(s);
//        }
//        System.out.println("--------------------");

        //
        List<String> ret = wordBreak139.solve("blue round hat", new HashSet<>(Arrays.asList("blue", "round hat")));
        for (String s: ret) {
            System.out.println(s);
        }
        System.out.println("--------------------");

        List<String> ret2 = wordBreak139.solve("blue round hat", new HashSet<>(Arrays.asList("blue", "round", "hat")));
        for (String s: ret2) {
            System.out.println(s);
        }
        System.out.println("--------------------");
    }

}
