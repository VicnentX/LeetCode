package leetcode.string;
/*
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.



Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"
 */

public class CountAndSay38 {
    public String countAndSayMethod1(int n) {
        if (n < 1) return "";
        StringBuilder cur = new StringBuilder("1");
        StringBuilder pre;
        int count;
        char say;
        for (int i = 2 ; i <= n ; ++i) {
            pre = cur;
            cur = new StringBuilder();
            count = 1;
            say = pre.charAt(0);
            for (int j = 1 ; j < pre.length() ; ++j) {
                if (pre.charAt(j) != say) {
                    cur.append(count).append(say);
                    count = 1;
                    say = pre.charAt(j);
                } else {
                    ++count;
                }
            }
            cur.append(count).append(say);
        }
        return cur.toString();
    }


    public String countAndSayMethod2(int n) {
        if(n == 1){
            return "1";
        }
        //递归调用，然后对字符串处理
        String str = countAndSayMethod2(n-1) +"*";//为了str末尾的标记，保证末尾的数字是不同的 并且不需要的
        char[] c = str.toCharArray();
        int count = 1;
        String s = "";
        for(int i = 0; i < c.length - 1;i++){
            if(c[i] == c[i+1]){
                count++;//计数增加
            }else{
                s = s + count + c[i];//上面的*标记这里方便统一处理
                count = 1;//初始化
            }
        }
        return s;
    }

    public static void main (String[] args) {
        CountAndSay38 ca= new CountAndSay38();
        System.out.println(ca.countAndSayMethod1(5));
        System.out.println(ca.countAndSayMethod2(5));
    }

}
