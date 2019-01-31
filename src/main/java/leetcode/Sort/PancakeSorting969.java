package leetcode.Sort;

/*
Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.



Example 1:

Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation:
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
Example 2:

Input: [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.


Note:

1 <= A.length <= 100
A[i] is a permutation of [1, 2, ..., A.length]
 */


/*
Actually it is a np-hard problem to prove this problem could be solved in a polynomial time.


NP-hardness (non-deterministic polynomial-time hardness),
in computational complexity theory,
is the defining property of a class of problems that are, informally,
"at least as hard as the hardest problems in NP".
A simple example of an NP-hard problem is the subset sum problem.


A more precise specification is:
a problem H is NP-hard when every problem L in NP can be reduced in polynomial time to H;
that is, assuming a solution for H takes 1 unit time,
we can use H‎'s solution to solve L in polynomial time.[1][2] As a consequence,
finding a polynomial algorithm to solve any NP-hard problem would give polynomial algorithms for all the problems in NP,
which is unlikely as many of them are considered difficult.[3]
The most simple pancake sorting algorithm must have at most 2n − 3 flips. In this algorithm,
a number of selection sort, we bring the largest pancake not yet sorted to the top with one flip;
take it down to its final position with one more flip;
and repeat this process for the remaining pancakes.
 */

import java.util.*;

public class PancakeSorting969 {
    public List<Integer> pancakeSort(int[] nums) {
        //position[]
        List<Integer> ret = new ArrayList<>();
        if (nums.length == 0) return ret;
        int n = nums.length;
        int[] position = new int[n + 1];
        for (int i = 0 ; i < n ; ++i) {
            position[nums[i]] = i;
        }

        for (int i = n ; i >= 1 ; --i) {
            //if num i is not in its position , num i should be in the i - 1;
            if (position[i] != i - 1) {
                flip(nums , position , position[i] , ret);
                flip(nums , position , i - 1 , ret);
            }
        }

        return ret;
    }

    private void flip (int[] nums , int[] position , int j , List<Integer> ret) {
        int i = 0;
        ret.add(j + 1);
        while (i < j) {
            position[nums[j]] = i;
            position[nums[i]] = j;
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
            ++i;
            --j;
        }
    }

    public static void main (String[] args) {
        PancakeSorting969 ps = new PancakeSorting969();
        System.out.println(ps.pancakeSort(new int[] {3,2,4,1}));
    }
}

