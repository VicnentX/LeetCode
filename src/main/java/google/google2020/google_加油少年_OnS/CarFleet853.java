package google.google2020.google_加油少年_OnS;

/*
N cars are going to the same destination along a one lane road.  The destination is target miles away.

Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored - they are assumed to have the same position.

A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.


How many car fleets will arrive at the destination?



Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:

0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.
 */

import java.util.Arrays;

public class CarFleet853 {
    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || speed == null || position.length != speed.length || position.length == 0) return 0;
        final int N = position.length;
        double[][] carInfo = new double[N][2];
        for (int i = 0; i < N; ++i) {
            carInfo[i] = new double[] {position[i], (target - position[i]) * 1.0 / speed[i]};
        }
        Arrays.sort(carInfo, (a,b) -> Double.compare(b[0], a[0]));
        double ahead = carInfo[0][1];
        int fleet = 1;
        for (double[] car: carInfo) {
            if (car[1] > ahead) {
                fleet++;
                ahead = car[1];
            }

        }
        return fleet;
    }

    public static void main(String[] args) {

    }
}
