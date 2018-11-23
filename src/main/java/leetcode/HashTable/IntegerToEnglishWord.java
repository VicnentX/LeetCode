package leetcode.HashTable;

import java.util.*;

public class IntegerToEnglishWord {
    class Solution {
        public String numberToWords(int num) {
            if(num == 0){
                return "Zero";
            }
            Map<Integer , String> map = new HashMap<>();
            map.put(0, "");
            map.put(-1, "Thousand");
            map.put(-2, "Million");
            map.put(-3, "Billion");
            map.put(1, "One");
            map.put(2, "Two");
            map.put(3, "Three");
            map.put(4, "Four");
            map.put(5, "Five");
            map.put(6, "Six");
            map.put(7, "Seven");
            map.put(8, "Eight");
            map.put(9, "Nine");
            map.put(10, "Ten");
            map.put(11, "Eleven");
            map.put(12, "Twelve");
            map.put(13, "Thirteen");
            map.put(14, "Fourteen");
            map.put(15, "Fifteen");
            map.put(16, "Sixteen");
            map.put(17, "Seventeen");
            map.put(18, "Eighteen");
            map.put(19, "Nineteen");
            map.put(20, "Twenty");
            map.put(30, "Thirty");
            map.put(40, "Forty");
            map.put(50, "Fifty");
            map.put(60, "Sixty");
            map.put(70, "Seventy");
            map.put(80, "Eighty");
            map.put(90, "Ninety");
            int n = num;
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            while(n > 0){
                StringBuilder part = threeDigitNum(n % 1000 , map);
                part.append(" ").append(map.get(idx--));
                sb.insert(0 ," ");
                sb.insert(0 , part);
                n /= 1000;
            }
            return sb.toString().trim();
        }

        public StringBuilder threeDigitNum(int num, Map<Integer, String> map) {
            int c = num % 10;
            num /= 10;
            int b = num % 10;
            int a = num / 10;
            StringBuilder res = new StringBuilder();
            if (a > 0) {
                res.append(map.get(a)).append(" Hundred");
            }
            if (b > 1) {
                res.append(" ").append(map.get(b * 10));
                res.append(" ").append(map.get(c));
            } else if (b == 1) {
                res.append(" ").append(map.get(b * 10 + c));
            } else {
                res.append(" ").append(map.get(c));
            }
            return res;
        }

    }
}
