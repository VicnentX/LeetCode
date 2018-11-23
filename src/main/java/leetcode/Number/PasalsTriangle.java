package leetcode.Number;

import java.util.*;

public class PasalsTriangle {

    public List<List<Integer>> generate(int n) {//generate 前n行

        //!!!!!!!!这个输出是[[1, 2, 1], [1, 2, 1], [1, 2, 1]]
        //这点值得研究 ，因为我直接ret.add（tem） 那就是我把tem放进去，当我后面tem改变的时候，前面放好的也会概念
        //所以我只能new一个新的List《integer》 ，里面把tem的值付给他，这样后面tem改变了 他也不会变
//        List<Integer> tem = new ArrayList<>();
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        //第一行
//        tem.add(1);
//        ret.add(tem);
//        //从第二行开始
//
//        for(int i = 1; i < n; ++i){
//            for(int j = i - 1; j >= 1; --j){
//                tem.set(j , tem.get(j) + tem.get(j - 1));
//            }
//            tem.add(1);
//            ret.add(tem);
//        }
//        return ret;


        List<Integer> tem = new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        //第一行
        tem.add(1);
        ret.add(new ArrayList<Integer>(tem));
        //从第二行开始

        for(int i = 1; i < n; ++i){
            for(int j = i - 1; j >= 1; --j){
                tem.set(j , tem.get(j) + tem.get(j - 1));
            }
            tem.add(1);
            ret.add(new ArrayList<Integer>(tem));
        }
        return ret;
    }

    public static void main(String[] args){
        PasalsTriangle pt = new PasalsTriangle();
        System.out.println(pt.generate(3));
    }

}
