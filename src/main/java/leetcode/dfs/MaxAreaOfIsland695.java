package leetcode.dfs;

public class MaxAreaOfIsland695 {

    int tem;

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ret = 0;
        for(int i = 0 ; i < m ; ++i){
            for(int j = 0 ; j < n ; ++j){
                if(grid[i][j] == 1){
                    tem = 0;//这里的tem 不能int 加了的话就和外面的tem是两个东西了
                    dfs(grid , i , j , m , n);
                    ret = Math.max(tem , ret);
                }
            }
        }
        return ret;
    }
    private void dfs(int[][] grid , int i , int j , int m , int n){
        if(grid[i][j] == 1){
            grid[i][j] = 0;
            ++tem;
            if(i - 1 >= 0){
                dfs(grid , i - 1 , j , m , n);
            }
            if(i + 1 < m){
                dfs(grid , i + 1 , j , m , n);
            }
            if(j - 1 >= 0){
                dfs(grid , i , j - 1 , m , n);
            }
            if(j + 1 < n){
                dfs(grid , i , j + 1 , m , n);
            }
        }
        return;
    }

    public static void main(String[] args){
        MaxAreaOfIsland695 ma = new MaxAreaOfIsland695();
        int[][] in = new int[][]{{1,1,0,0,0,} , {1,1,0,0,0} , {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println(ma.maxAreaOfIsland(in));
    }

}
