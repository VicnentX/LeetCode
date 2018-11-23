package leetcode.Number;

import java.util.*;
import  java.util.function.Function;

public class FindAndReplaceInString {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {

//方法一
//         StringBuffer ret=new StringBuffer(S);
//         int len=indexes.length;

//         int[][] sorted=new int[len][len];
//         for(int i=0;i<len;i++){
//             sorted[i]=new int[]{i,indexes[i]};
//         }

//         Arrays.sort(sorted,(a,b)->a[1]-b[1]);
//         for(int i=len-1;i>=0;i--){
//             int j=sorted[i][0];//就是序号 可能是乱序
//             int index=sorted[i][1];//这是需要开始替换的位置，是逆序
//             if(S.startsWith(sources[j],index)){
//                 ret.replace(index,index+sources[j].length(),targets[j]);
//             }
//         }

//         return ret.toString();

//方法二
        List<int[]> sorted=new ArrayList<>();
        for(int i=0;i<indexes.length;i++){
            sorted.add(new int[]{indexes[i],i});
        }
        Collections.sort(sorted,Comparator.comparing(i->-i[0]));//特别注意 这边是i【0】排序 因为并不一定indexes【i】里面i小 这个数字也小。
        for(int[] k:sorted){
            int i=k[0];
            int j=k[1];
            String s=sources[j];
            String t=targets[j];
            if(S.substring(i,i+s.length()).equals(s)){
                S=S.substring(0,i)+t+S.substring(i+s.length());
            }
        }

        return S;

    }
}
