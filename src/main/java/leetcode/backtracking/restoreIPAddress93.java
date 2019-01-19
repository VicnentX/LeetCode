package leetcode.backtracking;

/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
Seen this question in a real interview before?

 */

import java.util.ArrayList;
import java.util.List;

public class restoreIPAddress93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() == 0) return ret;
        dfs (0 , 0 , s.length() , new StringBuilder() , s , ret);
        return ret;
    }

    private void dfs (int start , int level , int n , StringBuilder path , String s , List<String> ret) {
        if (level == 4) {
            if (start == n) {
                //ret.add(path.toString());
                ret.add(path.toString());
            }
            return ;
        }

        if (start >= n) return;

        if (s.charAt(start) == '0') {
            dfs (start + 1 , level + 1 , n , path.append(0).append((level == 3 ? "" : '.')) , s , ret);
            path.setLength(path.length() - (level == 3 ? 1 : 2));
        } else {
            for (int len = 1 ; len <= Math.min(3 , n - start) ; ++len) {
                int tem = Integer.valueOf(s.substring(start , start + len));
                if (tem <= 255) {
                    dfs (start + len , level + 1 , n , path.append(tem).append((level == 3 ? "" : '.')) , s , ret);
                    path.setLength(path.length() - len - (level == 3 ? 0 : 1));
                }
            }
        }
    }

    public static void main(String[] args){
        restoreIPAddress93 ip = new restoreIPAddress93();
        List<String> result = ip.restoreIpAddresses("25525511135");
        for(String k : result){
            System.out.println(k);
        }
    }
}
