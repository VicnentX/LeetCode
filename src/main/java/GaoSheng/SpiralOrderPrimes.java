package GaoSheng;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.math.LongMath.isPrime;

/**
 * 从左上角顺时针螺旋走 把所有遇到的质数放在一个list里面
 */

public class SpiralOrderPrimes {
    public List<Integer> addPrimeToList(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) return res;

        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        while(true){
            for(int i = left; i <= right; i++) checkThenAdd(matrix[top][i], res);
            top++;
            if(left > right || top > bottom) break;

            for(int i = top; i <= bottom; i++) checkThenAdd(matrix[i][right], res);
            right--;
            if(left > right || top > bottom) break;

            for(int i = right; i >= left; i--) checkThenAdd(matrix[bottom][i], res);
            bottom--;
            if(left > right || top > bottom) break;

            for(int i = bottom; i >= top; i--) checkThenAdd(matrix[i][left], res);
            left++;
            if(left > right || top > bottom) break;
        }

        return res;
    }

    private void checkThenAdd(int num, List<Integer> res) {
        if (isPrime(num)) res.add(num);
    }


    public static void main(String[] args) {
        SpiralOrderPrimes spiralOrderPrimes = new SpiralOrderPrimes();
        List<Integer> ret = spiralOrderPrimes.addPrimeToList(new int[][] {
                {7,7,3,8,1}, {13,5,4,5,2}, {9,2,12,3,9}, {6,12,1,11,41}
        });
        System.out.println(ret);
    }
}
