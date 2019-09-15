package GaoSheng;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 就是每一行最大的那个取出来 然后每个人挑 都是按大的挑 最后比较两个人的差值
 */

public class MatrixGame {
    public int cntDiff(int[][] board) {
        final int M = board.length;
        final int N = board[0].length;
        PriorityQueue<Integer> pqOfThreeInt = new PriorityQueue<>((a,b) -> b - a);
        int[] sums = new int[N];
        int diff = 0;

        //make the first line element maximum of specific line
        for (int i = 0 ; i < N ; ++i) {
            int colMax = -100;
            for (int j = 0 ; j < M ; ++j) {
                colMax = Math.max(colMax, board[j][i]);
            }
            sums[i] = colMax;
        }

        //sort
        Arrays.sort(sums);

        //calculate
        int sign = 1;
        for (int i = N - 1 ; i >= 0 ; --i) {
            diff += sums[i] * sign;
            sign *= -1;
        }

        return diff;
    }

    public static void main(String[] args) {
        MatrixGame matrixGame = new MatrixGame();

        System.out.println(
                matrixGame.cntDiff(new int[][]{{3,7,5,3,4,5}, {4,5,2,6,5,4}, {7,4,9,7,8,3}})
        );
    }
}
