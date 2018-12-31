package leetcode.dfs;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

import java.util.*;
public class GenerateParentheses22 {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        dfs("" , 0 , 0 , ret , n);
        return ret;
    }
    private void dfs (String s , int left , int right , List<String> ret , int n) {
        if (left + right == 2 * n) {
            ret.add(s);
            return ;
        }

        if (left < n) {
            dfs (s + "(" , left + 1 , right , ret , n);
        }

        if (right < left) {
            dfs (s + ")", left , right + 1 , ret , n);
        }
    }

    public static void main (String[] args) {
        GenerateParentheses22 gp = new GenerateParentheses22();
        for (String s : gp.generateParenthesis(3)) {
            System.out.println(s);
        }
    }
}
