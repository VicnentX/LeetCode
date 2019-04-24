package Amazon.cv;

/*
find all prime which is smaller than 1000
 */

public class findPrime {
    public void findAllPrimeSmallerThanN (int n) {
        OUT:
        for (int i = 0 ; i < n ; ++i) {
            for (int a = 2 ; a < Math.sqrt(i) ; ++a) {
                if (gcd(a , i) != 1) {
                    continue OUT;
                }
            }
            System.out.print(i + " ");
        }
    }

    private int gcd (int a , int b) {
        if (b == 0) return a;
        return gcd(b , a % b);
    }

    public static void main (String[] args) {
        findPrime fp = new findPrime();
        fp.findAllPrimeSmallerThanN(1000);
    }
}
