package leetcode.ScanWindow;
/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character , Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int ret = 0;
        while (end < s.length()) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start , map.get(s.charAt(end)) + 1);
            }
            map.put(s.charAt(end) , end);
            ret = Math.max(ret , end - start + 1);
            ++end;
        }
        return ret;
    }

    public static void main (String[] args) {
        LongestSubstringWithoutRepeatingCharacters3 ls = new LongestSubstringWithoutRepeatingCharacters3();
        System.out.println(ls.lengthOfLongestSubstring("abcabcbb"));
    }
}
