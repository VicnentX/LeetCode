package Wish;
import java.util.*;
/*
給一組int array [1, 2, 3, 4, 2]
找最長的 "山"長度, 山的定義是指 先數組上升在下降
在這個case就是 1, 2, 3, 4, 2
ex2: [1, 2, 1, 3, 1]的話 答案是 3
 */
public class LongestMountain {
    public int findMax(int[] nums) {
        int ret = 0;
        if(nums.length < 3){
            return ret;
        }
        int n = nums.length;
        int[] up = new int[n];
        Arrays.fill(up , 1);
        int[] down = new int[n];
        Arrays.fill(down , 1);
        for(int i = 1 ; i < nums.length ; ++i){
            if(nums[i] > nums[i - 1]){
                up[i] = up[i - 1] + 1;
            }
        }
        for(int i = nums.length - 2 ; i >= 0 ; --i){
            if(nums[i] > nums[i + 1]){
                down[i] = down[i + 1] + 1;
            }
        }
        for(int i = 1 ; i < nums.length - 1 ; ++i){
            if(nums[i] > nums[i - 1] && nums[i] > nums[i + 1]){
                ret = Math.max(ret , up[i - 1] + 1 + down[i + 1]);
            }
        }
        return ret;
    }
    public static void main(String[] args){
        LongestMountain lm = new LongestMountain();
        System.out.println(lm.findMax(new int[]{1,2,3,4,2}));//5
        System.out.println(lm.findMax(new int[]{1,2,1,3,1}));//3
        System.out.println(lm.findMax(new int[]{4,3,2,1,2,3,4,5,6,7,8,9,8,7,6,5,6,7,8,65,3,2}));//13
    }
}
