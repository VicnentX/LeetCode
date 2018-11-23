package leetcode.Number;

public class LongestSubStringWithAtLeastKRepeatingCharacters {

    public int longestSubstring(String s, int k) {
         //方法一：
         if(s == null || s.length() == 0) return 0;

         int[] c = new int[26];
         for(int i = 0; i < s.length(); ++i){
             ++c[s.charAt(i) - 'a'];
         }
         boolean flag = true;
         for(int i = 0; i < c.length; ++i){
             if(c[i] < k && c[i] > 0){
                 flag = false;
                 break;
             }
         }
         //return the length of string if this string is a valid string
         if(flag == true) return s.length();
         //otherwise we use all the infrequent elements as splits
         int ret = 0;
         int start = 0 , cur = 0;
         while(cur < s.length()){
             if(c[s.charAt(cur) - 'a'] < k){
                 ret = Math.max(ret , longestSubstring(s.substring(start,cur), k));
                 start = cur + 1;//注意 这边写start=++cur的话 是简单的指把cur+1的结果给了start 而cur自身没有改变
             }
             ++cur;
         }
         //the corner case
         ret = Math.max(ret , longestSubstring(s.substring(start), k));
         System.out.println(s.substring(10).length() == 0? true:false); //这边若start=s.length that means the substring.length() = 0;
         return ret;


//        //方法二O（nlogn） worst O(n2)
//        return LongestSubstringHelper(s , k);
//    }
//
//    private int LongestSubstringHelper(String s , int k){
//
//        if(s.length() < k)  return 0;
//
//        int[] c = new int[26];
//        int index = 0;
//        int smallest = Integer.MAX_VALUE;
//        for(int i = 0;i < s.length(); ++i){
//            ++c[s.charAt(i) - 'a'];
//        }
//        //find the alph with minimal frequency
//        for(int i = 0; i < c.length; ++i){
//            if(c[i] != 0 && c[i] < smallest){
//                smallest = c[i];
//                index = i;
//            }
//        }
//
//        if(smallest >= k)   return s.length();
//
//        int max = 0;
//        String[] array = s.split(Character.toString((char)('a' + index)));
//        for(int i = 0 ; i < array.length ; ++i){
//            max = Math.max(max , LongestSubstringHelper(array[i] , k));
//        }
//        return max;
//
    }

    public static void main(String[] args){
        LongestSubStringWithAtLeastKRepeatingCharacters ls = new LongestSubStringWithAtLeastKRepeatingCharacters();
        System.out.println(ls.longestSubstring("sdfsdfsdfa",3));
    }



}
