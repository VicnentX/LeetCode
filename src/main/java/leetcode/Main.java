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



    }
}
