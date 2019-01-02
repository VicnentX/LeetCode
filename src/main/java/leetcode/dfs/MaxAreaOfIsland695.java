package leetcode.dfs;

public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland(int[][] grid) {
        int ret = 0;
        if (grid.length == 0 || grid[0].length == 0) return ret;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (grid[i][j] == 1) {
                    ret = Math.max(ret , dfs(i , j , grid , m , n));
                }
            }
        }
        return ret;
    }
    private int dfs(int i , int j , int[][] grid , int m , int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        return dfs(i + 1, j , grid , m , n)
                + dfs(i - 1, j , grid , m , n)
                + dfs(i , j + 1, grid , m , n)
                + dfs(i , j - 1, grid , m , n)
                + 1;
    }

    public static void main(String[] args){
        MaxAreaOfIsland695 ma = new MaxAreaOfIsland695();
        int[][] in = new int[][]{{1,1,0,0,0,} , {1,1,0,0,0} , {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println(ma.maxAreaOfIsland(in));
    }

}
