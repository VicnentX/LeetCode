package Amazon.full_time2020.final_round_加油少年_coding;


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
Seen this question in a real interview before?

 */


import java.util.HashMap;
import java.util.Map;

/**
 * the basic idea is,
 * keep a hashmap which stores the characters in string as keys
 * and
 * their positions as values,
 * and keep two pointers which define the max substring.
 * move the right pointer to scan through the string ,
 * and meanwhile update the hashmap.
 * If the character is already in the hashmap,
 * then move the left pointer to the right of the same character last found.
 *
 * Note that the two pointers can only move forward.
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int start = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (charIndexMap.containsKey(cur)) {
                start = Math.max(start, charIndexMap.get(cur) + 1);
            }
            charIndexMap.put(cur, i);
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }
}
