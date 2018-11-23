package leetcode.Number;

import java.util.*;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }

        Deque<Integer> dq = new LinkedList<>();
        int[] ret = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.removeFirst();
            }
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.offerLast(i);

            if (i + 1 >= k) {
                ret[i + 1 - k] = nums[dq.peekFirst()];
            }
        }
        return ret;
    }

    public static void main(String[] args){
        SlidingWindowMaximum sw=new SlidingWindowMaximum();
        int[] out=sw.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
        for(int i:out){
            System.out.println(i);
        }
    }
}
