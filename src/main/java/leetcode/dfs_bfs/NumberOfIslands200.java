package leetcode.dfs_bfs;

public class NumberOfIslands200 {
    int m;
    int n;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int cnt = 0;

        for(int i = 0 ; i < m ; ++i){
            for(int j = 0 ; j < n ; ++j){
                if(grid[i][j] == '1'){
                    dfs(grid , i , j);
                    ++cnt;
                }
            }
        }

        return cnt;
    }
    private void dfs(char[][] grid , int i , int j){
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0'){
            return ;
        }
        grid[i][j] = '0';
        dfs(grid , i - 1 , j);
        dfs(grid , i + 1 , j);
        dfs(grid , i , j - 1);
        dfs(grid , i , j + 1);
    }
}
