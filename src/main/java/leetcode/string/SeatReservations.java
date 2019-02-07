package leetcode.string;


import java.util.*;
public class SeatReservations {
    public int FamilyReversation (int n , String input) {
        if (n == 0 ) return 0;
        if (input.length() == 0 || input.length() == 1) return 3 * n;
        double[][] ret = new double[n][3];
        String[] tokens = input.split(" ");
        for (String s : tokens) {
            int i = 0;
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(s.charAt(i))) {
                num.append(s.charAt(i));
                ++i;
            }
            int index = Integer.valueOf(num.toString()) - 1;
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'C') {
                ret[index][0] = 1;
            } else if (c == 'D') {
                ret[index][1] = ret[index][1] == 1 ? 1 : ret[index][1] + 0.5;
            } else if (c == 'E' || c == 'F') {
                ret[index][1] = 1;
            } else if (c == 'G') {
                ret[index][1] = ret[index][1] == 1 ? 1 : ret[index][1] + 0.5;
            } else {
                ret[index][2] = 1;
            }
        }
        int sum = 0;
        for (double[] k : ret) {
            for(double kk : k) {
                sum += (int)kk;
            }
        }
        return 3 * n - sum;
    }

    public static void main (String[] args) {
        SeatReservations sr = new SeatReservations();
        System.out.println(sr.FamilyReversation(2 , "1A 2F 1C"));
        System.out.println(sr.FamilyReversation(1 , ""));
        //“1A 3C 2B 40G 5A”
        System.out.println(sr.FamilyReversation(41 , "1A 3C 2B 40G 5A"));
    }
}
