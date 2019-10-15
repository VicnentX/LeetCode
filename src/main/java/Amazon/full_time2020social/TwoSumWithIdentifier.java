package Amazon.full_time2020social;
/*
/**
 * Amazon Prime Air is developing a system that
 * divides shipping routes using flight optimization routing systems
 * to a cluster of aircraft that can fulfill these routes.
 *
 * Each shipping route is identified by a unique integer identifier,
 * requires a fixed non-zero amount of travel distance between airports,
 * and is defined to be either a forward shipping route or a return shipping route.
 * Identifiers are guaranteed to be unique within their own route type, but not across route types.
 *
 * Each aircraft should be assigned two shipping routes at once: one forward route and one return route.
 * Due to the complex scheduling of flight plans,
 * all aircraft have a fixed maximum operating travel distance,
 * and cannot be scheduled to fly a shipping route that
 * requires more travel distance than the prescribed maximum operating travel distance.
 *
 * The goal of the system is to optimize the total operating travel distance of a given aircraft.
 * A forward/return shipping route pair is considered to be “optimal”
 * if there does not exist another pair that has a higher operating travel distance than this pair,
 *
 * and also has a total less than or equal to the maximum operating travel distance of the aircraft.
 *
 * For example,
 *
 * if the aircraft has a maximum operating travel distance of 3000 miles,
 * a forward/return shipping route pair using a total of 2900 miles would be optimal
 * if there does not exist a pair that uses a total operating travel distance of 3000 miles,
 * but would not be considered optimal if such a pair did exist.
 * Your task is to write an algorithm to
 * optimize the sets of forward/return shipping route pairs
 * that allow the aircraft to be optimally utilized,
 * given a list of forward shipping routes and a list of return shipping routes.
 *
 * Input
 * The input to the function/method consists of three arguments:
 *
 * maxTravelDist:
 * an integer representing the maximum operating travel distance of the given aircraft
 *
 * forwardRouteList:
 * a list of pairs of integers where the first integer represents the unique identifier of a forward shipping route
 * and the second integer represents the amount of travel distance required by this shipping route
 *
 * returnRouteList:
 * a list of pairs of integers where the first integer represents the unique identifier of a return shipping route
 * and the second integer represents the amount of travel distance required by this shipping route.
 *
 * Output
 * Return a list of pairs of integers representing
 * the pairs of IDs of forward and return shipping routes that optimally utilize the given aircraft.
 * If no route is possible, return an empty list.
 */


/**
 * 找到小于等于target 但是最接近的pair 有多个就输出多个
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoSumWithIdentifier {
    public List<int[]> listAllClosestpairs(int maxTravelDist, List<int[]> forwardRouteList, List<int[]> returnRouteList) {

        //sort two list first , by distance first then by identifier
        //O(max(mlogm , nlogn))
        ArrayList<int[]> ret = new ArrayList<>();
        Collections.sort(forwardRouteList, (a,b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        Collections.sort(returnRouteList, (a,b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        final int M = forwardRouteList.size();
        final int N = returnRouteList.size();
        int i = 0;
        int j = N - 1;
        int optMaxDist = Integer.MIN_VALUE;

        //loop to get optMaxDist
        //first loop to find the optTarget
        //O(m + n)
        while (i < M && j >= 0) {
            int val1 = forwardRouteList.get(i)[1];
            int val2 = returnRouteList.get(j)[1];
            if (val1 + val2 > maxTravelDist) {
                j--;
            } else {
                if (val1 + val2 > optMaxDist) {
                    optMaxDist = val1 + val2;
                }
                i++;
            }
        }

        //now we know that the optMaxDist is the sum we want to find
        //O(m + n)
        i = 0;
        j = N - 1;
        while (i < M && j >= 0) {
            int val1 = forwardRouteList.get(i)[1];
            int val2 = returnRouteList.get(j)[1];
            if (val1 + val2 > optMaxDist) {
                j--;
            } else if (val1 + val2 == optMaxDist) {
                ret.add(new int[] {forwardRouteList.get(i)[0], returnRouteList.get(j)[0]});
                i++;
                j--;
            } else {
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TwoSumWithIdentifier twoSumWithIdentifier = new TwoSumWithIdentifier();
        List<int[]> forward = new ArrayList<>();
        List<int[]> returnL = new ArrayList<>();

        forward.add(new int[] {1, 3000});
        forward.add(new int[] {3, 7000});
        forward.add(new int[] {2, 5000});
        forward.add(new int[] {4, 10000});

        returnL.add(new int[] {1, 2000});
        returnL.add(new int[] {3, 4000});
        returnL.add(new int[] {2, 3000});
        returnL.add(new int[] {4, 5000});

        for (int[] pair: twoSumWithIdentifier.listAllClosestpairs(10000, forward, returnL)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        forward.clear();
        returnL.clear();
        System.out.println("=========================");

        //Output: [[2, 4], [3, 2]]
        forward.add(new int[] {1, 8000});
        forward.add(new int[] {3, 14000});
        forward.add(new int[] {2, 7000});

        returnL.add(new int[] {1, 5000});
        returnL.add(new int[] {2, 10000});
        returnL.add(new int[] {3, 14000});

        for (int[] pair: twoSumWithIdentifier.listAllClosestpairs(20000, forward, returnL)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        forward.clear();
        returnL.clear();
        System.out.println("=========================");

        //Output: [[1, 3], [3, 2]]
        forward.add(new int[] {1, 8000});
        forward.add(new int[] {3, 9000});
        forward.add(new int[] {2, 15000});

        returnL.add(new int[] {1, 8000});
        returnL.add(new int[] {2, 11000});
        returnL.add(new int[] {3, 12000});

        for (int[] pair: twoSumWithIdentifier.listAllClosestpairs(20000, forward, returnL)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        forward.clear();
        returnL.clear();
        System.out.println("=========================");

    }
}
