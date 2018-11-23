package Wish;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class Keyboard {
    public int entryTime(String s , String keypad){
        Map<Character , int[]> map = new HashMap<>();
        int index = 0;
        for(int i = 0 ; i < 3 ; ++i){
            for(int j = 0 ; j < 3 ; ++j){
                map.put(keypad.charAt(index) , new int[]{i , j});
                ++index;
            }
        }
        int ret = 0;
        for(int i = 1 ; i < s.length(); ++i){
            ret += times(s.charAt(i) , s.charAt(i - 1) , map);
        }
        return ret;
    }
    private int times(Character c1 , Character c2 , Map<Character , int[]> map){
        if(c1 == c2){
            return 0;
        }
        if(Math.abs(map.get(c1)[0] - map.get(c2)[0]) <= 1 && Math.abs(map.get(c1)[1] - map.get(c2)[1]) <= 1){
            return 1;
        }else{
            return 2;
        }
    }
    public static void main(String[] args){
        Keyboard kb = new Keyboard();
        System.out.println(kb.entryTime("1591" , "123456789"));
    }
}
