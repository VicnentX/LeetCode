package leetcode.dfs_bfs;

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

    ///first version of dfs

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


    ////second version of dfs

    public List<String> removeInvalidParentheses(String s) {
        Set<String> ret = new HashSet<>();
        if (s.length() == 0) {
            ret.add("");
            return new ArrayList<>(ret);
        }
        int left = 0;
        int right = 0;
        int wasteRight = 0;
        int wasteLeft = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++left;
            } else if (c == ')') {
                if (right < left) {
                    ++right;
                } else {
                    ++wasteRight;
                }
            }
        }
        wasteLeft = left - right;
        left = right;
        System.out.println(wasteLeft);
        System.out.println(wasteRight);
        System.out.println(left);
        dfs (0 , left , 0 , 0 , wasteLeft , wasteRight , s , ret , new StringBuilder());
        return new ArrayList<>(ret);
    }

    private void dfs (int index , int n , int curLeft , int curRight , int wasteLeft , int wasteRight , String s , Set<String> ret , StringBuilder path) {
        if (index == s.length()) {
            ret.add(new String(path));
            return ;
        }

        if (s.charAt(index) == '(') {
            if (wasteLeft > 0) dfs(index + 1 , n , curLeft , curRight , wasteLeft - 1 , wasteRight , s , ret , path);
            if (curLeft < n) {
                dfs(index + 1 , n , curLeft + 1 , curRight , wasteLeft , wasteRight , s , ret , path.append('('));
                path.deleteCharAt(path.length() - 1);
            }
        } else if (s.charAt(index) == ')') {
            if (wasteRight > 0) dfs(index + 1 , n , curLeft , curRight , wasteLeft , wasteRight - 1, s , ret , path);
            if (curRight < n && curRight < curLeft) {
                dfs(index + 1 , n , curLeft, curRight + 1, wasteLeft , wasteRight , s , ret , path.append(')'));
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            dfs(index + 1 , n , curLeft, curRight, wasteLeft , wasteRight , s , ret , path.append(s.charAt(index)));
            path.deleteCharAt(path.length() - 1);
        }
    }


    ////BFS

    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() == 0) {
            ret.add("");
            return new ArrayList<>(ret);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean flag = true;
        while(!queue.isEmpty()) {
            Set<String> tem = new HashSet<>();
            while (!queue.isEmpty()) {
                String str = queue.poll();
                if (isValid(str)) {
                    ret.add(str);
                    flag = false;
                } else {
                    if (flag) {
                        for (int i = 0 ; i < str.length() ; ++i) {
                            tem.add(str.substring(0 , i) + str.substring(i + 1));
                        }
                    }
                }
            }
            if (flag) {
                queue.addAll(tem);
            }
        }
        return ret;
    }

    private boolean isValid (String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++cnt;
            } else if (c == ')'){
                if (cnt == 0) {
                    return false;
                }
                --cnt;
            }
        }
        return cnt == 0;
    }

    public static void main (String[] args) {
        RemoveInvalidParentheses301 ri = new RemoveInvalidParentheses301();
        for (String s : ri.removeInvalidParenthesesDFS("())")) {
            System.out.println(s);
        }

        System.out.println("_________________");

        for (String s : ri.removeInvalidParenthesesBFS("())")) {
            System.out.println(s);
        }
    }
}

