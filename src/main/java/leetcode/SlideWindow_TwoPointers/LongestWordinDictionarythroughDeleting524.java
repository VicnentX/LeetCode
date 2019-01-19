package leetcode.SlideWindow_TwoPointers;

/*
Given a string and a string dictionary,
find the longest string in the dictionary
that can be formed by deleting some characters of the given string.
If there are more than one possible results, return the longest word
with the smallest lexicographical order. If there is no possible result,
return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */

import java.util.*;

public class LongestWordinDictionarythroughDeleting524 {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) return "";
        Collections.sort(d , (a , b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length()) ;

        System.out.println(d);

        Map<Character , List<Integer>> map = new HashMap<>();
        for (int i = 0 ; i < s.length() ; ++i) {
            map.putIfAbsent(s.charAt(i) , new ArrayList<>());
            map.get(s.charAt(i)).add(i);
        }
        OUT:
        for (String dic : d) {
            int start = -1;
            for (char c : dic.toCharArray()) {
                if (map.containsKey(c)) {
                    int index = binarySearch(start , map.get(c));
                    if (index == -1) {
                        continue OUT;
                    }
                    start = index;
                } else {
                    continue OUT;
                }
            }
            return dic;
        }
        return "";
    }

    private int binarySearch(int target , List<Integer> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (list.get(start) > target) return list.get(start);
        if (list.get(end) > target) return list.get(end);
        return -1;
    }

    public static void main (String[] args) {
        LongestWordinDictionarythroughDeleting524 lw = new LongestWordinDictionarythroughDeleting524();
        System.out.println(lw.findLongestWord("aewfafwafjlwajflwajflwafj" , new ArrayList<>(Arrays.asList(
                "apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"))));
    }
}
