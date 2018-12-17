package leetcode.UnionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombineSameProduct {
    public int findTotalProduct(Map<String , List<String>> input){
        if(input == null || input.size() == 0){
            return 0;
        }
        int n = input.size();
        int cnt = n;
        //build and fill map that connect the product name and code it.
        Map<String , Integer> map = new HashMap<>();
        int index = 0;
        for(String k : input.keySet()){
            map.put(k , index);
            ++index;
        }
        //size
        int[] size = new int[n];
        Arrays.fill(size , 1);
        //roots
        int[] roots = new int[n];
        for(int i = 0 ; i < n ; ++i){
            roots[i] = i;
        }
        //scan the input and union find
        for(String k : input.keySet()){
            for(String tem : input.get(k)){
                if(map.containsKey(tem)){
                    int root1 = find(roots , map.get(k));
                    int root2 = find(roots , map.get(tem));
                    if(root1 != root2){
                        if(size[root1] < size[root2]){
                            roots[root1] = root2;
                            size[root2] += size[root1];
                        }else{
                            roots[root2] = root1;
                            size[root1] += size[root2];
                        }
                        --cnt;
                    }
                }
            }
        }
        return cnt;
    }
    private int find(int[] roots , int id){
        while(roots[id] != id){
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }

    public static void main(String[] args){
        CombineSameProduct cs = new CombineSameProduct();
        System.out.println(cs.findTotalProduct(new HashMap<String , List<String>>(){{
            put("shirt" , Arrays.asList("shirt1","shirt2"));
            put("shirt1" , Arrays.asList("shirt4","shirt3"));
            put("pants" , Arrays.asList("trousers","sweater"));
            put("trousers" , Arrays.asList("sweater"));
            put("shirt6" , Arrays.asList("shirt7","shirt8"));
        }}));
    }
}
