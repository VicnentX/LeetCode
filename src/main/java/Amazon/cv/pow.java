package Amazon.cv;

/*
a^n
 */

public class pow {
    public double apown (int a , int n) {
        if (n == Integer.MIN_VALUE) {
            return 1/a * apown(a , n + 1);
        }
        if (n == 0) return 1;
        if (n < 0) {
            a = 1/a;
            n = -n;
        }
        return n % 2 == 0 ? apown(a * a , n / 2) : a * apown(a * a , n / 2);
    }

    public static void main (String[] args) {
        pow po = new pow();
        System.out.println(po.apown(3,4));
    }
}
