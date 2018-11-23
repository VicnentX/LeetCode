package leetcode.stack;

import java.util.*;
public class ExclusiveTimeOfFunction {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ret = new int[n];
        Stack<String[]> stack = new Stack<>();
        for(String k : logs){
            String[] tem = k.split(":");
            if(tem[1].equals("start")){
                if(stack.isEmpty()){
                    stack.push(new String[]{tem[0] , tem[2]});
                }else{
                    ret[Integer.parseInt(stack.peek()[0])] += Integer.parseInt(tem[2]) - Integer.parseInt(stack.peek()[1]);
                    stack.push(new String[]{tem[0] , tem[2]});
                }
            }
            else{//equals end
                ret[Integer.parseInt(tem[0])] += Integer.parseInt(tem[2]) + 1 - Integer.parseInt(stack.pop()[1]);
                if(!stack.isEmpty()){
                    stack.push(new String[]{stack.pop()[0] , String.valueOf(Integer.parseInt(tem[2]) + 1)});
                }
            }
        }
        return ret;
    }

    public static void main(String[] args){
        List<String> input = new ArrayList<>();
        input.add("0:start:0");
        input.add("1:start:2");
        input.add("1:end:5");
        input.add("0:end:6");
        ExclusiveTimeOfFunction et = new ExclusiveTimeOfFunction();
        int[] out = et.exclusiveTime(2 , input);
        for(int k : out){
            System.out.println(k);
        }
    }
}
