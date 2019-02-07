package google;

/*
input是2个string，这两个string长度相等，
比如"2a3b1c" 和 "1a2b3d"他们长度是一样的，
他们extend出来长度也相等 "2a3b1c"  -> "aabbbc", "1a2b3c" -> "abbccc", extend之后长度也相等
找出两个extend之后的不同的字母的个数，"aabbbc" 和 "abbccc" 一位一位的比较，有3个字母是不同的，要return 3
 */

import java.util.ArrayList;
import java.util.List;

public class PrintDifferencewithTwoString {
    public int findDifference(String s1 , String s2) {
        List<String[]> a = new ArrayList<>();
        List<String[]> b = new ArrayList<>();
        int n = s1.length();
        //fill a and b
        int i = 0;
        while (i < n) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(s1.charAt(i))) {
                num.append(s1.charAt(i));
                ++i;
            }
            a.add(new String[] {num.toString() , String.valueOf(s1.charAt(i))});
            ++i;
        }
        i = 0;
        while (i < n) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(s2.charAt(i))) {
                num.append(s2.charAt(i));
                ++i;
            }
            b.add(new String[] {num.toString() , String.valueOf(s2.charAt(i))});
            ++i;
        }
        //calculate the difference

        i = 0;
        int j = 0;
        int ret = 0;
        while (i < a.size() && j < b.size()) {
            int numA = Integer.valueOf(a.get(i)[0]);
            int numB = Integer.valueOf(b.get(j)[0]);
            String letterA = a.get(i)[1];
            String letterB = b.get(j)[1];
            int cover = Math.abs(Math.min(numA , numB));
            boolean same = letterA.equals(letterB);
            if (numA > numB) {
                a.get(i)[0] = String.valueOf(numA - numB);
                ret += same ? 0 : cover;
                ++j;
            } else if (numA < numB) {
                b.get(j)[0] = String.valueOf(numB - numA);
                ret += same ? 0 : cover;
                ++i;
            } else {
                ret += same ? 0 : cover;
                ++i;
                ++j;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        PrintDifferencewithTwoString pd = new PrintDifferencewithTwoString();
        System.out.println(pd.findDifference("1a2b3c" , "2a3b1c"));
        System.out.println(pd.findDifference("13a15b" , "15a13b"));
    }
}
