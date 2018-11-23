package leetcode.UnionFind;

import java.util.Arrays;

public class GraphValidTree261 {
    public boolean validTree(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0 ; i < n ; ++i){
            roots[i] = i;
        }
        //create is int[] size to compress union find;
        int[] size = new int[n];
        Arrays.fill(size , 1);

        for(int[] k : edges){
            int root1 = find(roots , k[0]);
            int root2 = find(roots , k[1]);
            if(root1 == root2){
                return false;
            }
            if(size[root1] < size[root2]){
                roots[root1] = root2;
                size[root2] += size[root1];
            }else{
                roots[root2] = root1;
                size[root1] += size[root2];
            }
        }
        return true;
    }
    private int  find(int[] roots , int id){
        while(roots[id] != id){
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
}
