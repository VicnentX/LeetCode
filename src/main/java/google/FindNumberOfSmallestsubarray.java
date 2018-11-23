package google;

import java.util.*;

//given array , partition into multiple strictly decreasing subarrays preserving original order. Find the smallest number
//of subarrays.
//eg. 5 2 3 1 6 -> [5 2][3 1][6] 输出3
//相同的不可以放在一个subarray里面
//只需要输出最小的number

//我这个算法是nlogn

public class FindNumberOfSmallestsubarray {
    public int countOfSubarray(int[] a){
        List<List<Integer>> ret = new ArrayList<>();
        for(int k : a){
            if(ret.size() == 0){
                ret.add(new ArrayList<>());
                ret.get(0).add(k);
            }else{
                int min = Integer.MAX_VALUE;
                int index = -1;
                for(int i = 0 ; i < ret.size() ; ++i){
                    List<Integer> straight = ret.get(i);
                    int lastElement = straight.get(straight.size() - 1);
                    if(lastElement > k){
                        if(lastElement < min){
                            min = lastElement;
                            index = i;
                        }
                    }
                }
                if(index == -1){
                    ret.add(new ArrayList<>());
                    ret.get(ret.size() - 1).add(k);
                }else{
                    ret.get(index).add(k);
                }
            }
        }
        for(List<Integer> list : ret){
            for(Integer k : list){
                System.out.print(k + " ");
            }
            System.out.println();
        }
        return ret.size();
    }


    public static void main(String[] args){
        FindNumberOfSmallestsubarray ss = new FindNumberOfSmallestsubarray();
        int amount = ss.countOfSubarray(new int[]{10,5,9,1,7});
        System.out.println(amount);


    }
}
