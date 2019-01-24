package leetcode.backtracking_TrieNode;

/*
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Seen this question in a real interview before?

 */

import java.util.*;
public class permutationSequence60 {
    public String getPermutation(int n, int k) {
        //build a factorial array
        int[] factorial = new int[n];
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1 ; i < n ; ++i) {
            sum *= i;
            factorial[i] = sum;
        }
        //build list
        List<Integer> nums = new ArrayList<>();
        for (int i = 1 ; i <= n ; ++i) {
            nums.add(i);
        }
        //
        --k;
        //
        StringBuilder ret = new StringBuilder();
        for (int i = 1 ; i <= n ; ++i) {
            int index = k / factorial[n - i];
            ret.append(nums.get(index));
            nums.remove(index);
            k -= index * factorial[n - i];
        }
        return ret.toString();
    }

    public static void main (String[] args) {
        permutationSequence60 ps = new permutationSequence60();
        System.out.println(ps.getPermutation(4 ,9));
    }
}
