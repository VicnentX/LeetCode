package leetcode.Number;

import java.util.*;

public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] a, String[] b, String[][] pairs) {
        if(a.length != b.length) return false;

        Map<String , Set<String>> map = new HashMap<>();
        for(String[] p : pairs){
            if(!map.containsKey(p[0])) map.put(p[0] , new HashSet<>());
            map.get(p[0]).add(p[1]);
            if(!map.containsKey(p[1])) map.put(p[1] , new HashSet<>());
            map.get(p[1]).add(p[0]);;
        }


        for(int i = 0 ; i < a.length ; ++i){
            if(!a[i].equals(b[i]) && !link(map , a[i] , b[i])) return false;
        }

        return true;
    }

    private boolean link(Map<String , Set<String>> map , String a , String b){
        Deque<String> dp = new LinkedList<String>();
        HashSet<String> visited = new HashSet<>();

        //if(!map.containsKey(a)) return false;
        /*
        for(String k : map.getOrDefault(a , new HashSet<>())){
            dp.add(k);
            visited.add(k);
        }*/
        dp.add(a);
        visited.add(a);

        while(!dp.isEmpty()){
            String s = dp.poll();
            //visited.add(s);
            if (!map.containsKey(s)) {
                return false;
            }
            for(String k : map.get(s)){
                if(k.equals(b)) return true;
                if(!visited.contains(k)) {
                    dp.add(k);
                    visited.add(k);
                }
            }
        }
        return false;
    }
}
