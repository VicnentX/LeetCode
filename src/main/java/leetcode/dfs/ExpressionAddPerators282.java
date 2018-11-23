package leetcode.dfs;

import java.util.*;

public class ExpressionAddPerators282 {
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<>();
        if(num == null || num.length() == 0){
            return ret;
        }
        dfs(num , ret , "" , 0 , 0 , 0 , target);
        return ret;
    }
    private void dfs(String num , List<String> ret , String path , int pos , long eval , long multed , int target){
        if(pos == num.length()){
            if(eval == target){
                ret.add(path);
            }
        }
        for(int i = pos ; i < num.length() ; ++i){
            if(i != pos && num.charAt(pos) == '0'){//！！！！！！这里小细节 ‘’不能漏！！！！！这里是pos 不是i！！！！
                break;
            }
            long cur = Long.parseLong(num.substring(pos , i + 1));
            if(pos == 0){
                dfs(num , ret , path + cur , i + 1 , cur , cur , target);
            }else{
                dfs(num , ret , path + "+" + cur , i + 1 , eval + cur , cur , target);
                dfs(num , ret , path + "-" + cur , i + 1 , eval - cur , -cur , target);
                dfs(num , ret , path + "*" + cur , i + 1 , eval - multed + multed * cur , multed * cur , target);
            }
        }
    }
}
