package Wish;

import java.util.*;
/*
最近在面Wish，找了地里的面经，发现有不少人都遇到这样的题目:从一个排好序的数组里找出出现次 数大于length / 4的数。被这题挂的人也挺多，但是地里也没找到解题报告，这里贴一份我的报告，如果有 什么不正确的地方，欢迎指正。
Solution:
1. 将题目抽象之后就是找出给定nums, k 找出出现频率不小于 length/ k的数。
2. 对于满足条件的数，一定只会出现在0, 1 * length / k, 2 * length / k, 3 * length / k, ... , min( k * length / k, length - 1), 注意最后的边界要取一个较小值。
3. 对于条件2中的数字，利用二分法找到第一个出现的位置和最后一个出现的位置，只要次数 >= length / k，就加入到结果中。
 */
public class LongerThanKportitionOfLength {
    public List<Integer> findLongerThanPKL(int[] nums , int k){
        List<Integer> ret = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return ret;
        }
        int n = nums.length ;
        //
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i <= k - 1 ; ++i){
            set.add(nums[i * n / k]);
        }
        set.add(nums[Math.min(k * n / k , n - 1)]);
        //
        for(int s : set){
            int start = findStart(nums , s);
            int end = findEnd(nums , s);
            if(end - start + 1 > n / k){
                ret.add(s);
            }
        }
        return ret;
    }
    private int findStart(int[] nums , int target){
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(nums[mid] >= target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(nums[start] == target){
            return start;
        }else{
            return end;
        }
    }
    private int findEnd(int[] nums , int target){
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(nums[mid] <= target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(nums[end] == target){
            return end;
        }else{
            return start;
        }
    }

    public static void main(String[] args){
        LongerThanKportitionOfLength lt = new LongerThanKportitionOfLength();
        List<Integer> ret = lt.findLongerThanPKL(new int[]{1,2,2,4,6,7,7} , 4);//answer should be 2,7
        for(int k : ret){
            System.out.print(k + " ");
        }
    }
}
