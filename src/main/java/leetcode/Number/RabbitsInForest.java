package leetcode.Number;

import java.util.*;

public class RabbitsInForest {

    public int numRabbits(int[] a) {

//        if(a==null){
//            return 0;
//        }
//        HashMap<Integer,Integer> map=new HashMap<>();
//
//        for(int i=0;i<a.length;i++){
//            map.put(a[i],map.getOrDefault(a[i],0)+1);
//        }
//
//        int ret=0;
//        for(int i : map.keySet()){
//
//            if((i+1)>=map.get(i)){
//                ret=ret+(i+1);
//            }else{
//                ret=ret+(i+1)*(map.get(i)/(i+1));
//                if(map.get(i)%(i+1) != 0){
//                    ret=ret+(i+1);
//                }
//            }
//        }
//
//        return ret;


        //方法二
        int ret =0;
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i:a){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        for(int i:map.keySet()){
            int multi=map.get(i)/(i+1);
            ret+= map.get(i)%(i+1)==0 ?   multi*(i+1):(multi+1)*(i+1);  //特别注意！ 这里只能写ret+=   不可以写ret=ret+
        }

        return ret;

    }

    public static void main(String[] args){
        RabbitsInForest rf=new RabbitsInForest();
        System.out.println(rf.numRabbits(new int[]{1,1,1,1,1,1,2,2,2,2,2}));
    }
}
