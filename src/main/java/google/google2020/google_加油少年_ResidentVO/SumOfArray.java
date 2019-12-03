package google.google2020.google_加油少年_ResidentVO;

/*
第一题要求返回两个array相加结果
e.g.
Input: [1,2,3,4] [1,2,3]
Output: [1,3,5,7]

Input: [9,9,9] [1]
Output: [1,0,0,0]
 */

import java.util.ArrayList;
import java.util.List;

public class SumOfArray {
    public List<Integer> solve(int[] nums1, int[] nums2) {
        List<Integer> ret = new ArrayList<>();
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry + (i >= 0 ? nums1[i] : 0) + (j >= 0 ? nums2[j] : 0);
            int remain = sum % 10;
            carry = sum / 10;
            ret.add(0, remain);
            i--;
            j--;
        }
        return ret;
    }

    public static void main(String[] args) {
        SumOfArray sumOfArray = new SumOfArray();
        for(int i: sumOfArray.solve(new int[] {9,9,9}, new int[] {1})) {
            System.out.println(i);
        }
        System.out.println("------------------");
        for(int i: sumOfArray.solve(new int[] {1,2,3,4}, new int[] {1,2,3})) {
            System.out.println(i);
        }
    }
}
