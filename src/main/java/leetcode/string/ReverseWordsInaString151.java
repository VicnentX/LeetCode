package leetcode.string;

/*
Given an input string, reverse the string word by word.

Example:

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.
 */

import java.util.*;
public class ReverseWordsInaString151 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        String cur = "";
        List<String> list = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            if (c == ' ') {
                if (!cur.isEmpty()) {
                    list.add(cur);
                    cur = "";
                }
            } else {
                cur += c;
            }
        }
        if (!cur.isEmpty()) list.add(cur);
        StringBuilder ret = new StringBuilder();
        for (int i = list.size() - 1 ; i >= 0 ; --i) {
            ret.append(list.get(i)).append(" ");
        }
        return ret.toString().trim();
    }

    public String reverseWordsJAVA(String s) {
        String[] words = s.split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" " , words);
    }

    public static void main (String[] args) {
        ReverseWordsInaString151 rw = new ReverseWordsInaString151();
        System.out.println(rw.reverseWords(" the sky is blue  "));
        System.out.println(rw.reverseWordsJAVA(" the sky is blue  "));
    }
}
