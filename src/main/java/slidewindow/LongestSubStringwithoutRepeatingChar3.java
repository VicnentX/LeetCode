package slidewindow;

import java.util.*;
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

public class LongestSubStringwithoutRepeatingChar3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null) return 0;
        Map<Character , Integer> map = new HashMap<>();
        int len = 0 , start = 0;
        for(int i = 0 ; i < s.length() ; ++i){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c , i);
                len = Math.max(len , i - start + 1);
            }else{
                len = Math.max(len , map.get(c) >= start ? i - map.get(c) : i - start  + 1);
                if(map.get(c) >= start) start = map.get(c) + 1;
                map.put(c , i);
            }
        }
        return len;
    }
}
