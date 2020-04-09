package leetcode.Number;

import java.util.*;

public class SimplifyPath {

    public String simplifyPath(String path) {

        //特别注意这边新建的时候不可以用Stack<String>，因为stack只能用最上面拿出来，Deque是双通道；
        Deque<String> stack=new LinkedList<>();
        HashSet<String> set=new HashSet<>(Arrays.asList("..",".",""));

        String[] s=path.split("/");
        for(String i:s){
            if(i.equals("..") && !stack.isEmpty()){
                stack.pop();
            }else if(!set.contains(i)){
                stack.push(i);
            }
        }

        String ret="";
        for(String i:stack){
            ret="/"+i+ret;
        }

        return ret==""?     "/" : ret;

    }

    public static void main(String[] args){
        SimplifyPath sp=new SimplifyPath();
        System.out.println(sp.simplifyPath("/a/./b/../../c/"));
    }
}
