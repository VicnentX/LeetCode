package COEN379;

import java.util.*;

//suppose that all characters in the pattern p are distinct .
//show how to accelerate to run in time O(n) on an n-character text t

//这个算法就是找出所有的

public class DistinctNaiveStringMatcher {
    public void allOccurrenceShift(String t , String p) {
        //do have to hash it anymore
        int n = t.length();
        int m = p.length();
        int k = 0;
        int s = 0;
        while (s <= n - m) {
            int i = 1;//i is the skip length
            if (t.charAt(s) == t.charAt(0)) {
                i = 0;
                while (i < m && t.charAt(s + i) == p.charAt(i)) {
                    ++i;
                    if (i == m) {
                        System.out.println("the shift could be " + s);
                    }
                }
            }
            s = s + i;
        }
    }
    public static void main (String[] args) {
        DistinctNaiveStringMatcher dn = new DistinctNaiveStringMatcher();
        dn.allOccurrenceShift("aAcabcabc" , "abc");
    }
}
