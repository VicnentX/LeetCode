package twitter.twitter_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给一个list, 比如[2,2,5,6],
 * 要求去重(只能通过增加的方式), 并使得最终list的sum最小.
 * 所以这个list去重后是[2,3,5,6],但是只要求输出最终的sum.
 * 这道题似乎见过, 但是想不起来哪里见过了, 也很简单, 就没找原题了
 */

public class RemoveDuplicateByCrement {
    public int CalculteSum(int[] list) {
        List<Integer> temp = new ArrayList<>();
        Collections.sort(temp);
        int sum = 0;
        Arrays.sort(list);
        int ladder = list[0];

        for (int num : list) {
            if (num < ladder) {
                sum += ladder;
                ladder ++;
            } else {
                sum += num;
                ladder = num + 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        RemoveDuplicateByCrement removeDuplicateByCrement = new RemoveDuplicateByCrement();
        System.out.println(removeDuplicateByCrement.CalculteSum(new int[] {2,2,6,6,7,7}));
    }
}
