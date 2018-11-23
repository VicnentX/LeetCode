package leetcode.UnionFind;

import java.util.*;

public class FriendCircles547 {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int cnt = n;
        int[] roots = new int[n];
        for(int i = 0 ; i < n ; ++i){
            roots[i] = i;
        }
        //introduce size[] to compression the union find
        int[] size = new int[n];
        Arrays.fill(size , 1);

        for(int i = 0 ; i < n ; ++i){
            for(int j = i + 1 ; j < n ; ++j){
                if(M[i][j] == 1){
                    int root1 = find(roots , i);
                    int root2 = find(roots , j);
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
        if (roots[id] == id) return id;
        return find(roots, roots[id]);
    }
}
