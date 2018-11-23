package leetcode.HashTable;

import java.util.*;

public class WordLadder {
    int ret = 1;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        //HashSet<String> visited = new HashSet<>();
        HashSet<String> head = new HashSet<>();
        for(String k : wordList){
            set.add(k);
        }
        if(!set.contains(endWord)){
            return 0;
        }
        head.add(beginWord);
        while(!head.isEmpty()){
            for(String k : head){
                if(k.equals(endWord)){
                    return ret;
                }
                //set.remove(k);
            }

            //visited.addAll(head);
            HashSet<String> tem = new HashSet<>();
            HashSet<String> readyToDelete = new HashSet<>();
            for(String k : head){
                for(String s : set){
                    if(diff(k , s)){
                        tem.add(s);
                        readyToDelete.add(s);
                    }
                }
            }
            set.removeAll(readyToDelete);
            head = tem;
            ++ret;
        }
        return 0;
    }

    private boolean diff(String a , String b){
        int len = 0;
        for(int i = 0 ; i < a.length() ; ++i){
            if(a.charAt(i) != b.charAt(i)){
                ++len;
                if(len > 1){
                    return false;
                }
            }
        }
        return len == 1? true : false;
    }

    public static void main(String[] args){
        WordLadder wl = new WordLadder();
        List<String> input = new ArrayList<>();
        input.add("hot");
        input.add("dot");
        input.add("dog");
        input.add("lot");
        input.add("log");
        input.add("cog");
        System.out.println(wl.ladderLength("hit" , "cog" , input));
    }
}
