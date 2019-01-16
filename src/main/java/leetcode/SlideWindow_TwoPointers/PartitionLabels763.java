package leetcode.SlideWindow_TwoPointers;

/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
 */

import java.util.*;
public class PartitionLabels763 {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ret = new ArrayList<>();
        if (s == null || s.length() == 0) return ret;
        int n = s.length();
        int[] map = new int[26];
        //store the last position of each char
        for (int i = 0 ; i < n ; ++i) {
            map[s.charAt(i) - 'a'] = i;
        }
        int start = 0;
        int last = 0;
        for (int i = 0 ; i < n ; ++i) {
            last = Math.max(last , map[s.charAt(i) - 'a']);
            if (last == i) {
                ret.add(last - start + 1);
                start = last + 1;
            }
        }
        return ret;
    }

    public static  void main (String[] args) {
        PartitionLabels763 pl = new PartitionLabels763();
        System.out.println(pl.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
