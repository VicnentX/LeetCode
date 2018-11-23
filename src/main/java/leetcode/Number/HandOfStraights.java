package leetcode.Number;

import java.util.*;

public class HandOfStraights {


    public boolean isNStraightHand(int[] hand, int w) {
        if(hand.length % w != 0){
            return false;
        }

        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>((a,b) -> (a.getKey() - b.getKey()));
        Map<Integer,Integer> map = new HashMap<>();
        //Map<Integer,Integer> tem = new HashMap<>();


        for(int k : hand){
            map.put(k, map.getOrDefault(k , 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            minHeap.add(entry);
        }

        //特别备注 如果上面需要用不联动的方法 那么需要这样写
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            minHeap.add(new MyEntry<>(entry.getKey() , entry.getValue()));
//        }

        while(!map.isEmpty()){
            for(int i = 0; i < w; ++i){
                if(map.containsKey(minHeap.peek().getKey() + i)){
                        map.put(minHeap.peek().getKey() + i , map.get(minHeap.peek().getKey() + i) - 1);
                        if(map.get(minHeap.peek().getKey() + i) == 0)   map.remove(minHeap.peek().getKey() + i);
                }else{
                    return false;
                }
            }
            while(!minHeap.isEmpty() && minHeap.peek().getValue() == 0){//!minHeap.isEmpty() must be prior to getValue
                minHeap.poll();
                }
        }
        return true;
    }


    final class MyEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

    public static void main(String[] args){
        HandOfStraights hs = new HandOfStraights();
        System.out.println(hs.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3));
        System.out.println(hs.isNStraightHand(new int[]{1,2,3,4,5,6},2));
    }

}
