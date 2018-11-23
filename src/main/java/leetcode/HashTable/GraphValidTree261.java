package leetcode.HashTable;

import java.util.*;

//union find is faster than bfs
public class GraphValidTree261 {
    public boolean validTree(int n, int[][] edges) {
        //this problem is to check out if there is a circle in this graph;
        //use map to record the degree of each node;
        //delete the node with 0 degree and at last if  # of the node deleted equals n , return true; otherwise return false;
        if(n <= 1) return true;
        if(edges.length < n - 1 || edges == null) return false;

        int ret = 0;
        Map<Integer , Integer> degree = new HashMap<>();
        Map<Integer , Set<Integer>> map = new HashMap<>();
        for(int[] k : edges){
            degree.put(k[0] , degree.getOrDefault(k[0] , 0) + 1);
            degree.put(k[1] , degree.getOrDefault(k[1] , 0) + 1);
            if(!map.containsKey(k[0])){
                map.put(k[0] , new HashSet<>());
            }
            map.get(k[0]).add(k[1]);
            if(!map.containsKey(k[1])){
                map.put(k[1] , new HashSet<>());
            }
            map.get(k[1]).add(k[0]);
        }
        //check if there is isolated node;
        if(map.size() != n){
            return false;
        }
        //把degree = 1的点找出来，然后不断iterate相关的点，degree变为1再放入q
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for(int k : degree.keySet()){
            if(degree.get(k) == 1){
                q.add(k);
                visited.add(k);
            }
        }
        while(!q.isEmpty()){
            int k = q.remove();
            ++ret;
            for(int k1 : map.get(k)){
                if(!visited.contains(k1)){
                    degree.put(k1 , degree.get(k1) - 1);
                    if(degree.get(k1) == 1){
                        q.add(k1);
                    }
                }
            }
        }
        return ret == n ;
    }
}
