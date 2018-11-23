package leetcode.dp;

public class MaximumProductSubarray152 {
    public int maxProduct(int[] nums) {
        int global = nums[0];
        int max = nums[0];
        int min = nums[0];
        for(int i = 1 ; i < nums.length ; ++i){
            //
            int temMax = Math.max(max * nums[i] , min * nums[i]);
            int temMin = Math.min(max * nums[i] , min * nums[i]);
            //
            max = Math.max(temMax , nums[i]);
            min = Math.min(temMin , nums[i]);
            //
            global = Math.max(global , max);
        }
        return global;
    }
}
