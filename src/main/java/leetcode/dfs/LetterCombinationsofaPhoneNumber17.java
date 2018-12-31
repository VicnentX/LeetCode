package leetcode.dfs;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

import java.util.*;

public class LetterCombinationsofaPhoneNumber17 {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> ret = new ArrayList<>();
        if (digits == null | digits.length() == 0) return ret;
        dfs ("" , 0 , ret , digits , map);
        return ret;
    }
    private void dfs (String s , int index , List<String> ret , String digits , Map<Character, String> map) {
        if (index == digits.length()) {
            ret.add(s);
            return ;
        }

        for (Character c : map.get(digits.charAt(index)).toCharArray()) {
            dfs (s + c , index + 1 , ret , digits , map);
        }
    }

    public static void main (String[] args) {
        LetterCombinationsofaPhoneNumber17 lc = new LetterCombinationsofaPhoneNumber17();
        for (String s : lc.letterCombinations("23")) {
            System.out.println(s);
        }
    }
}
