package slidewindow;

import java.util.*;
/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class MinimumWindowSubstring76 {
    public String minWindow(String s, String t) {
        Map<Character , Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c , map.getOrDefault(c , 0)  + 1);
        }
        int cnt = map.size();
        int end = 0 , start = 0 , len = Integer.MAX_VALUE , begin = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c , map.get(c) - 1);
                if(map.get(c) == 0) --cnt;
            }
            ++end;

            while(cnt == 0){
                char cc = s.charAt(start);
                if(map.containsKey(cc)){
                    map.put(cc , map.get(cc) + 1);
                    if(map.get(cc) > 0) ++cnt;
                }
                if(end - start < len){
                    len = end - start;
                    begin = start;
                }
                ++start;
            }
        }
        if(len == Integer.MAX_VALUE) return "";
        return s.substring(begin , begin + len);
    }
}
