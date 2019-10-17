package google.google2020.google_加油少年_OA;

/*
2.Relative Sort
Relative sorting is defined as sorting two arrays
(both in strictly ascending order)
 such that the only operation allowed is
 swapping ith element of one array with the ith element of the other array.
An array is said to be in strictly ascending order
if ith element of the array is smaller than (i+1)th element of the array

You are given two arrays of size N.
Print the minimum number of swaps required to make both arrays relatively sorted.

example:
input:
4
1 4 4 9
2 3 5 10
output:
1

 */

/**
 * 我在想法就是for loop once
 * 先查看我的现在的最小值是不是比之前那个最小值要大 然后最大值也是
 *
 * 如果不是就swap一下
 *
 * 最后min（swap， n - swap）
 */

public class RelativeSort {

    public int minSwap(final int N, int[] array1, int[] array2) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        int cnt = 0;
        int unneccessary = 0;
        for (int i = 0 ; i < array1.length; ++i) {
            int tempMin = Math.min(array1[i], array2[i]);
            int tempMax = Math.max(array1[i], array2[i]);
            if (tempMin <= min || tempMax <= max) return -1;
            if (tempMin > max) {
                unneccessary++;
            } else if (array1[i] <=array1[i - 1]) {
                swap(array1, array2, i);
                cnt++;
            }
            min = tempMin;
            max = tempMax;
        }
        return cnt > N - cnt ? N - cnt - unneccessary : cnt;
    }

    private void swap(int[] a1, int[] a2, int i) {
        int temp = a1[i];
        a1[i] = a2[i];
        a2[i] = temp;
    }

    public static void main(String[] args) {
        RelativeSort relativeSort = new RelativeSort();
        System.out.println(relativeSort.minSwap(4, new int[] {1,4,4,9}, new int[] {2,3,5,10}));
    }

}
