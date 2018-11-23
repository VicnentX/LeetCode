package leetcode.HashTable;

import java.util.*;

public class WordLadder127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int ret = 1;
        if(wordList.size() == 0 || wordList == null) return 0;
        if(!wordList.contains(endWord)){
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(String k : wordList){
            set.add(k);
        }
        Set<String> key = new HashSet<>();
        key.add(beginWord);
        while(!key.isEmpty()){
            ++ret;
            Set<String> tem = new HashSet<>();
            for(String s : key){
                for(String t : set){
                    if(isAdj(s , t)){
                        if(t.equals(endWord)){
                            return ret;
                        }
                        tem.add(t);
                    }
                }
            }
            set.removeAll(tem);
            key = tem;
        }
        return 0;
    }

    private boolean isAdj(String s , String t){
        int cnt = 0;
        for(int i = 0 ; i < s.length() ; ++i){
            if(s.charAt(i) != t.charAt(i)){
                ++cnt;
                if(cnt > 1){
                    return false;
                }
            }
        }
        return cnt == 1;
    }
}
