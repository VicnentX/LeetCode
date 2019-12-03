package google.google2020.google_加油少年_ResidentVO;

/*
输入在二位平面上的一组点，输出两个点以至于两点连线可以使两边剩余点的数量一致。
 */

import javafx.util.Pair;

public class FindLineToDividePoints {
    //O(n3)
    public Pair<int[], int[]> solve(int[][] points) {
        final int N = points.length;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (canDivided(i, j, points, N)) {
                    return new Pair<>(points[i], points[j]);
                }
            }
        }
        System.out.println("IMPOSSIBLE");
        return null;
    }

    private boolean canDivided(int i, int j, int[][] points, int n) {
        int cnt = 0;
        //(y-y2)/(y1-y2) = (x-x2)/(x1-x2)。
        //houlian0 2017-07-03 14:41
        //(y1-y2)*x - (x1-x2)*y + x1*y2 - x2*y1 = 0
        int y1 = points[i][1];
        int y2 = points[j][1];
        int x1 = points[i][0];
        int x2 = points[j][0];
        for (int index = 0; index < n; ++index) {
            int x = points[index][0];
            int y = points[index][1];
            int calculate = (y1-y2)*x - (x1-x2)*y + x1*y2 - x2*y1;
            cnt += Integer.compare(calculate, 0);
        }
        return cnt == 0;
    }

    public static void main(String[] args) {
        FindLineToDividePoints findLineToDividePoints = new FindLineToDividePoints();
        Pair<int[], int[]> pair = findLineToDividePoints.solve(new int[][] {{0,0},{0,1},{1,0},{1,1},{-5, 0},{-1,8}});
        System.out.println(pair.getKey()[0] + " " + pair.getKey()[1]);
        System.out.println(pair.getValue()[0] + " " + pair.getValue()[1]);
    }
}
