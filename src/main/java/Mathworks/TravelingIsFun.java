package Mathworks;

/**
 * 给四个参数
 * n - number of cities
 * g - cities x and y are connected if and only if gcd(x,y) > g
 * originCities - each index i (where 0 <= i <= q) describes x for the ith pair of cities
 * destinationCities - each index i (where 0 <= i <= q) describes y for the ith pair of cities
 */

public class TravelingIsFun {
    public int[] checkConnectionofPairs(int n, int g, int[] originCities, int[] destinationCities) {
        if (originCities.length != destinationCities.length || n <= 0) {
            return new int[n];
        }

        int[] ret = new int[originCities.length];
        //initialize the roots arrays
        int[] roots = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            roots[i] = i;
        }
        //unionFind
        for (int i = g + 1; i <= n; ++i) {
            for (int j = 2 * i; j <= n; j = j + i) {
                    int root1 = findRoot(roots, i);
                    int root2 = findRoot(roots, j);
                    if (root1 != root2) {
//                        roots[root2] = root1;
                        roots[root1] = root2;
                    }
            }
        }
        //check if connected
        for (int i = 0 ; i < ret.length; ++i) {
            if (findRoot(roots, originCities[i]) == findRoot(roots, destinationCities[i])) {
                ret[i] = 1;
            } else {
                ret[i] = 0;
            }
        }

        return ret;
    }

    private int findRoot(int[] roots, int id) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots , roots[id]);
        return roots[id];
    }

    private int findGCD(int a, int b) {
        //base case
        if (b == 0)
            return a;
        return findGCD(b, a % b);
    }

    public static void main(String[] args) {
        TravelingIsFun travelingIsFun = new TravelingIsFun();
        int[] ret = travelingIsFun.checkConnectionofPairs(6,0, new int[] {1,4,3,6}, new int[] {3,6,2,5});
        for (int i: ret) {
            System.out.println(i);
        }
        ret = travelingIsFun.checkConnectionofPairs(6,1, new int[] {1,2,4,6}, new int[] {3,3,3,4});
        for (int i: ret) {
            System.out.println(i);
        }
    }
}
