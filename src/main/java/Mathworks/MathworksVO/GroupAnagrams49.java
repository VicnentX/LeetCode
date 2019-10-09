package Mathworks.MathworksVO;

/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
Seen this question in a real interview before?

 */


/**
 * clarify :
 *  does order matter?
 *  is there duplicate in the input?
 *
 *
 *  must add * to avoid 11a + 1b against 1a + 11b
 *
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams49 {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> anagramsMap = new HashMap<>();

        for (String str: strs) {
            String key = buildKey(str);
            if (!anagramsMap.containsKey(key)) {
                anagramsMap.put(key, new ArrayList<>());
            }
            anagramsMap.get(key).add(str);
        }

        return new ArrayList<>(anagramsMap.values());
    }

    private String buildKey(String s) {
        int[] alphabet = new int[26];
        for (char c: s.toCharArray()) {
            alphabet[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int num: alphabet) {
            sb.append(num).append("*");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GroupAnagrams49 groupAnagrams49 = new GroupAnagrams49();
        for (List<String> list:
                groupAnagrams49.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"})) {
            for (String s: list) {
                System.out.print(s + " - ");
            }
            System.out.println();
        }
    }
}
