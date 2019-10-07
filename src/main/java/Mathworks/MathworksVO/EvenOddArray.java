package Mathworks.MathworksVO;

/*
输入一个正整数m，要求输出一个数组[m, ..., 1],
如果m是偶数，下个数字是m/2，
如果m是奇数，下个数字是3*m+1，以此类推直到1。

 */

public class EvenOddArray {
    public int[] buidArray(int m) {
        int[] ret = new int[m];
        ret[0] = m;
        for (int i = 0; i < m - 1; i++) {
            if (ret[i] % 2 == 0) {
                ret[i + 1] = ret[i] / 2;
            } else {
                ret[i + 1] = 3 * ret[i] + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        EvenOddArray evenOddArray = new EvenOddArray();
        for (int i: evenOddArray.buidArray(10)) {
            System.out.println(i);
        }
    }
}
