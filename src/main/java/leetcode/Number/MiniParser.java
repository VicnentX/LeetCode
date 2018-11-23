package leetcode.Number;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

import java.util.*;

public class MiniParser {
//    public NestedInteger deserialize(String s) {
//        Stack<NestedInteger> stack=new Stack<>();
//        String num="";
//
//        for(char c:s.toCharArray()){
//            switch(c){
//                case '['://present a list
//                    stack.push(new NestedInteger());
//                    break;
//                case ']':
//                    if(!num.equals("")){
//                        stack.peek().add(new NestedInteger(Integer.parseInt(num)));//添加数字到本层的list里
//                    }
//                    NestedInteger ret=stack.pop();//本层结束;
//                    num="";//num从新变为0；
//                    if(!stack.isEmpty()){//说明站内有更高层次的list曾在
//                        stack.peek().add(ret);
//                    }else{//说明栈空 那就结束啦 输出ret就可以啦
//                        return ret;
//                    }
//                    break;
//                case ',':
//                    if(!num.equals("")){
//                        stack.peek().add(new NestedInteger(Integer.parseInt(num)));
//                    }
//                    num="";
//                    break;
//                default:
//                    num+=c;
//            }
//        }
//
//        if(!num.equals("")){//只有数字的情况
//            return new NestedInteger(Integer.parseInt(num));
//        }
//
//        return null;//上面情况都没有 那就是空的情况。
//    }
}
