package leetcode.Number;

import java.util.HashMap;


public class IsormophicStrings {
        public boolean isIsomorphic(String s, String t) {

            //这道题不能用hashset因为这边是一种映射关系
            //这里要用hashmap，用两个一个是s-》t， 一个是t-》s
            if(s.length()!=t.length()){
                return false;
            }

            HashMap<Character,Character> HMS=new HashMap<Character, Character>();
            HashMap<Character,Character> HMT=new HashMap<Character, Character>();

            for(int i=0;i<s.length();i++){
                if(HMS.containsKey(s.charAt(i))){
                    if(HMS.get(s.charAt(i))!= t.charAt(i)){
                        return false;
                    }
                }else{
                    if(HMT.containsKey(t.charAt(i))){
                        return false;
                    }
                    HMS.put(s.charAt(i),t.charAt(i));
                    HMT.put(t.charAt(i),s.charAt(i));
                }
            }

            return true;


        }

        public static void main(String[] args){
            IsormophicStrings is=new IsormophicStrings();
            System.out.println(is.isIsomorphic("ababa","babab"));

        }
}
