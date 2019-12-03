package NextJump;



/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:

1 is typically treated as an ugly number.
n does not exceed 1690.
Seen this question in a real interview before?

 */

import java.util.ArrayList;
import java.util.List;

/**
 * The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/
 *
 * The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
 * because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:
 *
 * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
 * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
 * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
 * We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.
 *
 * Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.
 *
 * Every step we choose the smallest one, and move one step after,including nums with same value.
 *
 * Thanks for this author about this brilliant idea. Here is my java solution
 */


/**
 * We have an array k of first n ugly number. We only know, at the beginning, the first one, which is 1. Then
 *
 * k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:
 *
 * k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
 *
 * x here is multiplication.
 */

public class UglyNumberII264 {

    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        int t2 = 0, t3 = 0, t5 = 0;
        int[] k = new int[n];
        k[0] = 1;

        for (int i = 1; i < n; ++i) {
            k[i] = Math.min(k[t2] * 2, Math.min(k[t3] * 3, k[t5] * 5));
            if (k[i] == k[t2] * 2) t2++;
            if (k[i] == k[t3] * 3) t3++;
            if (k[i] == k[t5] * 5) t5++;
        }

        return k[n - 1];
    }

    //变种的题目，就是给一个范围, 然后问里面哪些数是可以只由3，5来组成的
    public int UglyNumbercnt(int left, int right) {
        int cnt = 0;
        if (left < 1 || left > right) return cnt;
        int t3 = 0, t5 = 0;

        List<Integer> k = new ArrayList<>();
        k.add(1);

        while (Math.min(k.get(t3) * 3, k.get(t5) * 5) <= right) {
            int curMinValid = Math.min(k.get(t3) * 3, k.get(t5) * 5);
            k.add(curMinValid);
            if (curMinValid >= left) cnt++;
            if (curMinValid == k.get(t3) * 3) t3++;
            if (curMinValid == k.get(t5) * 5) t5++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        UglyNumberII264 uglyNumberII264 = new UglyNumberII264();
        System.out.println(uglyNumberII264.nthUglyNumber(12));

        System.out.println(uglyNumberII264.UglyNumbercnt(200, 405));
    }
}
