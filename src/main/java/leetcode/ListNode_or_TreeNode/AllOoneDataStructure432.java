package leetcode.ListNode_or_TreeNode;
/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */

import java.util.*;

public class AllOoneDataStructure432 {
    //maintain a doubly linked list of buckets
    private Bucket head;
    private Bucket tail;
    //for accessing a specific Bucket among the Bucket list in O(1)
    private Map<Integer , Bucket> bucketMap;
    //track of count of keys
    private Map<String , Integer> map;

    //each Bucket contains all the keys with the same count
    private class Bucket{
        int count ;
        Set<String> KeySet;
        Bucket next;
        Bucket pre;
        public Bucket(int cnt){
            count = cnt;
            KeySet = new HashSet<>();
        }
    }

    /** Initialize your data structure here. */
    public AllOoneDataStructure432() {
        head = new Bucket(Integer.MIN_VALUE);// this is the bucket whose count = 0 inf;
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        bucketMap = new HashMap<>();
        map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)){
            changeKey(key , 1);
        }else{
            map.put(key , 1);
            if(head.next.count != 1){
                addBucketAfter(new Bucket(1) , head);//add a new bucket after head
            }
            head.next.KeySet.add(key);
            bucketMap.put(1 , head.next);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)){
            int count = map.get(key);
            if(count == 1){
                map.remove(key);
                removeKeyFromBucket(bucketMap.get(count) , key);
            }else{
                changeKey(key , - 1);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return head.next == tail? "":tail.pre.KeySet.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail? "":head.next.KeySet.iterator().next();
    }

    private void changeKey(String key , int offset){
        int count = map.get(key);
        map.put(key , count + offset);
        Bucket curBucket = bucketMap.get(count);
        Bucket newBucket;
        if(bucketMap.containsKey(count + offset)){
            //target Bucket already exists
            newBucket = bucketMap.get(count + offset);
        }else{
            newBucket = new Bucket(count + offset);
            //add new line in the bucketMap
            bucketMap.put(count + offset , newBucket);
            addBucketAfter(newBucket , offset == 1? curBucket : curBucket.pre);
        }
        newBucket.KeySet.add(key);
        removeKeyFromBucket(curBucket , key);
    }
    private void removeKeyFromBucket(Bucket bucket , String key){
        bucket.KeySet.remove(key);
        if(bucket.KeySet.size() == 0){
            removeBucketFromList(bucket);
            bucketMap.remove(bucket.count);
        }
    }
    private void removeBucketFromList(Bucket bucket){
        bucket.pre.next = bucket.next;
        bucket.next.pre = bucket.pre;
        bucket.next = null;
        bucket.pre = null;
    }
    private void addBucketAfter(Bucket newBucket , Bucket preBucket){
        newBucket.pre = preBucket;
        newBucket.next = preBucket.next;
        preBucket.next = newBucket;
        newBucket.next.pre = newBucket;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
