package leetcode.mathematical;

/*
Given n orders, each order consist in pickup and delivery services.

Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
Example 2:

Input: n = 2
Output: 6
Explanation: All possible orders:
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
Example 3:

Input: n = 3
Output: 90

Constraints:

1 <= n <= 500
 */

public class CountAllValidPickupandDeliveryOptions1359 {
    /*
    Intuition 1
Assume we have already n - 1 pairs, now we need to insert the nth pair.
To insert the first element, there are n * 2 - 1 chioces of position。
To insert the second element, there are n * 2 chioces of position。
So there are (n * 2 - 1) * n * 2 permutations.
Considering that delivery(i) is always after of pickup(i), we need to divide 2.
So it's (n * 2 - 1) * n.
    */
    public int countOrders(int n) {
        int mod = 1000000007;
        long ret = 1;
        for (int i = 1; i <= n; ++i) {
            ret = ret * i * (i * 2 - 1) % mod;
        }
        return (int) ret;
    }
}
