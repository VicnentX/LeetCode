package leetcode.fenwickTree;

/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */

public class RangeSumQuery_Mutable307 {
    int[] sum;
    int[] nums;

    public RangeSumQuery_Mutable307(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            update_helper(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {
        update_helper(i + 1, val - this.nums[i]);
        this.nums[i] = val;
    }

    public void update_helper(int i, int delta) {
        //update ith node with delta
        //第0个元素应该始终没有被使用
        while (i < sum.length) {
            sum[i] += delta;
            //这里i每次都加上他的最低位为1的值 比如5 = 101 就加上1 得到6 = 110； 如果是 6 = 110就加上10得到1000就是8
            i += (i & -i);
        }
    }

    public int sumRange(int i, int j) {
//        System.out.println(query(j + 1));
//        System.out.println(query(i));
        return query(j + 1) - query(i);
    }

    public int query(int i) {
        int temp = 0;
        while(i > 0) {
            temp += sum[i];
            i -= (i & -i);
        }
        return temp;
    }

    public static void main(String[] args) {
        RangeSumQuery_Mutable307 rs = new RangeSumQuery_Mutable307(new int[] {1,3,5});
        System.out.println(rs.sumRange(0,2));
    }
}
