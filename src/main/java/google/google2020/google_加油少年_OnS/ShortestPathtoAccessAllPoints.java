package google.google2020.google_加油少年_OnS;

public class ShortestPathtoAccessAllPoints {

    int ret = Integer.MAX_VALUE;

    public int solve(int m, int n, int[][] points, int[] start) {
        dfs(m, n, start[0], start[1], 0, points, new int[points.length], 0);
        return ret;
    }

    private void dfs(int m, int n, int i, int j, int cnt, int[][] points, int[] visited, int sum) {

        if (cnt == points.length) {
            ret = Math.min(sum, ret);
            return;
        }
        for (int index = 0; index < points.length; ++index) {
            if (visited[index] == 0) {
                int x = points[index][0];
                int y = points[index][1];
                visited[index] = 1;
                dfs(m, n, x, y, cnt + 1, points, visited, sum + Math.abs(i - x) + Math.abs(j - y));
                visited[index] = 0;
            }
        }
    }

    public static void main(String[] args) {
        ShortestPathtoAccessAllPoints shortestPathtoAccessAllPoints = new ShortestPathtoAccessAllPoints();
        //6
        int ret = shortestPathtoAccessAllPoints.solve(3,3,new int[][] {{0,2},{1,0},{2,2}}, new int[] {0,0});
        System.out.println(ret);
    }

}
