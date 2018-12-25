package leetcode.array;

/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */

public class MajorityElement169 {
    public int majorityElement(int[] nums) {
        //moore voting algorithm
        int ret = 0;
        int cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                ret = num;
            }
            if (ret != num) {
                --cnt;
            } else {
                ++cnt;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        MajorityElement169 me = new MajorityElement169();
        System.out.println(me.majorityElement(new int[] {2,2,1,1,1,2,2}));
    }
}
