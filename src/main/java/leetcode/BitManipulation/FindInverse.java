package leetcode.BitManipulation;

/*
50 = 110010
so
110010 -> 001101 = 13

n <= 100000
 */

public class FindInverse {
    public int InverseInteger (int n) {
        int len = (int)Math.ceil(Math.log(n + 1) / Math.log(2));
        int allone = 1;
        for (int i = 1 ; i < len ; ++i) {
            allone = (allone << 1) | 1;
        }
        return allone ^ n;
    }
    public static void main (String[] args) {
        FindInverse fi = new FindInverse();
        System.out.println(fi.InverseInteger(50));
        System.out.println(fi.InverseInteger(0));
        System.out.println(fi.InverseInteger(1));
        System.out.println(fi.InverseInteger(13));
    }
}
