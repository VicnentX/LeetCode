package leetcode.Number;

public class ReshapeMatrix {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length;
        int m = nums[0].length;
        int[][] ret = new int[r][n * m / r];

        if(r >= n * m){
            ret = new int[n * m][1];
        }

        int row = 0;
        int col = 0;

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                ret[row][col] = nums[i][j];
                ++col;
                if(col == n * m / r){
                    col = 0;
                    ++row;
                }
            }
        }
        return ret;
    }

}
