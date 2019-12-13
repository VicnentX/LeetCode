package google.google2020.google_加油少年_VO;

/*
题目是关于随机数的，随机生成一个以及多个5x5的Bingo card
 */

import java.util.Random;

public class BingoCardGenerator {
    public int[][] solve() {
        int[][] ret = new int[5][5];
        for (int j = 0; j < 5; ++j) {
            fillColumns(ret, j);
        }
        ret[4][2] = ret[3][2];
        ret[3][2] = ret[2][2];
        ret[2][2] = -1;
        return ret;
    }

    private void fillColumns(int[][] ret, int j) {
        Random random = new Random();
        int n = 15;
        int[] nums = new int[n];
        int index = 0;
        for (int i = j * 15 + 1; i < (j + 1) * 15 + 1; ++i) {
            nums[index++] = i;
        }
        for (int i = 0 ; i < 5; ++i) {
            int randomIndex = random.nextInt(n) + i;
            swap(nums, i, randomIndex);
            ret[i][j] = nums[i];
            --n;
        }
        return;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        BingoCardGenerator bingoCardGenerator = new BingoCardGenerator();
        int[][] ret = bingoCardGenerator.solve();
        for (int[] rows: ret) {
            for (int i: rows) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
