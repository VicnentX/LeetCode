package twitter;

import java.util.*;

public class SimpleTextQueries {
    public List<List<Integer>> ExecuteQuery(List<List<String>> query , List<List<String>> sentence){
        List<List<Integer>> ret = new ArrayList<>();

        Map<String , List<int[]>> map = new HashMap<>();
        for(int i = 0 ; i < sentence.size() ; ++i){
            Map<String , Integer> tem = new HashMap<>();
            for(String k : sentence.get(i)){
                tem.put(k , tem.getOrDefault(k , 0) + 1);
            }
            for(String k : tem.keySet()){
                if(!map.containsKey(k)){
                    map.put(k , new ArrayList<>());
                }
                map.get(k).add(new int[]{i , tem.get(k)});
            }
        }
        OUT:
        for(int i = 0 ; i < query.size() ; ++i){
            Map<String , Integer> tem = new HashMap<>();//tem is the map of one specific query
            for(String k : query.get(i)){
                tem.put(k , tem.getOrDefault(k , 0 ) + 1);
            }
            Set<Integer> residue = new HashSet<>();
            for(String k : tem.keySet()){
                int time = tem.get(k);
                Set<Integer> temSet = new HashSet<>();
                for(int[] kk : map.get(k)){
                    if(kk[1] >= time){
                        if(residue.isEmpty()){
                            temSet.add(kk[0]);
                        }else if(residue.contains(kk[0])){
                            temSet.add(kk[0]);
                        }
                    }
                }
                if(temSet.isEmpty()){
                    ret.add(Arrays.asList(-1));
                    continue OUT;
                }
                residue = temSet;
            }
            ret.add(new ArrayList<>(new HashSet<>(residue)));
        }
        return ret;
    }

    public static void main(String[] args){
        SimpleTextQueries st = new SimpleTextQueries();

        List<List<String>> sentence  = new ArrayList<>();
        List<String> places = Arrays.asList("it", "go", "will" , "away");
        sentence.add(places);
        places = Arrays.asList("go", "do", "art");
        sentence.add(places);
        places = Arrays.asList("what", "to", "will" , "east");
        sentence.add(places);

        List<List<String>> query  = new ArrayList<>();
        places = Arrays.asList("it", "will" );
        query.add(places);
        places = Arrays.asList("go", "east" , "will" );
        query.add(places);
        places = Arrays.asList( "will" );
        query.add(places);

        System.out.println(st.ExecuteQuery(query , sentence));
    }
}
