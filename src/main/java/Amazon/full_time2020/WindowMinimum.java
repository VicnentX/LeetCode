package Amazon.full_time2020;

/*
Window Minimum。具体题目如下：

给了一个ArrayList：4, 2, 12, 11, -5，窗口size为2，
返回的ArrayList为：2, 2, 11, -5。这里窗口size是一个参数。
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class WindowMinimum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        final int NUMS_LEGNTH = nums.length;
        int[] ret = new int[NUMS_LEGNTH - k + 1];
        int index = 0;
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < NUMS_LEGNTH; ++i) {
            //remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            //remove larger number in k range cuz they are useless
            while (!q.isEmpty() && nums[q.peekLast()] > nums[i]) {
                q.pollLast();
            }
            //q contains index .. r contains content
            q.offer(i);
            if (i >= k - 1) {
                ret[index] = nums[q.peek()];
                ++index;
            }
        }

        return ret;

    }

    public static void main(String[] args) {
        WindowMinimum windowMinimum = new WindowMinimum();
        for (int i: windowMinimum.maxSlidingWindow(new int[] {4, 2, 12, 11, -5}, 2)) {
            System.out.println(i);
        }

        for (char i = 'a'; i < 'a' + 26; ++i) {
            System.out.print(i + "   ");
        }

        System.out.println();

        for (int i = 1; i <= 26; ++i) {
            System.out.print(i + "   ");
        }
    }
}
