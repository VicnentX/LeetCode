package leetcode.PriorityQueue;

import java.util.*;
public class FindKPairsWithSmallestSums373 {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //Basic idea: Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
        //
        //Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0] since arrays are all sorted; And for a specific number in nums1, its next candidate sould be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)
        //
        //Here is a simple example demonstrate how this algorithm works.
        //TC klogk;
        PriorityQueue<int[]> q = new PriorityQueue<>((a , b) -> a[0] + a[1] - b[0] - b[1]);
        List<int[]> ret = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return ret;
        for(int i = 0 ; i < nums1.length && i < k ; ++i){
            q.add(new int[] {nums1[i] , nums2[0] , 0});
        }
        while(k-- > 0 && !q.isEmpty()){
            int[] cur = q.poll();
            ret.add(new int[]{cur[0] , cur[1]});
            if(cur[2] == nums2.length - 1){
                continue;
            }
            q.add(new int[]{cur[0] , nums2[cur[2] + 1] , cur[2] + 1});
        }
        return ret;
    }

}
