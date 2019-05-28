package leetcode.Number;

/*
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < N and N % x == 0.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.



Example 1:

Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
Example 2:

Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.


Note:

1 <= N <= 1000
Seen this question in a real interview before?

 */

public class DivisorGame1025 {
    public boolean divisorGame(int n) {
        int round = 0;
        while (n > 0) {
            for (int i = n - 1; i > 1 ; --i) {
                if (n % i == 0 && isPrime(n - i)) {
                    return round % 2 == 0;
                }
            }
            n--;
            round++;
        }
        return round % 2 == 0;
    }
    private boolean isPrime (int num) {
        boolean isComposite = false;
        for (int a = 2; a <= Math.sqrt(num); ++a) {
            if (gcd(a, num) != 1) {
                isComposite = true;
                break;
            }
        }
        return !isComposite;
    }
    private long gcd(long a , long b) {
        if (b == 0) return a;
        return gcd( b ,a % b);
    }
}
