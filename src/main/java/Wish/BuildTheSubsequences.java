package Wish;

import java.util.*;

public class BuildTheSubsequences {
//    public List<String> makeList(String s){
//        List<String> ret = new ArrayList<>();
//        char[] ch = s.toCharArray();
//        helper(new StringBuilder() , 0 , ch , ret);
//        Collections.sort(ret , (a,b) -> a.compareTo(b));
//        return ret;
//    }
//    private void helper(StringBuilder sb , int start  , char[] ch , List<String> ret){
//        if(start > ch.length){
//            return ;
//        }
//        if(sb.length() != 0){
//            ret.add(sb.toString());
//        }
//        for(int i = start ; i < ch.length ; ++i){
//
//            helper(sb.append(ch[i]) , i + 1 , ch , ret);
//            sb.deleteCharAt(sb.length() - 1);
//        }
//    }


    //————————————————————————————————————————————————————————————————————————————————————————————————
    //这个方法是用位数的做法
    //第一个是for循环循环是把每一种可能 从1开始是因为""不算做一种subsequence
    //具体比如 abc ,i = 3 means 011 ,means bc ,
    //所以后面j的for循环就是通过和011每一位比较 生产bc的stringbuilder
    public List<String> makeList(String s){
        List<String> ret = new ArrayList<>();
        for(int i  = 1 ; i <= (1<<s.length()) - 1 ; ++i){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j <= s.length() - 1 ; ++j){
                if((i & (1<<j)) != 0){//!!!!!!!!注意这边不一定等于1 以为不一定在最右边那位
                    sb.insert(0 , s.charAt(s.length() - 1 - j));
                }
            }
            ret.add(sb.toString());
        }
        Collections.sort(ret);//这种排序就是按照dictionary
        return ret;
    }



    public static void main(String[] args){
        BuildTheSubsequences bts = new BuildTheSubsequences();
        for(String k : bts.makeList("ba")){
            System.out.print(k + " ");
        }
        System.out.println();
        for(String k : bts.makeList("XYZ")){
            System.out.print(k + " ");
        }
    }
}
