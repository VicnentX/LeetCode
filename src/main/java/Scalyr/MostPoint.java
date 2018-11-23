package Scalyr;
//这道题就是在跑道上面来回跑 问哪个点经过最多
//1，3，5 就是指 从1-3 再从3-5 ，所以3经过两次 答案就是3
//很多个点都是经过次数最多的话 取最小的哪个。

import java.util.*;

public class MostPoint {
    public int getPoint(int[] a , int n) {
        int len = a.length - 1;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for(int i = 0 ; i < len ; ++i){
            starts[i] = Math.min(a[i] , a[i + 1]);
            ends[i] = Math.max(a[i] , a[i + 1]);
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int cnt = 0;
        int j = 0;
        int point = -1;
        for(int i = 0 ; i < starts.length ; ++i){
            if(starts[i] <= ends[j]){
                ++cnt;
                point = starts[i];//我要的是这个点
            }else{
                ++j;
            }
        }
        return point;
    }

    public static void main(String[] args){
        MostPoint mp = new MostPoint();
        System.out.println(mp.getPoint(new int[]{1,3,5,7,9,5,9} , 10));
        System.out.println(mp.getPoint(new int[]{1,3,5,7,9,5,9,7} , 10));
        System.out.println(mp.getPoint(new int[]{1,3,10,7,9} , 10));
    }
}
