package leetcode.backtracking_TrieNode;

/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */

import java.util.*;

public class LetterCasePermutation784 {
    public List<String> letterCasePermutation(String s) {
        List<String> ret = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(path , s.toUpperCase() , 0 , s.length() , ret);
        return ret;
    }

    private void dfs (StringBuilder path , String s , int i , int n , List<String> ret) {
        if (i == n) {
            ret.add(new String(path));
            return;
        }

        if (Character.isDigit(s.charAt(i))) {
            dfs (path.append(s.charAt(i)) , s , i + 1 , n  , ret);
            path.deleteCharAt(path.length() - 1);
        } else {
            dfs (path.append(s.charAt(i)) , s , i + 1 , n , ret);
            path.deleteCharAt(path.length() - 1);
            dfs (path.append((char)(s.charAt(i) + 32)) , s , i + 1 , n , ret);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
