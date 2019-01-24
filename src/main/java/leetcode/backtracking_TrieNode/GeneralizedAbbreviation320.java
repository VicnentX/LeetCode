package leetcode.backtracking_TrieNode;

/*
Write a function to generate the generalized abbreviations of a word.

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

import java.util.*;
public class GeneralizedAbbreviation320 {
    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        if (word.length() == 0) {
            ret.add("");
            return ret;
        }
        dfs (new StringBuilder() , 0 , 0 , word.length() , word , ret);
        return ret;
    }

    private void dfs (StringBuilder path , int res , int i , int n , String s , List<String> ret) {
        if (i == n) {
            if (res != 0) {
                path.append(res);
                ret.add(new String(path));
                path.setLength(path.length() - (int)Math.log10(res) - 1);
            } else {
                ret.add(new String(path));
            }
            return ;
        }


        dfs(path.append(res == 0 ? "" : res).append(s.charAt(i)) , 0 , i + 1 , n  , s , ret);
        if (res == 0) {
            path.deleteCharAt(path.length() - 1);
        } else {
            path.setLength(path.length() - 1 - (int)Math.log10(res) - 1);
        }

        dfs(path , res + 1 , i + 1 , n , s , ret);
    }

    public static void main (String[] args) {
        GeneralizedAbbreviation320 ga = new GeneralizedAbbreviation320();
        System.out.println(ga.generateAbbreviations("Haiyan_Z"));
    }
}
