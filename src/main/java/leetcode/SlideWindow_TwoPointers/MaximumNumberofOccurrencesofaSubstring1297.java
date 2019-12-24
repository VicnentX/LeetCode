package leetcode.SlideWindow_TwoPointers;

/*
Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.


Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0


Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.
Seen this question in a real interview before?

 */

import java.util.HashMap;
import java.util.Map;

/**
 * 这题的难度在于我可以用string当作key 不过要删除最前面 加上后面 再变成string需要On的时间
 * 我自键了一个hash来做
 */

public class MaximumNumberofOccurrencesofaSubstring1297 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        if(minSize > n) return 0;
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, Integer> cntMap = new HashMap<>();
        int hash = 0;
        for(int i = 0; i < n; ++i) {
            hash = hash * 26 + (s.charAt(i) - 'a');
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (i >= minSize) {
                hash %= Math.pow(26, minSize);
                char key = s.charAt(i - minSize);
                int value = map.get(key);
                map.put(key, value - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
            }
            if (i >= minSize - 1) {
                if (map.size() <= maxLetters) {
                    cntMap.put(hash, cntMap.getOrDefault(hash, 0) + 1);
                    max = Math.max(max, cntMap.get(hash));
                }
            }
        }

        return max;
    }
}
