package leetcode.Number;

import java.util.*;

public class Matrix542 {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Deque<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < m ; ++i){
            for(int j = 0 ; j < n ; ++j){
                if(matrix[i][j] == 0){
                    queue.add(new int[]{i , j});
                } else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int i , j;
        while(!queue.isEmpty()){
            int[] tem = queue.poll();
            i = tem[0];
            j = tem[1];
            if(i - 1 >= 0){
                if(matrix[i - 1][j] != 0){
                    if(matrix[i - 1][j] > matrix[i][j] + 1){
                        matrix[i - 1][j] = matrix[i][j] + 1;
                        queue.add(new int[]{i - 1 , j});
                    }
                }
            }
            if(j - 1 >= 0){
                if(matrix[i][j - 1] != 0){
                    if(matrix[i][j - 1] > matrix[i][j] + 1){
                        matrix[i][j - 1] = matrix[i][j] + 1;
                        queue.add(new int[]{i , j - 1});
                    }
                }
            }
            if(i + 1 < m){
                if(matrix[i + 1][j] != 0){
                    if(matrix[i + 1][j] > matrix[i][j] + 1){
                        matrix[i + 1][j] = matrix[i][j] + 1;
                        queue.add(new int[]{i + 1, j});
                    }
                }
            }
            if(j + 1 < n){
                if(matrix[i][j + 1] != 0){
                    if(matrix[i][j + 1] > matrix[i][j] + 1){
                        matrix[i][j + 1] = matrix[i][j] + 1;
                        queue.add(new int[]{i , j + 1});
                    }
                }
            }
        }


        return matrix;
    }
}
