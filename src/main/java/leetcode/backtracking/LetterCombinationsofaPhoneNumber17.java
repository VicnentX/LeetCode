package leetcode.backtracking;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber17 {
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits.length() == 0) return  ret;
        Map<Character , String> map = new HashMap<>();
        map.put('2' , "abc");
        map.put('3' , "def");
        map.put('4' , "ghi");
        map.put('5' , "jkl");
        map.put('6' , "mno");
        map.put('7' , "pqrs");
        map.put('8' , "tuv");
        map.put('9' , "wxyz");
        int n = digits.length();
        dfs(new StringBuilder() , 0 , n , map , digits , ret);
        return ret;
    }

    private void dfs (StringBuilder path , int i , int n , Map<Character , String> map , String s , List<String> ret) {
        if (i == n) {
            ret.add(new String(path));
            return ;
        }

        for (char c : map.get(s.charAt(i)).toCharArray()) {
            dfs(path.append(c) , i + 1 , n  , map , s  , ret);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main (String[] args) {
        LetterCombinationsofaPhoneNumber17 lc = new LetterCombinationsofaPhoneNumber17();
        for (String s : lc.letterCombinations("23")) {
            System.out.println(s);
        }
    }
}
