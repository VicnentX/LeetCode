package intuit.Intuit2020;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrixQuestionSet5 {

    /**
     * find the only one 0's rectangle
     */
    /*
    第一题：Intuit 网上coding competition的一道题
    给一个矩阵，矩阵里的每个元素是1，其中有一个0组成的矩形，其余都是1，找出这个矩形的左上及右下坐标。
    Ans:遍历matrix碰到第一个0就上下左右四个方向找，得到上左下右边界后break。
    提问时空间复杂度。测试所有能想到的corner cases，就是这一步耽误了不少时间。
    https://www.geeksforgeeks.org/find-rectangles-filled-0/
    example：
    input:
    [
    [1,1,1,1,1,1],
    [0,0,1,0,1,1],
    [0,0,1,0,1,0],
    [1,1,1,0,1,0],
    [1,0,0,1,1,1]
    output:
    [
    [1,0,2,1],
    [1,3,3,3],
    [2,5,3,5],
    [4,1,4,2]
     */


    public int[] findOnly0sRectangle(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    return findLocOf0s(i, j, grid, M, N);
                }
            }
        }

        return new int[] {-1,-1,-1,-1};
    }

    /*
    example：
    input:
    [
    [1,1,1,1,1,1],
    [0,0,1,0,1,1],
    [0,0,1,0,1,0],
    [1,1,1,0,1,0],
    [1,0,0,1,1,1]
    output:
    [
    [1,0,2,1],
    [1,3,3,3],
    [2,5,3,5],
    [4,1,4,2]
     */
    private List<int[]> findAllOnly0sRectangles(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        List<int[]> ret = new ArrayList<>();

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    ret.add(findLocOf0s(i, j, grid, M, N));
                }
            }
        }

        return ret;
    }

    /*
    第三题：connected components
只不过是返回一个list of list，每个list是一个component的所有点坐标
那个图是1,01,0组成的矩阵，0组成的就是各种图形。跟前面关系的确不大
如果矩阵里有多个不规则的形状，返回这些形状。这里需要自己思考并定义何谓“返回这些形状”

     */

    /**
     * 第三题就用List<List<Loc>> store all the patterns and each pattern is list<Loc>
     */

    private int[] findLocOf0s(int i, int j, int[][] grid, final int M, final int N) {
        Queue<Loc> curLevelLocs = new LinkedList<>();
        curLevelLocs.add(new Loc(i, j));
        grid[i][j] = 1;
        Loc lastElement = new Loc(i, j);
        while (!curLevelLocs.isEmpty()) {
            lastElement = curLevelLocs.peek();
            Loc cur = curLevelLocs.poll();
            addToNextLevelLocs(cur, grid, M, N, curLevelLocs);
        }
        return new int[] {i, j, lastElement.x, lastElement.y};
    }


    private void addToNextLevelLocs(Loc cur, int[][] grid, final int M, final int N, Queue<Loc> curLevelLocs) {
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0, 1}, {0, -1}};
        for (int[] dir: dirs) {
            int x = cur.x + dir[0];
            int y = cur.y + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 0) {
                grid[x][y] = 1;
                curLevelLocs.add(new Loc(x,y));
            }
        }
    }

    private static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        MatrixQuestionSet5 matrixQuestionSet = new MatrixQuestionSet5();
        int[] ret1 = matrixQuestionSet.findOnly0sRectangle(new int[][]{{1,1,1}, {1,0,0}});
        for (int i: ret1) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("__________end of q1___________");


        List<int[]> ret2 = matrixQuestionSet.findAllOnly0sRectangles(new int[][] {
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,1,1}
        });
        for (int[] loc: ret2) {
            for (int i: loc) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("__________end of q2___________");
    }

}
