package Wish;
/*
給一組int array [1, 2, 3, 4, 2]
找最長的 "山"長度, 山的定義是指 先數組上升在下降
在這個case就是 1, 2, 3, 4, 2
ex2: [1, 2, 1, 3, 1]的話 答案是 3
 */
public class LongestMountain {
    public int findMax(int[] nums){
        int ret = 0;
        int start = 0;
        while(nums[start] > nums[start + 1]){
            ++start;
        }
        //now start is the first valley
        for(int i = start + 1 ; i < nums.length ; ++i){
            if(nums[i] < nums[i - 1] && (i + 1 >= nums.length || nums[i] < nums[i + 1])){
                ret = Math.max(ret , i - start + 1);
                start = i;
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
