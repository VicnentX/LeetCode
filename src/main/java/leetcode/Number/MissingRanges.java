package leetcode.Number;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class MissingRanges {

//    public List<String> findMissingRanges(int[] A, int lower, int upper) {
//        if(A==null) return null;
//        List<String> res = new ArrayList<String>();
//        for(int i=0; i<A.length; i++) {
//            while(i<A.length && A[i] == lower ) {lower++; i++;}
//            if(i>=A.length) break;
//            if(A[i] == lower+1) {
//                res.add("\""+ String.valueOf(lower)+"\"");
//            } else {
//                res.add("\"" + lower + "\"" + "->" + "\""+ (A[i]-1) +"\"");
//            }
//            lower = A[i] + 1;
//        }
//
//        if(lower == upper) {
//            res.add(String.valueOf(lower));
//        } else if(lower < upper ){
//            res.add("\"" + lower +"\"" + "->" + "\""+ upper+"\"");
//        }
//        return res;
//    }


//    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
////        List<String> res = new LinkedList<String>();  //这和下面应该是一样的
//        List<String> res = new ArrayList<String>();
//        // 初始化prev为lower-1，判断是否存在“第一个”区间
//        int prev = lower - 1, curr = 0;
//        for(int i = 0 ; i <= nums.length; i++){
//            // 当遍历到length时，设置curr为upper+1，判断是否存在“最后一个”区间
//            curr = i == nums.length ? upper + 1 : nums[i];
//            // 如果上一个数和当前数相差大于1，说明之间有区间
//            if(curr - prev > 1){
//                res.add(getRanges(prev+1, curr-1));
//            }
//            prev = curr;
//        }
//        return res;
//    }
//
//    private String getRanges(int from, int to){
//        return from == to ? String.valueOf(from) : from + "->" + to;
//    }


    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret=new ArrayList();
        long prev=(long)lower-1;
        long curr=0;

        for(int  i=0;i<=nums.length;i++){
            curr=i==nums.length?    (long)upper+1   :   nums[i];

            if(curr-prev>1){
                ret.add(showrange(prev+1,curr-1));
            }
            prev=curr;
        }
        return ret;

    }

    private String showrange(long l, long l1) {
        return l==l1?   String.valueOf(l)   :   l+"->"+l1;
    }


    public static void main(String[] args){
        MissingRanges mr=new MissingRanges();

        System.out.println(mr.findMissingRanges(new int[]{0,1,3,50,75},0,99));


    }

}
