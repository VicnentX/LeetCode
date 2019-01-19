package Wish;

/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
Seen this question in a real interview before?

 */

import java.util.*;

public class restoreIPAddress93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        dfs(s , ret , "" , 0 , 0);
        return ret;
    }
    private void dfs(String s , List<String> ret , String cur , int index , int cnt){
        if(cnt > 4){
            return ;
        }
        if(cnt == 4 && index == s.length()){
            ret.add(cur);
        }
        for(int i = 1 ; i < 4 ; ++i){
            if(index + i > s.length()) break;
            String tem = s.substring(index , index + i);
            if(tem.charAt(0) == '0' && tem.length() > 1 || Integer.parseInt(tem) >= 256) break;
            dfs(s , ret , cur + tem + (cnt == 3 ? "" : ".") , index + i , cnt + 1);
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
