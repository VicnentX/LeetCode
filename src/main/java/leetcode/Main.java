package leetcode;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.*;

public class Main {


    public static void main(String[] args) {

//        String s = "  ";
//        String ss = s.trim();
//        System.out.println(ss.length());
//
//        Set<ArrayList<Integer>> ret = new HashSet<>();
//        ret.add(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
//        ret.add(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
//        System.out.println(ret.size());

        //i will test get a candidate under 1000 loop and also I will calculate a ave using 100 candidates

//        double ave = 0;
//
//        for (int tt = 0 ; tt < 100 ; ++tt) {
//            int cnt = 0;
//            for (int t = 0 ; t < 1000 ; ++t) {
//                double i = 1 + Math.random();
//                double j = Math.random();
//                if (j < 1 / i) ++cnt;
//            }
//            System.out.println(cnt / 1000.0);
//            ave += (cnt / 1000.0);
//        }
//
//        System.out.println("ave = " + ave / 100);

//        int num = 5;
//        System.out.println((char)(num + '0'));
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("ad");
//        StringBuilder newsb = sb.reverse();
//        System.out.println(sb);
//        System.out.println(newsb);
        List<Integer> ret = new ArrayList<>();
        int cnt = 0;
        int num = 560;
        OUT:
        while (cnt <= 2) {
            ++num;
            boolean isComposite = false;
            for (int a = 2; a <= num - 2; ++a) {
                if (gcd(a, num) != 1) {
                    isComposite = true;
                    break;
                }
            }
            //num is a composite
            if (isComposite) {
                for (int a = 2 ; a <= num - 1 ; ++a) {
                    if (powerMod(a , num - 1 , num) != 1) {
                        continue OUT;
                    }
//                    if (Math.pow(a , num - 1) % num != 1) {
//                        continue OUT;
//                    }
                }
                ret.add(num);
                ++cnt;
            }
        }
        System.out.println(ret);
    }
    //return greatest common divisor of a and b
    private static int gcd(int a , int b) {
        if (b == 0) return a;
        return gcd( b ,a % b);
    }

    //powerMod(b, e, m) = (b^e) % m
    private static int  powerMod(int b , int e , int m) {
        if (e == 0) return 1;
        int temp = powerMod(b , e/2 , m);
        int ans = (temp * temp) % m;
        if (e % 2 == 0) {
            return ans;
        } else {
            return (b * ans) % m;
        }
    }
}
