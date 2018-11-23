package leetcode.Number;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        //方法一
        // while(k-->0){
        //     int tem = nums[nums.length - 1];
        //     for(int i = nums.length -1; i > 0; --i){
        //         nums[i] = nums[i - 1];
        //     }
        //     nums[0] = tem;
        // }

        //方法二
        if(nums == null) return ;
        int n = nums.length;
        k %= n;
        if(k == 0) return ;

        reverse(nums, 0, n - 1 - k);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int st, int ed){
        while(st < ed){
            int tem = nums[st];
            nums[st++] = nums[ed];
            nums[ed--] = tem;
        }
    }

    public static void main(String[] args){
        RotateArray ra = new RotateArray();
        int[] input = {1,2,3,4,5};
        ra.rotate(input, 2);
        for(int k : input){
            System.out.println(k);
        }
    }

}
