package google.google2020.google_加油少年_VO;

/*
输入： int[] arr, int k

用宽度k的window滑过arr，输出滑动过程中每个window的最小值组成的array

 */

import java.util.ArrayDeque;
import java.util.Deque;

public class KWindowsArray {
    public int[] getMinElementArray(int[] nums, int k) {
        if (k > nums.length) {
            System.out.println("invalid: k is larger than nums length");
            return null;
        }
        final int N = nums.length;
        int[] ret = new int[N - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; ++i) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }
            //input it inside
            deque.add(i);
            if (i >= k - 1) {
                ret[i - k + 1] = nums[deque.peek()];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        KWindowsArray kWindowsArray = new KWindowsArray();
        for (int i: kWindowsArray.getMinElementArray(new int[] {1,2,3,4,5}, 2)) {
            System.out.println(i);
        }
    }
}
