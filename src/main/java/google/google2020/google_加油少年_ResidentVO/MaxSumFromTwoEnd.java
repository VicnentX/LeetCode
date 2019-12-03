package google.google2020.google_加油少年_ResidentVO;

/*
给一个数组 [2,5,4,7,1,10,4]， 每次只能从头或从尾取一个数，要求去 k 次，取出数的和最大是多少。
如 k=1: sum=4;
k=2: sum=4+10=14;
k=3: sum=4+10+2=16

我先 clarify the question，比如输入是个普通 array
啊还是个 list，会不会有负数，k 会不会比数组的个数多或 k 会不会是 0 或负数，
如果 sum 太大不能用整形表示怎么办，面试官说 good，然后 discuss 了一下。

当时看出了这个肯定是 dp 问题，但我转牛角尖一直往 2 维 dp 想，
怎么也找不到状态。最后先给出了暴力 dfs，
然后面试官看我想不出更好的解法就先让我把 dfs 实现以下。
写完代码过一遍 test case 没啥问题，再继续想怎么优化。
最后面试官看我想不出来给了个提示，
写了一个三维数组 memo[start][end][k]，
我就明白这是个三维 dp，状态也马上找到了，
马上先写出了状态转移方程，但时间不够了就没让我写代码。
 */

import java.util.Arrays;

/**
 * 第一题只需要用一个k的滑动窗口 ，窗口可以理解为两段，
 * 一段在开始一段在尾部，加起来长度是k，
 * 开始从0滑到k，O（k）时间可以得到答案
 */

public class MaxSumFromTwoEnd {
    public int solve(int[] nums, int k) {
        if (k >= nums.length) return Arrays.stream(nums).sum();
        int n = nums.length;
        int cur = 0;
        int ret = 0;
        for (int i = 0; i < k; ++i) {
            cur += nums[i];
        }
        for (int i = k - 1; i >= 0; --i) {
            cur = cur - nums[i] + nums[i + n - k];
            ret = Math.max(ret, cur);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxSumFromTwoEnd maxSumFromTwoEnd = new MaxSumFromTwoEnd();
        //16
        System.out.println(maxSumFromTwoEnd.solve(new int[] {4,5,6,11,3,2}, 3));
        //16
        System.out.println(maxSumFromTwoEnd.solve(new int[] {2,5,4,7,1,10,4}, 3));
    }
}
