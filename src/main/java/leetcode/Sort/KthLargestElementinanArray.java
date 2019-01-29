package leetcode.Sort;


/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

/*
public class Solution2 {

    private static Random random = new Random(System.currentTimeMillis());

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k > len) {
            throw new IllegalArgumentException("参数错误");
        }
        // 转换一下，这样比较好操作
        // 第 k 大元素的索引是 len - k
        int target = len - k;
        int l = 0;
        int r = len - 1;
        while (true) {
            int i = partition(nums, l, r);
            if (i < target) {
                l = i + 1;
            } else if (i > target) {
                r = i - 1;
            } else {
                return nums[i];
            }
        }
    }

    // 在区间 [left, right] 这个区间执行 partition 操作
    private int partition(int[] nums, int left, int right) {
        // 在区间随机选择一个元素作为标定点（以下这两行代码非必需）
        // 这一步优化非必需
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }

        int pivot = nums[left];
        int l = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                l++;
                swap(nums, l, i);
            }
        }
        swap(nums, left, l);
        return l;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
---------------------
作者：李威威
来源：CSDN
原文：https://blog.csdn.net/lw_power/article/details/80889022
版权声明：本文为博主原创文章，转载请附上博文链接！
 */

import java.util.*;
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1 && k == 1) return nums[0];
        //
        if (nums.length == 0) return 0;
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int i = partition(left , right , nums);
            if (i < target) {
                left = i + 1 ;
            } else if (i > target) {
                right = i - 1;
            } else {
                return nums[i];
            }
        }
    }

    private int partition (int left , int right , int[] nums) {
        //shuffle
        if (right > left) {
            Random rd = new Random();
            int index = left + 1 + rd.nextInt(right - left);
            swap(left , index , nums);
        }

        //partition
        int j = left;
        for (int i = left + 1 ; i <= right ; ++i) {
            if (nums[i] < nums[left]) {
                ++j;
                swap(j , i , nums);
            }
        }
        swap(left , j , nums);
        return j;
    }

    private void swap (int a , int b , int[] nums) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }

    public static void main (String[] args) {
        KthLargestElementinanArray kl = new KthLargestElementinanArray();
        System.out.println(kl.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6} , 4));
    }
}
