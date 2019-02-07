package COEN379;

import java.util.Random;
import java.util.*;

/*
this method is to use quick-select to find (nums.size() + 1) / 2 largest number
after quick-select,
all the number less than it is on the left side
    and
all the number equals and larger than it is on the right side
then keep the left side and drop off the right side (including the number itself)

proof :

 */

public class HW3_DeleteLargerHalf17_3_7 {

    public void Insert (int x , List<Integer> nums) {
        nums.add(x);
    }

    public void output (List<Integer> nums) {
        int stop = findKthLargest(nums , (nums.size() + 1) / 2);

        /*
        //nums = nums.subList(0 , stop);
        这个时候外部的nums并没有改变
        */
        //有 . 就是进入函数内部修改了
        int size = nums.size();
        for(int i = size - stop ; i > 0 ; i--){
            nums.remove(nums.size() - 1);
        }

        System.out.println(nums);

    }
    private int findKthLargest(List<Integer> nums, int k) {
        if (nums.size() == 1 && k == 1) return nums.get(0);
        //
        if (nums.size() == 0) return 0;
        int target = nums.size() - k;
        int left = 0;
        int right = nums.size() - 1;
        while (true) {
            int i = partition(left , right , nums);
            if (i < target) {
                left = i + 1 ;
            } else if (i > target) {
                right = i - 1;
            } else {
                return i;
                //here returns the index of kth largest
            }
        }
    }

    private int partition (int left , int right , List<Integer> nums) {
        //shuffle
        if (right > left) {
            Random rd = new Random();
            int index = left + 1 + rd.nextInt(right - left);
            swap(left , index , nums);
        }

        //partition
        int j = left;
        for (int i = left + 1 ; i <= right ; ++i) {
            if (nums.get(i) < nums.get(left)) {
                ++j;
                swap(j , i , nums);
            }
        }
        swap(left , j , nums);
        return j;
    }

    private void swap (int a , int b , List<Integer> nums) {
        int tem = nums.get(a);
        nums.set(a , nums.get(b));
        nums.set(b , tem);
    }

    public static void main (String[] args) {
        HW3_DeleteLargerHalf17_3_7 dl = new HW3_DeleteLargerHalf17_3_7();
        List<Integer> nums = new ArrayList<>();
        dl.Insert(1  , nums);
        dl.Insert(5  , nums);
        dl.Insert(2  , nums);
        dl.Insert(4  , nums);
        dl.Insert(3  , nums);
        dl.output(nums);
        nums.add(0);
        dl.output(nums);
    }
}

/*
i have another idea that is use a arraylist(named filter) to filter the number.
details are as below:
    when I insert a number , I add it to the filter.
    if the filter's size == 3
        then sort it (because there is only 3 element , so the cost is constant)
            then I keep the first element to the output array
            and drop off the last element
                so right now only one element left
    then continue

    at last there is two situation :
        (1) one element is left , just drop it
        (2) two elements are left =>
            sort it and store the left one to the output array and drop the right one.
 */
