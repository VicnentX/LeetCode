package leetcode.Number;

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        //方法一只能用在t里面没有重复的情况！！！！！！！
//        Map<Character, Integer> map = new HashMap<>();
//        Set<Character> set = new HashSet<>();
//        PriorityQueue<Map.Entry<Character, Integer>> minHeap = new PriorityQueue<>((a,b) -> (a.getValue()-b.getValue()));
//
//        for(char ch : t.toCharArray()){
//            set.add(ch);
//        }
//
//        for(char ch : t.toCharArray()){
//            map.put(ch,-1);
//        }
//
//        for(Map.Entry<Character, Integer> entry : map.entrySet()){
//            minHeap.add(entry);
//        }
//
//        int len = s.length()+1;
//        String ret = "";
//
//        for(int i = 0; i < s.length() ; ++i){
//
//            if(set.contains(s.charAt(i))){
//                map.put(s.charAt(i),i);
//            }
//
//            minHeap.add(minHeap.poll());//因为不这样做 我的minHeap的头元素并不会改变 ，相当于强制他heap一下，这边我的感觉我不去poll的话就不会重新heap
//
//            if(minHeap.peek().getValue() != -1){
//                if(i - minHeap.peek().getValue() + 1 < len){
//                    len = i - minHeap.peek().getValue() + 1;
//                    ret = s.substring(minHeap.peek().getValue() , i + 1);
//                }
//            }
//        }
//
//        return ret;


        //方法二

        if(s == null || s.length() < t.length() || s.length() == 0){
            return "Please input proper strings";
        }

        HashMap<Character , Integer> map = new HashMap<>();

        for(char ch : t.toCharArray()){
            map.put(ch , map.getOrDefault(ch , 0) + 1);
        }

        int left = 0;
        int minLeft = 0;
        int minLen = s.length() + 1;
        int cnt = 0;

        for(int i = 0; i < s.length() ; ++i){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i) , map.get(s.charAt(i)) - 1);
                if(map.get(s.charAt(i)) >= 0){
                    ++cnt;
                }

                while(cnt == t.length()){
                    if(i - left + 1 < minLen){
                        minLeft = left;
                        minLen = i - left + 1;
                    }
                    if(map.containsKey(s.charAt(left))){
                        map.put(s.charAt(left) , map.get(s.charAt(left)) + 1);
                        if(map.get(s.charAt(left)) > 0){
                            --cnt;
                        }
                    }
                    ++left;
                }
            }
        }
        return minLen>s.length()?   "Min Window Substring NOT FOUND" :   s.substring(minLeft , minLeft + minLen);



        //方法三 4ms
        // if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        // int[] cnt = new int[128];
        // int from = 0;
        // int total = t.length();
        // int min = Integer.MAX_VALUE;
        // for (char c: t.toCharArray()) {
        //     cnt[c]++;
        // }
        // for (int i = 0, j = 0; i < s.length(); i++) {
        //     if (cnt[s.charAt(i)]-- > 0) total--;
        //     while (total == 0) {
        //         if (i - j + 1 < min) {
        //             min = i - j + 1;
        //             from = j;
        //         }
        //         if (++cnt[s.charAt(j++)] > 0) total++;
        //     }
        // }
        // return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }

    public static void main(String[] args){
        MinimumWindowSubstring mw = new MinimumWindowSubstring();
        System.out.println(mw.minWindow("ADOBECODEBANC","ABC"));
        System.out.println(mw.minWindow("a","aa"));
    }

}
