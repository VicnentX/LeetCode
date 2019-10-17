package leetcode.array;

/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.



Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
 */

/**
 * Count the frequency of each number in A and B, respectively;
 * Count the frequency of A[i] if A[i] == B[i];
 * If countA[i] + countB[i] - same[i] == A.length, we find a solution; otherwise, return -1;
 * min(countA[i], countB[i]) - same[i] is the answer.
 */
public class MinimumDominoRotationsForEqualRow1007 {

    public int minDominoRotations(int[] a, int[] b) {

        if (a.length != b.length) return -1;

        int[] countFrequencyInA = new int[7];
        int[] countFrequencyInB = new int[7];
        int[] same = new int[7];

        for (int i = 0; i < a.length; ++i) {
            countFrequencyInA[a[i]]++;
            countFrequencyInB[b[i]]++;
            if (a[i] == b[i]) {
                same[a[i]]++;
            }
        }

        //get the result
        for (int i = 0; i < 7; ++i) {
            if (countFrequencyInA[i] + countFrequencyInB[i] - same[i] == a.length) {
                return Math.min(countFrequencyInA[i], countFrequencyInB[i]) - same[i];
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        MinimumDominoRotationsForEqualRow1007 minimumDominoRotationsForEqualRow1007 = new MinimumDominoRotationsForEqualRow1007();
        //2
        System.out.println(
                minimumDominoRotationsForEqualRow1007.minDominoRotations(new int[] {2,1,2,4,2,2}, new int[] {5,2,6,2,3,2}));
        //-1
        System.out.println(
                minimumDominoRotationsForEqualRow1007.minDominoRotations(new int[] {3,5,1,2,3}, new int[] {3,6,3,3,4}));
    }

}
