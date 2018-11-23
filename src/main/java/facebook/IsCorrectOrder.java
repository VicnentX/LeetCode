package facebook;

import java.util.*;
public class IsCorrectOrder {
    public boolean CorrectOrder(String[] s , char[] order){
        int n = s.length;
        if(n < 1){
            return true;
        }
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i = 0 ; i < order.length ; ++i){
            map.put(order[i] , i);
        }

        for(int i = 1 ; i < n ; ++i){
            if(!isMatch(s[i - 1] , s[i] , map)){
                return false;
            }
        }
        return true;
    }
    private boolean isMatch(String s , String t , Map<Character , Integer> map){
        int m = s.length();
        int n = t.length();
        for(int i = 0 ; i < Math.min(m , n) ; ++i){
            char ss = s.charAt(i);
            char tt = t.charAt(i);
            if(ss != tt){
                if(map.get(ss) > map.get(tt)){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return m <= n;
    }

    public static void main(String[] args){
        IsCorrectOrder ic = new IsCorrectOrder();
        System.out.println(ic.CorrectOrder(new String[]{"a" , "aa" , "cb" , "bc"} , new char[]{'a', 'c', 'b'}));
        System.out.println(ic.CorrectOrder(new String[]{"a" , "aa" , "cb" , "bc"} , new char[]{'c', 'a', 'b'}));
        System.out.println(ic.CorrectOrder(new String[]{"cb" , "bc"} , new char[]{'c', 'a', 'b'}));
    }
}
