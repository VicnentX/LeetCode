package leetcode.Number;

import java.util.*;

public class ShortestWordDistanceII {

    private Map<String , LinkedList<Integer>> map;

    //construction function
    public ShortestWordDistanceII(String[] words) {
        map = new HashMap<>();
        for(int i = 0 ; i < words.length ; ++i){
            if(!map.containsKey(words[i])) map.put(words[i] , new LinkedList<>());
            map.get(words[i]).add(i);
        }
    }

    public int shortest(String w1, String w2) {
        List<Integer> list1 = map.get(w1);
        List<Integer> list2 = map.get(w2);
        int i = 0;
        int j = 0;
        int ret = Integer.MAX_VALUE;
        while(i < list1.size() && j < list2.size()){
            int x = list1.get(i);
            int y = list2.get(j);
            if(x < y){
                ++i;
                ret = Math.min(ret , y - x);
            }else{
                ++j;
                ret = Math.min(ret , x - y);
            }
        }
        return ret;
    }
}
