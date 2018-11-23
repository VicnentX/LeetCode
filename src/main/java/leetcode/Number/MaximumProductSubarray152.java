package leetcode.Number;

public class MaximumProductSubarray152 {
    public int maxProduct(int[] n) {
        int global = n[0]; //全剧最大
        int max = n[0];     //局部最大
        int min = n[0];     //局部最小
        //
        for(int i = 1 ; i < n.length ; ++i){
            int maxTemp = Math.max(max * n[i] , min * n[i]);
            int minTemp = Math.min(max * n[i] , min * n[i]);
            //
            max = Math.max(maxTemp , n[i]);
            min = Math.min(minTemp , n[i]);
            //
            global = Math.max(global , max);
        }
        return global;
    }
}
