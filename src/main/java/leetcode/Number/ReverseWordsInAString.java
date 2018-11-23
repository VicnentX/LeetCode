package leetcode.Number;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ReverseWordsInAString {

    //下面这种方法就更加的简单了，疯狂的利用到了Java的内置函数，
    // 这也是Java的强大之处，注意这里的分隔符没有用正则表达式，
    // 而是直接放了个空格符进去，后面还是有+号，跟上面的写法得到的效果是一样的，
    // 然后我们对字符串数组进行翻转，然后调用join()函数来把字符串数组拼接成一个字符串，
    // 中间夹上空格符即可，参见代码如下：
    public String ReverseWords(String s) {
/*
String s，StringBuffer sb，char【】ch之间的转换
bufer ---》string     String s=new String(sb);     or     String s= sb.toString();
char[]--->String      String s=new String(ch);

string-->buffer        StringBuffer sb=new StringBuffer(s);    or  StringBuffer sb=new StringBuffer();sb.append(s);
char[]--->buffer       s.valueOf(char[] ch);StringBuffer sb=new StringBuffer(s)   or  String s =new String(ch);StringBuffer                                 sb=new StringBuffer(s)
String-->char[]       char[] ch=s.toCharArray();
buffer--->char[]        String s=new String(sb);char[] ch=s.toCharArray();    or
                        s=sb.toString();char[] ch=s.toCharArray();
*/

//          if(s.isEmpty()) return s;

//             int cnt=0;
//             for(int i=0;i<s.length();i++){
//                 if(s.charAt(i) == ' '){
//                     cnt++;
//                 }
//             }
//             if(cnt==s.length()) return "";

//方法一
//     	List<String> result = new ArrayList();


//         int start = s.charAt(0)==' ' ? 1:0;

//         for(int i =1; i < s.length();i++){
//             if(s.charAt(i)==' '){
//                 if (s.charAt(i-1)!=' '){
//                     result.add(s.substring(start, i));
//                 }
//                 start = i +1;
//             }
//         }

//     //g->f
//         if(s.charAt(s.length()-1 )!= ' '){
//             result.add(s.substring(start));
//         }

// //方法一的第二部分的一种解法
//         //String[] newStr =  list.toArray(new String[1]); //返回一个包含所有对象的指定类型的数组
//         String[] newStr =  result.toArray(new String[1]);

//         String ret="";
//         for(int i=newStr.length-1;i>0;i--){
//             ret=ret+newStr[i]+" ";
//         }
//         ret=ret+newStr[0];

//         return ret;


//方法一的第二部分的一种解法
        // String[] newStr =  result.toArray(new String[1]);
        // Collections.reverse(Arrays.asList(newStr));
        // return String.join(" ",newStr);


//方法二 开头的判断是不是space 这里是不需要的
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);


//方法三   开头的判断是不是space 这里是不需要的
//        String[] words=s.trim().split("\\s+");
//        String ret="";
//        for(int i=words.length-1;i>0;i--){
//            ret=ret+words[i]+" ";
//        }
//        ret=ret+words[0];

//        return ret;


//方法四 因为array是固定长度 这种方法不能去掉中间的多个空格 头尾的空格可以用s=s.trim()来删除 解这道题不合适
//     s=s.trim();
//     char[] ch=s.toCharArray();

//     for(int i=0; i<ch.length-1-i; i++){
//         char tem=ch[i];
//         ch[i]=ch[ch.length-1-i];
//         ch[ch.length-1-i]=tem;
//     }

//     int st=-1;
//     int ed=0;
//     for(int i=0;i<ch.length;i++){
//         if(ch[i]!=' ' && st==-1){
//             st=i;
//         }
//         if(ch[i]==' ' && st!=-1){
//             ed=i;
//             for(st=st;st<ed-1;st++,ed--){
//                 char tem=ch[st];
//                 ch[st]=ch[ed-1];
//                 ch[ed-1]=tem;
//             }
//             st=-1;
//         }
//     }
//     if(st !=-1){
//        ed=ch.length;
//        for(st=st;st<ed-1;st++,ed--){
//             char tem=ch[st];
//             ch[st]=ch[ed-1];
//             ch[ed-1]=tem;
//         }
//     }

//     return new String(ch);


//方法五 用stringbuffer来做 用s.trim（）和string buffer去掉头尾 和 中间的空格
//         s=s.trim();
//         StringBuffer sb= new StringBuffer(s);
//         for(int i=0;i<sb.length();i++){
//             if(sb.charAt(i)==' ' && sb.charAt(i-1)==' '){
//                 sb.deleteCharAt(i);
//                 i--;    //删掉之后后面的就往前移了一位，所以我还留在这个位置上面
//             }
//         }

//         //再把StringBuffer变成char【】
//         //在Java中不支持直接从StringBuffer转换成字符数组。而是先将StringBuffer转换成String，然后由String调用toCharArray函数转换成字符数组。
//         s=sb.toString();
//         char[] ch=s.toCharArray();

//         for(int i=0; i<ch.length-1-i; i++){
//             char tem=ch[i];
//             ch[i]=ch[ch.length-1-i];
//             ch[ch.length-1-i]=tem;
//         }

//         int st=-1;
//         int ed=0;
//         for(int i=0;i<ch.length;i++){
//             if(ch[i]!=' ' && st==-1){
//                 st=i;
//             }
//             if(ch[i]==' ' && st!=-1){
//                 ed=i;
//                 for(st=st;st<ed-1;st++,ed--){
//                     char tem=ch[st];
//                     ch[st]=ch[ed-1];
//                     ch[ed-1]=tem;
//                 }
//                 st=-1;
//             }
//         }
//         if(st !=-1){
//            ed=ch.length;
//            for(st=st;st<ed-1;st++,ed--){
//                 char tem=ch[st];
//                 ch[st]=ch[ed-1];
//                 ch[ed-1]=tem;
//             }
//         }

//         return new String(ch);


    }




    public static void main(String[] args){
        ReverseWordsInAString rs=new ReverseWordsInAString();
        System.out.println(rs.ReverseWords(" i am dsa ew  "));

        String s = "sdewew";
        char[] ch = s.toCharArray();
        System.out.println(ch[0]);
        System.out.println(ch.length);
        for(int i=0; i<ch.length-1-i; i++){
            char tem=ch[i];
            ch[i]=ch[ch.length-1-i];
            ch[ch.length-1-i]=tem;
        }
        System.out.println(ch);

    }

}


