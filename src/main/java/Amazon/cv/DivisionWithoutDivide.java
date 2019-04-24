package Amazon.cv;

/*
b / a; only keep integer part
 */

public class DivisionWithoutDivide {
    public int DWD (int a , int b) {
        int start = 0;
        int end = b;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid * a <= b) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * a <= b) return end;
        return start;
    }


    public static void main (String[] args) {
        DivisionWithoutDivide dw = new DivisionWithoutDivide();
        System.out.println(dw.DWD(3,10));
        System.out.println(dw.DWD(10,3));
    }
}
