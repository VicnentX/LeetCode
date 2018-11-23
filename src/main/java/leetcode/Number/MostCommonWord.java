package leetcode.Number;

import java.util.*;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {

        HashSet<String> set=new HashSet<>(Arrays.asList(banned));
        HashMap<String,Integer> map=new HashMap<>();



        //去标点，变成空格，变成小写，再切分，放进新的String【】
        String[] words=paragraph.replaceAll("[!?',;.]+"," ").toLowerCase().split("\\s+");
        String ret="";
        int max=0;


        for(String i:words){
            if(!set.contains(i)){
                map.put(i,map.getOrDefault(i,0)+1);
                if(map.get(i)>max){
                    max=map.get(i);
                    ret=i;
                }
            }
        }

        return ret;

    }

    public static void main(String[] args){
        MostCommonWord mc=new MostCommonWord();
        System.out.println(mc.mostCommonWord("ball, BALL.hit Hit HIT hit , s",new String[]{"hit","ds"}));


    }
}
