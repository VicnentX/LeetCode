package COEN379;

import java.util.*;

public class HW2 {
    public List<Long> CarMicheal(int TotalNumber) {
        List<Long> ret = new ArrayList<>();
        long cnt = 0;
        long num = 3;
        OUT:
        while (cnt < TotalNumber) {
            ++num;
            boolean isComposite = false;
            //这边一定要注意算到sqrt（num）就可以了
            for (long a = 2; a <= Math.sqrt(num); ++a) {
                if (gcd(a, num) != 1) {
                    isComposite = true;
                    break;
                }
            }
            //num is a composite
            if (isComposite) {
                for (long a = 2 ; a <= num - 1 ; ++a) {
                    if (gcd(a , num) == 1) {
                        if (powerMod(a , num - 1 , num) != 1) {
                            continue OUT;
                        }
//                        if (Math.pow(a , num - 1) % num != 1) {
//                            continue OUT;
//                        }
                    }
                }
                ret.add(num);
                ++cnt;
            }
        }
        return ret;
    }

    //return greatest common divisor of a and b
    private long gcd(long a , long b) {
        if (b == 0) return a;
        return gcd( b ,a % b);
    }

    //powerMod(b, e, m) = (b^e) % m
    private long powerMod(long b , long e , long m) {
        if (e == 0) return 1;
        long temp = powerMod(b , e/2 , m);
        long ans = (temp * temp) % m;
        if (e % 2 == 0) {
            return ans;
        } else {
            return (b * ans) % m;
        }
    }

    public static void main (String[] args) {
        HW2 hw2 = new HW2();
        System.out.println(hw2.CarMicheal(20));
    }
}
