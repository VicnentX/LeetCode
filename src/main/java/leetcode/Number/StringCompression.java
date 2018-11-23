package leetcode.Number;

import java.util.*;

public class StringCompression {

        public int compress(char[] chars) {
            Arrays.sort(chars);
            int cnt = 1;
            int sum = 0;
            for(int i = 1 ; i < chars.length ; ++i){
                if(chars[i] == chars[i - 1]){
                    ++cnt;
                }else{
                    sum += (Integer.toString(cnt).length() + 1);
                    cnt = 1;
                }
            }
            sum += (String.valueOf(cnt).length() + 1);
            return sum;
        }

        public static void main(String[] args){
            StringCompression sc = new StringCompression();
            System.out.println(sc.compress(new char[]{'a','a','b','b','c','c','c'}));
        }
}
