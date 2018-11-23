package leetcode.Number;

import java.util.*;


public class LeastBrick {

    public int leastBrick(List<List<Integer>> wall) {

        HashMap<Integer,Integer> map=new HashMap<>();

        for(List<Integer> row:wall){
            int sum=0;
            for(int i=0;i<row.size()-1;i++){

                sum=sum+row.get(i);
                map.put(sum ,  map.getOrDefault(sum,0)+1);
            }
        }

        int ret=wall.size();
        for(int k:map.keySet()){
            ret=Math.min(ret , wall.size()-map.get(k));
        }

        return ret;
    }


    public static void main(String[] args){
        LeastBrick lb=new LeastBrick();

//        >> a= new  List<List<Integer>>(Arrays.asList("1,2,2,1","3,1,2"));
//
//        t1 = Arrays.asList(1,2,2,1);

        List<List<Integer>> a = new ArrayList<>();

        List<Integer> test1 = Arrays.asList(1,2,2,1);
        a.add(test1);
        List<Integer> test2 = Arrays.asList(3,1,2);
        a.add(test2);
        List<Integer> test3 = Arrays.asList(1,3,2);
        a.add(test3);
        List<Integer> test4 = Arrays.asList(2,4);
        a.add(test4);
        List<Integer> test5 = Arrays.asList(3,1,2);
        a.add(test5);
        List<Integer> test6 = Arrays.asList(1,3,1,1);
        a.add(test6);
        System.out.println(lb.leastBrick(a));
    }

}
