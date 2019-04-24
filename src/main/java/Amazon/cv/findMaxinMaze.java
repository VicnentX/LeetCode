package Amazon.cv;

/*
在一个二位数组里面找到最大值 从0，0开始走 每次的差值不大于1
 */

public class findMaxinMaze {
    private int ret = -1;
    int[][] dirs = new int[][] {{1,0} , {-1,0} , {0,1} , {0,-1}};
    public int findMax () {
        int[][] maze = new int[][]{{1,2,3,4,5},{6,5,4,9,10}};
        dfs(0 , 0 , maze , new int[2][5]);
        return ret;
    }
    private void dfs (int i , int j , int[][] maze , int[][] visited) {
        ret = Math.max(ret , maze[i][j]);
        visited[i][j] = 1;
        for (int[] dir : dirs) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            if (ii < 0 || ii >= 2 || jj < 0 || jj >= 5 || visited[ii][jj] == 1
                    || Math.abs(maze[i][j] - maze[ii][jj]) > 1) continue;
            dfs(ii , jj , maze , visited);
        }
    }

    public static void main (String[] args) {
        findMaxinMaze fmm = new findMaxinMaze();
        System.out.println(fmm.findMax());
    }
}
