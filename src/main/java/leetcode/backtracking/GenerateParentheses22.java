package leetcode.backtracking;

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

/**
 * 这边区别于dfs用backtracking体现在用了stringbuilder 所以后期需要删除掉
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses22 {
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        if (n <= 0) return ret;
        dfs (new StringBuilder() , 0 , 0 , n , ret);
        return ret;
    }

    private void dfs (StringBuilder path , int left , int right , int n , List<String> ret) {
        if (left + right == 2 * n) {
            ret.add(new String(path));
            return ;
        }

        if (left < n) {
            dfs (path.append('(') , left + 1 , right , n , ret);
            path.deleteCharAt(path.length() - 1);
        }

        if (left > right) {
            dfs (path.append(')') , left , right + 1, n , ret);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main (String[] args) {
        GenerateParentheses22 gp = new GenerateParentheses22();
        for (String s : gp.generateParenthesis(3)) {
            System.out.println(s);
        }
    }
}
