package leetcode;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        String s = "  ";
        String ss = s.trim();
        System.out.println(ss.length());

        Set<ArrayList<Integer>> ret = new HashSet<>();
        ret.add(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
        ret.add(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
        System.out.println(ret.size());

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

        int num = 5;
        System.out.println((char)(num + '0'));

    }
}
