package leetcode.Number;

import java.util.*;

public class LongestWordInDictionary {

    public String longestWord(String[] words) {

        Set<String> set = new TreeSet<>();
        for(String s : words){
            set.add(s);
            //System.out.println(s);
        }

        String ret = "";
        for(String s : set){
            //System.out.println(s);
            int end = s.length()-1;
            while(set.contains(s.substring(0,end)) && end > 0){
                --end;
            }
            if(end == 0 && s.length() > ret.length()){
                ret = s;
            }
        }

        return ret;
    }

    public static void main(String[] args){
        LongestWordInDictionary lw = new LongestWordInDictionary();
        System.out.println(lw.longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"}));
    }

}
