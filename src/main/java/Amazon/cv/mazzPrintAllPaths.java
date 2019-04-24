package Amazon.cv;

/*
题目就是给你一个迷宫 有些点不通 给你起始点和重点 输出输出所有可能的路径

我做的时候会建一个随机的 打印出来 然后打印出来所以的路径
 */

import java.util.*;
public class mazzPrintAllPaths {

    List<List<int[]>> ret = new ArrayList<>();

    int[][] dirs = new int[][]{{1,0} , {-1,0} , {0,1} , {0 , -1}};
    public List<List<int[]>> findAllPaths (int m , int n) {
        int[][] mazz = new int[m][n];
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                mazz[i][j] = (int)(Math.random() * 2);
            }
        }

        System.out.println("this mazz is like :");
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                System.out.print(mazz[i][j] + " ");
            }
            System.out.println();
        }
        //find a random start
        int[] start = new int[] {0 , 0};
        while (mazz[start[0]][start[1]] == 0) {
            start = new int[] {(int)(Math.random() * m) , (int)(Math.random() * n)};
        }

        //find a random end
        int[] end = new int[] {m - 1 , n - 1};
        while (mazz[end[0]][end[1]] == 0) {
            end = new int[] {(int)(Math.random() * m) , (int)(Math.random() * n)};
        }

        //print start and end point
        System.out.println("start is :" + start[0] + start[1]);
        System.out.println("end is :" + end[0] + end[1]);

        dfs(m , n , start , end , mazz , new int[m][n] , new ArrayList<>() , ret);

        return ret;
    }

    private void dfs(int m , int n , int[] cur , int[] end , int[][] mazz , int[][] visited , List<int[]> path , List<List<int[]>> ret) {
        if (cur[0] < 0 || cur[0] >= m || cur[1] < 0 || cur[1] >= n || mazz[cur[0]][cur[1]] == 0 || visited[cur[0]][cur[1]] == 1) return ;

        visited[cur[0]][cur[1]] = 1;
        path.add(new int[] {cur[0] , cur[1]});
        if (cur[0] == end[0] && cur[1] == end[1]) {
            ret.add(new ArrayList<>(path));
            visited[cur[0]][cur[1]] = 0;
            path.remove(path.size() - 1);
            return;
        }
        for (int[] dir : dirs) {
            dfs(m , n , new int[] {cur[0] + dir[0] , cur[1] + dir[1]} , end , mazz , visited , path , ret);
        }

        visited[cur[0]][cur[1]] = 0;
        path.remove(path.size() - 1);
    }

    public static void main (String[] args) {
        mazzPrintAllPaths mpap = new mazzPrintAllPaths();
        for (List<int[]> list : mpap.findAllPaths(4,5)) {
            for (int[] k : list) {
                System.out.print(k[0] + "-" + k[1] + " + ");
            }
            System.out.println();
        }

    }
}
