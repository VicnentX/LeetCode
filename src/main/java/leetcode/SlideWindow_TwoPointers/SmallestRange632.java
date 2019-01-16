package leetcode.SlideWindow_TwoPointers;

/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
 */

/**
 * while
 *
 * 每一行取一个数字
 * 算range
 * 然后最小的那个数字的指针往右移一位
 * 更新max
 */

import java.util.*;
public class SmallestRange632 {
    public class element {
        int row;
        int col;
        int val;
        element (int r , int c , int v) {
            row = r;
            col = c;
            val = v;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int m = nums.size();
        int max = Integer.MIN_VALUE , range = Integer.MAX_VALUE;
        PriorityQueue<element> pq = new PriorityQueue<>((a , b) -> a.val - b.val);
        for (int i = 0 ; i < m ; ++i) {
            element tem = new element (i , 0 , nums.get(i).get(0));
            pq.add(tem);
            if (tem.val > max) {
                max = tem.val;
            }
        }
        int start = -1 , end = -1;

        while (pq.size() == m) {
            element min = pq.poll();
            if (max - min.val < range) {
                start = min.val;
                end = max;
                range = end - start;
            }

            if (min.col + 1 < nums.get(min.row).size()) {
                element added = new element(min.row , min.col + 1 , nums.get(min.row).get(min.col + 1));
                pq.add(added);
                if (added.val > max) {
                    max = added.val;
                }
            }
        }
        return new int[] {start , end};
    }
}
