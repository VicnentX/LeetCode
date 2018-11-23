package intuit;

import java.util.*;

public class PositionOfRectangle {

    int x;
    int y;
    boolean flag;
    public List<List<Integer>> PrintPosition(int[][] matrix){
        List<List<Integer>> ret = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0 ; i < m ; ++i){
            for(int j = 0 ; j < n ; ++j){
                if(matrix[i][j] == 0){
                    flag = true;
                    helper(matrix , n , m , i , j);
                    List<Integer> tem = Arrays.asList(i , j , x , y);
                    ret.add(tem);
                }
            }
        }
        return ret;
    }

    private void helper(int[][] matrix ,int n , int m , int i , int j){
            if(matrix[i][j] == 0){

                matrix[i][j] = 1;

                if(i + 1 < m && j + 1 < n && matrix[i + 1][j] == 1 && matrix[i][j + 1] == 1 ||
                        i + 1 >= m && j + 1 < n && matrix[i][j + 1] == 1 ||
                        j + 1 >= n && i + 1 < m && matrix[i + 1][j] == 1 ||
                        i + 1 >= m && j + 1 >= m){
                    if(flag == true){
                        x = i ;
                        y = j ;
                        flag = false;
                    }
                    return;
                }
                if(j + 1 < n){
                    helper(matrix , n , m , i , j + 1);
                }
                if(i + 1 < m){
                    helper(matrix , n , m , i + 1, j );
                }
            }
    }

    public static void main(String[] args){
        PositionOfRectangle pr = new PositionOfRectangle();
        System.out.println(pr.PrintPosition(new int[][] {
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,1,1}
        }));
    }
}
