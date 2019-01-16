package leetcode.SlideWindow_TwoPointers;

import java.util.*;

/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */

public class LongestSubstringwithAtMostTwoDistinctChar159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null) return 0;
        Map<Character , Integer> map = new HashMap<>();
        int start = 0 , end = 0 , cnt = 0 , len = 0;//cnt means how many different kind of char , len is the result;
        while(end < s.length()){
            char temp1 = s.charAt(end);
            map.put(temp1 , map.getOrDefault(temp1 , 0) + 1);
            if(map.get(temp1) == 1) ++cnt;
            ++end;
            while(cnt > 2){
                char temp2 = s.charAt(start);
                map.put(temp2 , map.get(temp2) - 1);
                if(map.get(temp2) == 0) --cnt;
                ++start;
            }
            len = Math.max(len , end - start);
        }
        return len;
    }

    public int lengthOfLongestSubstringTwoDistinctMethod2(String s) {
        int ret = 0;
        int start = 0;
        int n = s.length();
        if (s == null || s.length() == 0) return ret;
        Map<Character , Integer> map = new HashMap<>();
        for (int i = 0 ; i < n ; ++i) {
            char c = s.charAt(i);
            map.put(c , i);
            if (map.size() > 2) {
                char dele = s.charAt(start);
                start = map.get(dele);
                for (char check : map.keySet()) {
                    if (map.get(check) < start) {
                        dele = check;
                        start = map.get(check);
                    }
                }
                map.remove(dele);
                ++start;
            } else {
                ret = Math.max(ret , i - start + 1);
            }
        }
        return ret;
    }


}
