package leetcode.binary_search;

/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */

public class SplitArrayLargestSum410 {
    public int splitArray(int[] nums, int m) {
        int start = 0;
        int end = 0;
        for(int k : nums){
            start = Math.max(start , k);
            end += k;
        }
        //corner case
        if(start == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            int required = 1;
            int cur = 0;
            for(int k : nums){
                if(cur + k <= mid){
                    cur += k;
                }else{
                    ++required;
                    cur = k;
                }
            }
            if(required <= m){
                end = mid;
            }else{
                start = mid;
            }
        }
        //!!!这边之前的错误是先检查end 这是不对的 应该先检查小的start；
        int cur = 0;
        int required = 1;
        for(int k : nums){
            if(cur + k <= start){
                cur += k;
            }else{
                ++required;
                cur = k;
            }
        }
        if(required <= m){
            return start;
        }else{
            return end;
        }
    }

    public static void main(String[] args){
        SplitArrayLargestSum410 sa = new SplitArrayLargestSum410();
        System.out.println(sa.splitArray(new int[]{1,4,4} , 3));
    }
}
