package leetcode.PriorityQueue;

/*
Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
You may repeat this procedure as many times as needed.
Return True if it is possible to construct the target array from A otherwise return False.



Example 1:

Input: target = [9,3,5]
Output: true
Explanation: Start with [1, 1, 1]
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
Example 2:

Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].
Example 3:

Input: target = [8,5]
Output: true


Constraints:

N == target.length
1 <= target.length <= 5 * 10^4
1 <= target[i] <= 10^9
 */

import java.util.PriorityQueue;

public class ConstructTargetArrayWithMultipleSums1354 {
    public boolean isPossible(int[] target) {
        long sum = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> {return (int)(b - a);});
        for(int i = 0; i < target.length; i++){
            sum += target[i];
            pq.offer((long)target[i]);
        }
        while(!pq.isEmpty()){
            long now = pq.poll();
            // if there is no number bigger than 1 early exit true
            if (now == 1){
                return true;
            }
            // check if current max can be subtract multiple times by other elements' sum.
            long diff = sum - now;
            long times = (now - 1) / diff;

            //一倍是至少的，没有的话说明就不行了
            if (times < 1){
                return false;
            }

            // update value.
            sum = sum - times * diff;
            now = now - times * diff;

            pq.offer(now);
        }
        //其实这里的true只是针对空数组的情况
        return true;
    }
}
