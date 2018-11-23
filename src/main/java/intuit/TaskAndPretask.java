package intuit;

import java.util.*;

public class TaskAndPretask {
    public List<List<String>> taskByLevel(String[][] input){
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ret = new ArrayList<>();

        for(String[] k : input){
            if(!map.containsKey(k[0])) map.put(k[0] , new ArrayList<>());
            map.get(k[0]).add(k[1]);

            if(!indegree.containsKey(k[0])){
                indegree.put(k[0] , 0);
            }
            indegree.put(k[1] , indegree.getOrDefault(k[1] , 0) + 1);
        }

        while(!indegree.isEmpty()){
            List<String> tem = new ArrayList<>();
            List<String> delete = new ArrayList<>();
            for(String k : indegree.keySet()){
                if(indegree.get(k) == 0){
                    tem.add(k);
                    delete.add(k);
                }
            }
            ret.add(tem);
            for(String k : delete){
                if(map.containsKey(k)){
                    for(String next : map.get(k)){
                        indegree.put(next , indegree.get(next) - 1);
                    }
                }
                indegree.remove(k);
            }
        }
        return ret;
    }

    public static void main(String[] args){
        TaskAndPretask tp = new TaskAndPretask();
        List<List<String>> result = tp.taskByLevel(new String[][]{{"cook","eat"} , {"study" , "eat"} , {"study" , "TV"} , {"sleep" , "study"}});

        System.out.println(result);
    }
}