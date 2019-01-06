package twitter;

import java.util.*;


public class WhoIsTheClosest {
    public List<Integer> findClosestIndex(String s , List<Integer> query){
        List<Integer> ret = new ArrayList<>();
        Map<Character , List<Integer>> map = new HashMap<>();
        Map<Integer , Integer> position = new HashMap<>();//record index and its position in the map's value
        for(int i = 0 ; i < s.length() ; ++i){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c , new ArrayList<>());
            }
            position.put(i , map.get(c).size());
            map.get(c).add(i);
        }
        for(int k : query){
            char target = s.charAt(k);
            int pos = position.get(k);
            if(map.get(target).size() == 1){
                ret.add(-1);
            }else{
                int min = Integer.MAX_VALUE;
                int index = -1;
                if(pos != 0){
                    int dis = map.get(target).get(pos) - map.get(target).get(pos - 1);
                    if(dis < min){
                        min = dis;
                        index = map.get(target).get(pos - 1);
                    }
                }
                if(pos != map.get(target).size() - 1){
                    int dis = map.get(target).get(pos + 1) - map.get(target).get(pos);
                    if(dis < min){
                        min = dis;
                        index = map.get(target).get(pos + 1);
                    }
                }
                ret.add(index);
            }
        }
        return ret;
    }

    public static void main(String[] args){
        WhoIsTheClosest wi = new WhoIsTheClosest();
        System.out.println(wi.findClosestIndex("hackerrank" , new ArrayList<>(Arrays.asList(4,1,6,8))));
    }
}
