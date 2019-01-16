package leetcode.SlideWindow_TwoPointers;
/*
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 */

import java.util.*;

public class FruitIntoBaskets904 {
    public int totalFruit(int[] tree) {
        int ret = 0;
        if (tree == null || tree.length == 0) return ret;
        int n = tree.length;
        Map<Integer , Integer> map = new HashMap<>();
        int start = 0 , end = 0;
        while (end < n) {
            map.putIfAbsent(tree[end] , 0);
            map.put(tree[end] , map.get(tree[end]) + 1);
            while (map.size() > 2) {
                int value = map.get(tree[start]) - 1;
                if (value == 0) {
                    map.remove(tree[start]);
                    ++start;
                } else {
                    map.put(tree[start] , value);
                    ++start;
                }
            }
            ret = Math.max(ret , end - start + 1);
            ++end;
        }
        return ret;
    }

    public static void main (String[] args) {
        FruitIntoBaskets904 fi = new FruitIntoBaskets904();
        //Input: [3,3,3,1,2,1,1,2,3,3,4]
        //Output: 5
        System.out.println(fi.totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
    }
}
