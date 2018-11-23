package intuit;

import java.util.*;

public class Domain {

    public Map<String , Integer> click(String[][] s){
        String key;
        int value;
        Map<String , Integer> ret = new HashMap<>();
        for(String[] pair : s){
            String[] array = pair[0].split("\\.");
            StringBuilder sb = new StringBuilder();
            sb.append(array[array.length - 1]);
            key = sb.toString();
            value = Integer.valueOf(pair[1]);
            ret.put(key , ret.getOrDefault(key , 0) + value);
            for(int i = array.length - 2 ; i >= 0 ; --i){
                sb.insert(0 , ".");
                sb.insert(0 , array[i]);
                key = sb.toString();
                value = Integer.valueOf(pair[1]);
                ret.put(key , ret.getOrDefault(key , 0) + value);
            }
        }
        return ret;
    }

    public static void main(String[] args){
        Domain d = new Domain();
        System.out.println(d.click(new String[][]{{"google.com" , "60"},{"yahoo.com" , "50"},{"sports.yahoo.com" , "80"}}));
    }
}
