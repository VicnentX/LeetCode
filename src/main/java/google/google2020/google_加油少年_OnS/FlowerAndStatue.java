package google.google2020.google_加油少年_OnS;

/*

一：鲜花和雕像
在2D的矩阵上，某些坐标有鲜花（F），某些坐标又雕像(S)。其余的坐标是空白的（O）
例：
OOFOO
OOSOO
FFOFF
SOOOF
OFOOO

雕像会挡住这个方向的鲜花, 问：在每一个空白位置上，朝四个方向看，一共能看到几朵鲜花。
在这个例子里：在左下角的空白，朝上被雕像挡住，朝右右1朵鲜花，一共1朵
在正中间的空白，上0，左2，右2，下0，一共4朵
输出一个2d矩阵

follow up:
1. 写test case，conrer case.
2. 如果矩阵很大，不能一次性读入内存怎么办。假设矩阵是稀疏的（鲜花和雕像很少）
 */

import com.sun.tools.javac.comp.Flow;

public class FlowerAndStatue {
    public int[][] getFlowerCntForEachNode(char[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] left = new int[M + 2][N + 2];
        int[][] up = new int[M + 2][N + 2];
        int[][] right = new int[M + 2][N + 2];
        int[][] down = new int[M + 2][N + 2];

        //update up and left
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                char cur = grid[i - 1][j - 1];
                if (cur == 'O') {
                    left[i][j] = left[i][j - 1];
                    up[i][j] = up[i - 1][j];
                }
                if (cur == 'F') {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                }
                if (cur == 'S') {
                    //do nothing
                }
            }
        }
        //update right and down
        for (int i = M ; i >= 1; --i) {
            for (int j = N ; j >= 1; --j) {
                char cur = grid[i - 1][j - 1];
                if (cur == 'O') {
                    right[i][j] = right[i][j + 1];
                    down[i][j] = down[i + 1][j];
                }
                if (cur == 'F') {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
                if (cur == 'S') {
                    //do nothing
                }
            }
        }

        //store ret
        int[][] ret = new int[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 'O') {
                    ret[i][j] = left[i + 1][j + 1] + up[i + 1][j + 1] + right[i + 1][j + 1] + down[i + 1][j + 1];
                } else {
                    ret[i][j] = -1;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        FlowerAndStatue flowerAndStatue = new FlowerAndStatue();
        int[][] ret = flowerAndStatue.getFlowerCntForEachNode(new char[][] {{'O','O','F','O','O'},{'O','O','S','O','O'},{'F','F','O','F','F'},{'S','O','O','O','F'},{'O','F','O','O','O'}});
        for (int[] line: ret) {
            for (int node: line) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
