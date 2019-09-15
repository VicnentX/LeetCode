package Amazon.full_time2020;

import java.util.*;

/**
 * Amazon Prime Air is developing a system that divides shipping routes using flight optimization routing systems to a cluster of aircraft that can fulfill these routes. Each shipping route is identified by a unique integer identifier, requires a fixed non-zero amount of travel distance between airports, and is defined to be either a forward shipping route or a return shipping route. Identifiers are guaranteed to be unique within their own route type, but not across route types.
 * Each aircraft should be assigned two shipping routes at once: one forward route and one return route. Due to the complex scheduling of flight plans, all aircraft have a fixed maximum operating travel distance, and cannot be scheduled to fly a shipping route that requires more travel distance than the prescribed maximum operating travel distance. The goal of the system is to optimize the total operating travel distance of a given aircraft. A forward/return shipping route pair is considered to be “optimal” if there does not exist another pair that has a higher operating travel distance than this pair, and also has a total less than or equal to the maximum operating travel distance of the aircraft.
 * For example, if the aircraft has a maximum operating travel distance of 3000 miles, a forward/return shipping route pair using a total of 2900 miles would be optimal if there does not exist a pair that uses a total operating travel distance of 3000 miles, but would not be considered optimal if such a pair did exist.
 * Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow the aircraft to be optimally utilized, given a list of forward shipping routes and a list of return shipping routes.
 * Input
 * The input to the function/method consists of three arguments: maxTravelDist: an integer representing the maximum operating travel distance of the given aircraft forwardRouteList: a list of pairs of integers where the first integer represents the unique identifier of a forward shipping route and the second integer represents the amount of travel distance required by this shipping route returnRouteList: a list of pairs of integers where the first integer represents the unique identifier of a return shipping route and the second integer represents the amount of travel distance required by this shipping route.
 * Output
 * Return a list of pairs of integers representing the pairs of IDs of forward and return shipping routes that optimally utilize the given aircraft. If no route is possible, return an empty list.
 */

public class TravelLimit2 {
    public List<List<Integer>> maxTravelLimit(List<List<Integer>> departs, List<List<Integer>> returns, int limit) {
        //build two list and two map
        //list store the distance of  shipping routes(unique)
        //map stores pairs of distance and list of ids
        List<Integer> fowardDistancesList = new ArrayList<>();
        List<Integer> returnDistancesList = new ArrayList<>();
        Map<Integer, List<Integer>> forwardMap = new HashMap<>();
        Map<Integer, List<Integer>> returnMap = new HashMap<>();

        fillListAndMap(departs, fowardDistancesList, forwardMap);
        fillListAndMap(returns, returnDistancesList, returnMap);

        Collections.sort(fowardDistancesList);
        Collections.sort(returnDistancesList);

        int i = 0, j = returnDistancesList.size() - 1, maxDis = 0;
        List<List<Integer>> ret = new ArrayList<>();

        while (i < fowardDistancesList.size() && j >= 0) {
            int dis1 = fowardDistancesList.get(i);
            int dis2 = returnDistancesList.get(j);
            int totalDis = dis1 + dis2;

            if (totalDis <= limit) {
                if (totalDis > maxDis) {
                    ret = buildNewRet(new ArrayList<>(), forwardMap.get(dis1), returnMap.get(dis2));
                    maxDis = totalDis;
                } else if (totalDis == maxDis) {
                    ret = buildNewRet(ret, forwardMap.get(dis1), returnMap.get(dis2));
                }
                ++i;
            } else {
                --j;
            }
        }

        return ret;
    }

    private List<List<Integer>> buildNewRet(List<List<Integer>> ret, List<Integer> forwardList, List<Integer> returnList) {
        for (int id1: forwardList) {
            for (int id2: returnList) {
                ret.add(Arrays.asList(id1, id2));
            }
        }
        return ret;
    }

    private void fillListAndMap(List<List<Integer>> departs, List<Integer> distanceList, Map<Integer, List<Integer>> map) {
        for (List<Integer> depart: departs) {
            int id = depart.get(0);
            int distance = depart.get(1);
            if (!map.containsKey(distance)) {
                map.put(distance, new ArrayList<>());
                distanceList.add(distance);
            }
            map.get(distance).add(id);
        }
    }

    public static void main(String[] args) {
        TravelLimit2 travelLimit2 = new TravelLimit2();

        List<List<Integer>> ret = travelLimit2.maxTravelLimit(Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,4),
                Arrays.asList(3,2)
                ),
                Arrays.asList(
                        Arrays.asList(2,2),
                        Arrays.asList(3,3)
                ),
                5);

        for(List<Integer> list: ret) {
            System.out.print(list.get(0) + "   " + list.get(1));
            System.out.println();
        }
    }
}
