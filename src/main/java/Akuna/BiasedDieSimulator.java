package Akuna;

/**
 * calcualte all permutation
 * rollMax = [1,2,1,2,1,2] mean it cannot roll the number i more than rollMax[i] times consecutively
 */

public class BiasedDieSimulator {

    private int cnt = 0;

    public int findAllPermutation(int n, int[] rollMax) {
        dfs(n , 0, 0, rollMax);
        return cnt;
    }

    private void dfs(int n, int lastNum, int repeatedOfLastNum, int[] rollMax) {
        if (n == 0) {
            cnt++;
            return;
        }

        for (int i = 0; i < 6; ++i) {
            if (i == lastNum) {
                if (repeatedOfLastNum < rollMax[i]) {
                    dfs(n - 1, lastNum, repeatedOfLastNum + 1, rollMax);
                }
            } else {
                dfs(n - 1, i, 1, rollMax);
            }
        }
    }

    public static void main(String[] args) {
        BiasedDieSimulator biasedDieSimulator = new BiasedDieSimulator();
        System.out.println(biasedDieSimulator.findAllPermutation(2, new int[] {1,2,1,2,1,2}));
    }
}
