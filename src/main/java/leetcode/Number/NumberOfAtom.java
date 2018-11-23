package leetcode.Number;

import java.util.*;

public class NumberOfAtom {

    private int k = 0;


    public String countOfAtoms(String s) {

        if(s == null || s.length() == 0){
            return s;
        }
        String ret = "";
        TreeMap<String,Integer> result = Combination(s);
        for(String i : result.keySet()){
            System.out.println(i + ":" + result.get(i));
            if(result.get(i) == 1){
                ret += i;
            }else{
                ret += (i + result.get(i));
            }

        }
        return ret;
    }

//    public TreeMap<String , Integer> Combination(String s){
//
//        String element = "";
//        TreeMap<String,Integer> map = new TreeMap<>();
//        int cnt = 0;
//
//        while(k < s.length()){
//            if(Character.isUpperCase(s.charAt(k))){
//                if(!element.isEmpty()){
//                    map.put(element , map.getOrDefault(element,0) + 1);
//                    element = "";
//                }
//                element += s.charAt(k);
//                ++k;
//            }
//
//            else if(Character.isLowerCase(s.charAt(k))){
//                element += s.charAt(k);
//                ++k;
//            }
//
//            else if(Character.isDigit(s.charAt(k))){
//                while(Character.isDigit(s.charAt(k))){
//                    cnt = cnt * 10 + s.charAt(k) - '0';
//                    ++k;
//                    if( k >= s.length()){
//                        break;
//                    }
//                }
//                map.put(element , map.getOrDefault(element , 0) + cnt);
//                cnt = 0;
//                element = "";
//
//            }
//
//            else if(s.charAt(k) == '('){
//
//                if(!element.isEmpty()){
//                    map.put(element , map.getOrDefault(element,0) + 1);
//                    element = "";
//                }
//
//                ++k;
//                TreeMap<String,Integer> tem = Combination(s);
//                for(String i : tem.keySet()){
//                    if(map.containsKey(i)){
//                        map.put(i , map.get(i) + tem.get(i));
//                    }else{
//                        map.put(i , tem.get(i));
//                    }
//                }
//            }
//
//            else{//it means ) here
//                if(!element.isEmpty()){
//                    map.put(element , map.getOrDefault(element,0) + 1);
//                    element = "";
//                }
//
//                ++k;
//
//                if(Character.isDigit(s.charAt(k))){
//                    while(Character.isDigit(s.charAt(k))){
//                        cnt = cnt * 10 + s.charAt(k) - '0';
//                        ++k;
//                        if(k >= s.length()){
//                            break;
//                        }
//                    }
//
//                    for(String i : map.keySet()){
//                        map.put(i , cnt * map.get(i));
//                    }
//
//                    cnt = 0;
//
//                    return map;
//                }else{//end of string or isUpperCase
//                    return map;
//                }
//
//            }
//        }
//        if(!element.isEmpty()){
//            map.put(element , map.getOrDefault(element,0) + 1);
//        }
//        return map;
//    }

    public TreeMap<String , Integer> Combination(String s) {


        TreeMap<String, Integer> map = new TreeMap<>();

        while (k < s.length()) {
            if (s.charAt(k) == '(') {
                ++k;
                TreeMap<String, Integer> tem = Combination(s);
                //merge tem with map
                for (String i : tem.keySet()) {
                    if (map.containsKey(i)) {
                        map.put(i, map.get(i) + tem.get(i));
                    } else {
                        map.put(i, tem.get(i));
                    }
                }
            } else if (s.charAt(k) == ')') {
                //figure out the cnt if any
                ++k;
                int start = k;
                while (k < s.length() && Character.isDigit(s.charAt(k))) {
                    ++k;
                }
                int cnt = 1;
                if (start < k) {
                    cnt = Integer.parseInt(s.substring(start, k));
                    for (String i : map.keySet()) {
                        map.put(i, cnt * map.get(i));
                    }
                }
                return map;

            } else {//这里把元素和后面的个数一起分析了 不用再加上新的分类情况
                int start = k;
                ++k;
                //figure out the name of atom
                while (k < s.length() && Character.isLowerCase(s.charAt(k))) {
                    ++k;
                }
                String name = s.substring(start, k);
                //figure out the count of atom
                start = k;
                while (k < s.length() && Character.isDigit(s.charAt(k))) {
                    ++k;
                }
                int cnt = 1;
                if (start < k) {
                    cnt = Integer.parseInt(s.substring(start, k));
                }
                //没有就直接放入 ； 有就cnt加上之前有的
                map.put(name , map.getOrDefault(name,0) + cnt);
            }
        }
        return map;
    }

    public static void main(String[] args){
        NumberOfAtom na=new NumberOfAtom();
        //System.out.println(na.countOfAtoms("H2O"));
        System.out.println(na.countOfAtoms("Mg(OH)2"));
    }

}
