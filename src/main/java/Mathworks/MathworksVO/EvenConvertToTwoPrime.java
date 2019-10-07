package Mathworks.MathworksVO;

/*
已知任意一个偶数都可以拆两个质数之和，写个function 返回这两个质。
 */

import static com.google.common.math.LongMath.isPrime;

public class EvenConvertToTwoPrime {

    public int[] findTwoPrime(int num) {
        for (int i = 1; i <= num / 2; ++i) {
            if (isPrime(i) && isPrime(num - i)) {
                return new int[] {i, num - i};
            }
        }
        return new int[]{-1, -1};
    }

    private boolean isPrimeDIY(int num) {
        for (int a = 2; a <= Math.sqrt(num); ++a) {
            if (gcd(a, num) != 1) {
                return false;
            }
        }
        return true;
    }

    private long gcd(long a , long b) {
        if (b == 0) return a;
        return gcd( b ,a % b);
    }

    public static void main(String[] args) {
        EvenConvertToTwoPrime evenConvertToTwoPrime = new EvenConvertToTwoPrime();
        for (int i: evenConvertToTwoPrime.findTwoPrime(16)) {
            System.out.println(i);
        }
    }
}
