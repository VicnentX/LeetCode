package intuit;

import java.util.*;

public class BadgeRecord {
    public List<HashSet<String>> securityRecord(String[][] s){
        //0 means the person is outside
        //1 means the person is inside
        //in normal situation , the value should change between 0 - 1
        //if value is 2 ,it means he forgot to badge exit
        //if value is -1 , it means he forgot ot badge enter
        HashSet<String> noBadgeEnter = new HashSet<>();
        HashSet<String> noBadgeExit = new HashSet<>();
        HashMap<String , Integer> map = new HashMap<>();//Integer refers the status of employee
        // having badged enter will add one on the value , vice versa delete one on the value
        for(String[] k : s){
            if(!map.containsKey(k[0])){
                map.put(k[0] , 0);
            }
            if(k[1].equals("enter")){
                if(map.get(k[0]) + 1 == 2){
                    noBadgeExit.add(k[0]);
                }
                map.put(k[0] , 1);
            }else{//equals exit
                if(map.get(k[0]) - 1 == -1){
                    noBadgeEnter.add(k[0]);
                }
                map.put(k[0] , 0);
            }
        }
        //scan map again and catch the element whose value is 1 , so that means he enter without leaving
        for(String k : map.keySet()){
            if(map.get(k) == 1){
                noBadgeExit.add(k);
            }
        }
        List<HashSet<String>> ret = new ArrayList<>();
        ret.add(noBadgeExit);
        ret.add(noBadgeEnter);
        return ret;
    }

    public static void main(String[] args){
        BadgeRecord br = new BadgeRecord();
        System.out.println(br.securityRecord(new String[][]{
                {"Martha" , "exit"} ,
                {"Paul" , "enter"} ,
                {"Martha" , "enter"} ,
                {"Martha" , "exit"} ,
                {"Jennifer" , "enter"} ,
                {"Paul" , "enter"} ,
                {"Curtis" , "enter"} ,
                {"Paul" , "exit"} ,
                {"Martha" , "enter"} ,
                {"Martha" , "exit"} ,
                {"Jennifer" , "exit"}
        }));
    }
}
