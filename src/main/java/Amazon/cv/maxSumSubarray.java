package Amazon.cv;

/*
给定array of integers ， 找出长度为k的max sum subarray
我把题目改成找到第一个开始的index
 */

public class maxSumSubarray {
    public int findIndexofMaxSumSubarray (int[] nums , int k) {
        if (k > nums.length) return 0;
        int maxSum = 0;
        int i;
        int sum = 0;
        for (i = 0 ; i < k ; ++i) {
            sum += nums[i];
        }
        maxSum = sum;
        int ret = 0;
        //continue to scan the array
        for (; i < nums.length ; ++i) {
            sum = sum - nums[i - k] + nums[i];
            if (sum > maxSum) {
                maxSum = sum;
                ret = i - k + 1;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        maxSumSubarray mss = new maxSumSubarray();
        System.out.println(mss.findIndexofMaxSumSubarray(new int[] {1,2,3,4,5,6,7} , 3));
    }
}
