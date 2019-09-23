package DRW;

public class FindParseAnyOne {
    // write your code in C++14 (g++ 6.2.0)
    public int findParse(int N) {
        for (int i = 0; i <= N / 2; ++i) {
            int j = N - i;
            if (isParse(i) && isParse(j)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isParse(int n) {
        return (n & (n>>1)) == 0;
    }

    public static void main(String[] args) {
        FindParseAnyOne fdsf = new FindParseAnyOne();
        System.out.println(fdsf.findParse((int)Math.pow(10, 9)));
    }
}
