package google.google2020.google_加油少年_VO;

/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.



Example 1:



Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation:
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
Example 2:



Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation:
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].


Note:

0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000
 */

import java.util.*;

/**
 * the question states, there are n workers and m bikes, and m > n.
 * We are able to solve this question using a greedy approach.
 *
 * initiate a priority queue of bike and worker pairs. The heap order should be Distance ASC, WorkerIndex ASC, Bike ASC
 * Loop through all workers and bikes, calculate their distance, and then throw it to the queue.
 * Initiate a set to keep track of the bikes that have been assigned.
 * initiate a result array and fill it with -1. (unassigned)
 * poll every possible pair from the priority queue and check if the person already got his bike or the bike has been assigned.
 * early exist on every people got their bike.
 */



public class CampusBikes1057 {


    /**
     * Time complexity might be O(M*N*log(M*N))
     */

    public int[] assignBikes(int[][] workers, int[][] bikes) {
//        int n = workers.length;
//
//        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
//        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0]);
//        // loop through every possible pairs of bikes and people,
//        // calculate their distance, and then throw it to the pq.
//        for (int i = 0; i < workers.length; ++i) {
//            int[] curWorker = workers[i];
//            for (int j = 0; j < bikes.length; ++j) {
//                int[] curBike = bikes[j];
//                int dis = Math.abs(curBike[0] - curWorker[0]) + Math.abs(curBike[1] - curWorker[1]);
//                q.add(new int[] {dis, i, j});
//            }
//        }
//        // init the result array with state of 'unvisited'.
//        int[] ret = new int[n];
//        Arrays.fill(ret, -1);
//
//        //get result
//        Set<Integer> bikeAssigned = new HashSet<>();
//        while (bikeAssigned.size() < n) {
//            int[] pair = q.poll();
//            if (ret[pair[1]] == -1 && !bikeAssigned.contains(pair[2])) {
//                ret[pair[1]] = pair[2];
//                bikeAssigned.add(pair[2]);
//            }
//        }
//
//        return ret;

        final int N = workers.length;
        int[] ret = new int[N];
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a,b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0]);
        for (int i = 0 ; i < workers.length; ++i) {
            int[] curWorker = workers[i];
            for (int j = 0; j < bikes.length; ++j) {
                int[] curBike = bikes[j];
                int dis = Math.abs(curBike[0] - curWorker[0]) + Math.abs(curBike[1] - curWorker[1]);
                q.add(new int[] {dis, i, j});
            }
        }
        Arrays.fill(ret, -1);
        Set<Integer> bikeAssinged = new HashSet<>();
        while (bikeAssinged.size() < N) {
            int[] cur = q.poll();
            if (ret[cur[1]] == -1 && !bikeAssinged.contains(cur[2])) {
                ret[cur[1]] = cur[2];
                bikeAssinged.add(cur[2]);
            }
        }
        return ret;
    }


    //bucket sort

    /**
     * Then I noticed that 0 <= workers[i][j], bikes[i][j] < 1000.
     * So that means Manhattan distance
     * between any worker and bike is between 0 and 2000.
     * So we could use the following counting sort solution with time
     * complexity of O(M*N)
     * @param workers
     * @param bikes
     * @return
     */
    public int[] assignBikesBucketSort(int[][] workers, int[][] bikes) {
        // Notice that the Manhattan distance is between 0 and 2000,
        // which means we can sort easily without even using priority queue
        final int M = workers.length;
        final int N = bikes.length;
        List<int[]>[] distacnce = new ArrayList[2001];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                int[] curWorker = workers[i];
                int[] curBike = bikes[j];
                int dis = Math.abs(curBike[0] - curWorker[0]) + Math.abs(curBike[1] - curWorker[1]);
                if (distacnce[dis] == null) {
                    distacnce[dis] = new ArrayList<>();
                }
                distacnce[dis].add(new int[] {i, j});
            }
        }

        int assigned = 0;
        int[] ret = new int[M];
        Set<Integer> assignedWorker = new HashSet<>();
        Set<Integer> assignedBike = new HashSet<>();
        for (int i = 0; i <= 2000 && assigned < M; ++i) {
            if (distacnce[i] == null) continue;
            for (int[] pair: distacnce[i]) {
                if (!assignedWorker.contains(pair[0]) && !assignedBike.contains(pair[1])) {
                    ret[pair[0]] = pair[1];
                    assignedWorker.add(pair[0]);
                    assignedBike.add(pair[1]);
                }
            }
        }
        return ret;
    }
}
