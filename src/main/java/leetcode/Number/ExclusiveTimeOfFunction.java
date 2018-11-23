package leetcode.Number;

import java.util.*;

public class ExclusiveTimeOfFunction {

    public int[] exclusiveTime(int n, List<String> logs) {

//这边用栈的思路要记住 并且总结为什么用栈 不用hashmap
        int[] ret=new int[n];
        Stack<Integer> stack=new Stack<>();
        int prevTime=0;

        for(String i:logs){
            String[] s=i.split(":");
            int id=Integer.parseInt(s[0]);
            int timeStamp=Integer.parseInt(s[2]);

            //下面分start还是end讨论
            if(s[1].equals("start")){
                if(!stack.isEmpty()){
                    ret[stack.peek()]+=timeStamp-prevTime;
                }
                stack.push(id);
                prevTime=timeStamp;
            }else{
                stack.pop();
                ret[id] += timeStamp-prevTime+1;
                prevTime=timeStamp+1;
            }
        }

        return ret;

    }


    public static void main(String[] args){
        ExclusiveTimeOfFunction et=new ExclusiveTimeOfFunction();
        List<String> input=new LinkedList<>();
        input.add("0:start:0");
        input.add("1:start:2");
        input.add("1:end:5");
        input.add("0:end:6");



        for(int i=0;i<2;i++){
            System.out.println(et.exclusiveTime(2,input)[i]);
        }



    }
}
