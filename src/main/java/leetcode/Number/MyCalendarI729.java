package leetcode.Number;

import java.util.*;

public class MyCalendarI729 {

    TreeMap<Integer,Integer> calendar;

    public MyCalendarI729() {
        calendar=new TreeMap<>();
    }

    public boolean book(int start, int end) {

//        Integer floKey=calendar.floorKey(start);
//        Integer celKey=calendar.ceilingKey(start);
//
//        if(floKey!=null && calendar.get(floKey)>start){
//            return false;
//        }
//        if(celKey!=null && celKey<end){
//            return false;
//        }
//        calendar.put(start,end);
//        return true;


            Integer low = calendar.lowerKey(end);

            if(low == null || calendar.get(low) <= start) {
                calendar.put(start, end);
                return true;
            }
            return false;
    }

    public static void main(String[] args){
        MyCalendarI729 mc=new MyCalendarI729();
        System.out.println(mc.calendar);
        System.out.println(mc.book(10,20));
        System.out.println(mc.book(15,25));
        System.out.println(mc.book(20,30));
        System.out.println(mc.calendar);

    }
}

/**
 * Your MyCalendarI729 object will be instantiated and called as such:
 * MyCalendarI729 obj = new MyCalendarI729();
 * boolean param_1 = obj.book(start,end);
 */
