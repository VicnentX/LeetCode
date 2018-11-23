package leetcode.ScanWindow;

import java.util.*;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; ++i){
            if(i < 9){
                sb.append(s.charAt(i));
            }
            else if(i == 9){
                sb.append(s.charAt(i));
                set.add(sb.toString());
            }
            else{
                sb.deleteCharAt(0);
                sb.append(s.charAt(i));
                if(!set.add(sb.toString()) && set2.add(sb.toString())){
                    ret.add(sb.toString());
                }
            }
        }
        return ret;
    }

    public static void main(String[] args){
        RepeatedDNASequences rd = new RepeatedDNASequences();
        System.out.println(rd.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
