package leetcode.Sort;

/*
Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.



Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.


Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000


Seen this question in a real interview before?

 */

public class SortArrayByParityII922 {
    public int[] sortArrayByParityII(int[] a) {
        if (a.length == 0) return new int[] {};
        int n = a.length;
        int odd = n - 1;
        int even = n - 2;
        for (int i = 0 ; i < Math.max(odd , even) + 1 ; ++i) {
            if (i % 2 == 0) {
                if (a[i] % 2 != 0) {
                    swap(i , odd , a);
                    --i;
                    odd = odd - 2;
                }
            } else {
                if (a[i] % 2 == 0) {
                    swap(i , even , a);
                    --i;
                    even = even - 2;
                }
            }
        }
        return a;
    }
    private void swap (int a , int b , int[] nums) {
        int tem = nums[a];
        nums[a] = nums[b];
        nums[b] = tem;
    }

    public static void main (String[] args) {
        SortArrayByParityII922 sa = new SortArrayByParityII922();
        for (int k : sa.sortArrayByParityII(new int[] {1,2,3,4})) {
            System.out.print(k + " ");
        }
    }
}
