package Amazon.full_time2020.final_round_加油少年_coding;

/*
arrA[] = {3, 6, 4, 8},
arrB[] = {4, 6, 8, 3}

keep arraA unchange
4, 8 / 4, 3 / 8, 3

swap 4 with 8,   arrB = {8, 6, 4, 3}
swap 8 with 3,   arrB = {3, 6, 4, 8}

result: 2

Minimum swaps to make two arrays identical
*/

/*
Minimum
 */

import javafx.util.Pair;

import java.util.*;

public class MinSwapCnt {

    public int minSwapTwoArray(int[] a, int[] b) {
        //store b into map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < b.length; ++i) {
            map.put(b[i], i);
        }
        //try to change b according to a
        int cnt = 0;
        for (int i = 0; i < a.length; ++i) {
            if (b[i] == a[i]) continue;
            if (!map.containsKey(a[i])) {
                System.out.println("IMPOSSIBLE");
                return -1;
            }
            swap(a, b, map, i);
            if (a[i] != b[i]) {
                System.out.println("IMPOSSIBLE");
                return -1;
            }
            cnt++;
        }

        return cnt;
    }

    private void swap(int[] a, int[] b, Map<Integer, Integer> map, int i) {
        int j = map.get(a[i]);
        int temp = b[i];
        b[i] = b[j];
        b[j] = temp;
    }

    public static void main(String[] args) {
        MinSwapCnt minSwapCnt = new MinSwapCnt();
        System.out.println(minSwapCnt.minSwapTwoArray(new int[] {1,2,3,4,5}, new int[] {1,5,4,3,2}));
        System.out.println(minSwapCnt.minSwapTwoArray(new int[] {0, 6, 4, 8}, new int[] {8, 3, 6, 4}));
    }

}
