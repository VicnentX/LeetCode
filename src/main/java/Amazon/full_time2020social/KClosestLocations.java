package Amazon.full_time2020social;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 假设起始位置是0，0 找到最近的K个点
 * the time complexity is O(nlogk)
 * the space complexity is O(k)
 */

public class KClosestLocations {
    public List<int[]> getClosestPoints(int numDestinations, int[][] allLocations, int numDeliveries) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        List<int[]> ret = new ArrayList<>();
        //fill the pq
        //O(nlogk)
        for (int[] location: allLocations) {
            pq.add(location);
            if (pq.size() > numDeliveries) {
                pq.poll();
            }
        }
        //get the result
        //O(k)
        while(!pq.isEmpty()) {
            ret.add(pq.poll());
        }

        return ret;
    }

    /**
     * III. The last solution is based on quick sort, we can also call it quick select. In the quick sort, we will always choose a pivot to compare with other elements. After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order). So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be. Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa. If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot.
     *
     * Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would be degenerated to O(N^2), and pratically, the real time it takes on leetcode is 15ms.
     *
     * The advantage of this solution is it is very efficient.
     * The disadvatage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.
     *
     * The short code shows as follows:
     * @return
     */

    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }




    public static void main(String[] args) {
        KClosestLocations kClosestLocations = new KClosestLocations();
        for (int[] pair: kClosestLocations.getClosestPoints(3, new int[][] {{1,2},{3,4},{1,-1}}, 2)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
