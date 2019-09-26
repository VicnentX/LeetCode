package Akuna;

import java.util.Arrays;

/**
 * Akuna 的quant dev面试，本来投的是quant research的岗位，也就随便投了一个，两个小时五道题；
 * 第一题：将一组int vector 转化为bit, 然后按照出现（1）的个数来给他们排序，若1出现的次数相同则按照原int大小排序，
 * （不难，最笨的方法直接将他们储存在一组二维vector中排序然后输出即可），最后有三个 test好像没过，有点恶心。
 */



public class SortArrayByBit1 {

    public int[] sortArrays(int[] nums) {
        Integer[] newNums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(newNums, (a,b) -> (countBits(b) == countBits(a) ? a - b : countBits(a) - countBits(b)));
        nums = Arrays.stream(newNums).mapToInt(Integer::intValue).toArray();
        return nums;
    }

    private int countBits(int num) {
        int cnt = 0;
        while (num > 0) {
            if ((num & 1) == 1) cnt++;
            num >>= 1;
        }
        return cnt;
    }


    public static void main(String[] args) {
        SortArrayByBit1 sortArrayByBit1 = new SortArrayByBit1();
        int[] ret = sortArrayByBit1.sortArrays(new int[] {5,3,8});
        for (int i: ret) {
            System.out.println(i);
        }
    }
}
