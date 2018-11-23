package leetcode.Number;

import java.util.*;


public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        if(k == 0) return null;
        List<int[]> ret = new ArrayList<>();

        HashMap<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer,Integer>> minHeap =new PriorityQueue<>((a,b) -> (a.getKey() - b.getKey()));

        for(int i = 0; i < nums1.length; ++i){
            for(int j = 0; j < nums2.length; ++j){
                int element = nums1[i] + nums2[j];
                map.put(element, map.getOrDefault(element,0) + 1);
            }
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet() ){
            minHeap.add(entry);
        }

        for(int index= 0; index < k; ++index){
            index += minHeap.peek().getValue();
            for(int i = 0; i < nums1.length; ++i){
                for(int j = 0; j < nums2.length; ++j){
                    if(nums1[i] + nums2[j] == minHeap.peek().getKey()){
                        ret.add(new int[]{nums1[i],nums2[j]});
                    }
                }
            }
            minHeap.poll();
        }

        return ret;

    }

    //[-10,-4,0,0,6]
    //[3,5,6,7,8,100]
    //10

    public static void main(String[] args){
        FindKPairsWithSmallestSums ss=new FindKPairsWithSmallestSums();
        //System.out.println(ss.kSmallestPairs(new int[]{-10,-4,0,0,6}, new int[]{3,5,6,7,8,100}, 10));
        //System.out.println(Arrays.toString(ss.kSmallestPairs(new int[]{-10,-4,0,0,6}, new int[]{3,5,6,7,8,100}, 10).toArray()))
        for(int i = 0; i < ss.kSmallestPairs(new int[]{-10,-4,0,0,6}, new int[]{3,5,6,7,8,100}, 10).size() ; ++i){
            System.out.println(ss.kSmallestPairs(new int[]{-10,-4,0,0,6}, new int[]{3,5,6,7,8,100}, 10).get(i));
        }
    }

}
