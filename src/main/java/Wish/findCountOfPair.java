package Wish;

import java.util.*;

public class findCountOfPair {
    public int findPair(int[] nums , int target){
        Map<Integer , Integer> map = new HashMap<>();
        int cnt = 0;
        for(int k : nums){
            if(!map.containsKey(target - k)){
                map.put(k , 0);//0 means not to be paired , 1 means to have been paired;
            }else{
                if(map.get(target - k) == 0){
                    ++cnt;
                    map.put(k , 1);
                    map.put(target - k , 1);
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        findCountOfPair fc = new findCountOfPair();
        System.out.println(fc.findPair(new int[]{1,2,3,4,5,6,7,8,9,0} , 9));//5
        System.out.println(fc.findPair(new int[]{1,8,8,1,1,8} , 9));//1
    }
}
