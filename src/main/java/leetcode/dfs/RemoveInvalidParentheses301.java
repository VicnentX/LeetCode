package leetcode.dfs;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

import java.util.*;

public class RemoveInvalidParentheses301 {

    public List<String> removeInvalidParenthesesDFS(String s) {
        Set<String> ret = new HashSet<>();
        if (s == null || s.length() == 0) {
            ret.add("");
            return new ArrayList<>(ret);
        }
        int cnt = cntToAdd(s);
        dfs (cnt , cnt , "" , s , 0 , ret );
        return new ArrayList<>(ret);
    }

    private int cntToAdd(String s) {
        int totalLeft = 0;
        int totalRight = 0;
        int r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') ++totalLeft;
            if (c == ')') {
                if (totalRight >= totalLeft) {
                    ++r;
                } else {
                    ++totalRight;
                }
            }
        }
        return totalRight;
    }

    private void dfs (int left , int right , String path , String s , int i , Set<String> ret) {
        if (i == s.length()) {
            if (left == 0 && right == 0) {
                System.out.println("we are here");
                ret.add(path);
            }
            return;
        }

        if (s.charAt(i) == '('){
            if (left > 0){
                dfs (left - 1, right , path + '(', s , i + 1 , ret);
            }
            dfs (left , right , path , s , i + 1 , ret);
        } else if (s.charAt(i) == ')') {
            if (right > left) {
                dfs (left , right - 1, path + ')' , s , i + 1 , ret);
            }
            dfs (left , right , path , s , i + 1 , ret);
        } else {
            dfs (left , right , path + s.charAt(i) , s , i + 1 , ret);
        }

    }

    public static void main (String[] args) {
        RemoveInvalidParentheses301 ri = new RemoveInvalidParentheses301();
        for (String s : ri.removeInvalidParenthesesDFS("())")) {
            System.out.println(s);
        }
    }
}

