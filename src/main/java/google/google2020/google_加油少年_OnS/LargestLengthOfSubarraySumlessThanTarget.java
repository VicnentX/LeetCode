package google.google2020.google_加油少年_OnS;

/*
Given an array of integers,
find the largest length of subarray that has sum less than or
equal to the target number.
example:
[7,2,1,3,4,5,1,2], target = 6
output: 3  ([2,1,3] is the longest subarray that has a sum <= 6)
 */

import java.util.LinkedList;
import java.util.Queue;

public class LargestLengthOfSubarraySumlessThanTarget {
    public int sovle(int[] nums, int target) {
        int ret = 0;
        int sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int num: nums) {
            queue.add(num);
            sum += num;
            while (sum > target) {
                sum -= queue.remove();
            }
            //udpate ret
            ret = Math.max(ret, queue.size());
        }
        return ret;
    }

    public static void main(String[] args) {
        LargestLengthOfSubarraySumlessThanTarget llss = new LargestLengthOfSubarraySumlessThanTarget();
        System.out.println(llss.sovle(new int[ ]{7,2,1,3,4,5,1,2}, 6));
        System.out.println(llss.sovle(new int[] {1, 1, 1, 1, -100, 1, 1, 1}, 3));
    }
}
