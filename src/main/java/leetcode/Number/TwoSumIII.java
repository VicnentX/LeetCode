package leetcode.Number;

import java.util.*;

public class TwoSumIII {
    private HashMap<Integer , Integer> map;

    /** Initialize your data structure here. */
    public TwoSumIII() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number , map.containsKey(number)?   map.get(number) + 1 : 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {

        if(value % 2 == 0){
            if(map.containsKey(value / 2)){
                if(map.get(value / 2) > 1) return true;
            }
        }
        for(int k : map.keySet()){
            if(map.containsKey(value - k) && k != value - k){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        TwoSumIII ts = new TwoSumIII();
        ts.add(1);
        ts.add(3);
        ts.add(5);
        System.out.println(ts.find(4));
        System.out.println(ts.find(7));
    }
}
