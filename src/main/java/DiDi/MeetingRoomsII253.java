package DiDi;

/*
Given an array of meeting time intervals
consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019.
Please reset to default code definition to get new method signature.
 */

import java.util.Arrays;

public class MeetingRoomsII253 {
    public int minMeetingRooms(int[][] intervals) {
        final int M = intervals.length;
        int[][] pair = new int[M * 2][2];
        //fill the pair, start time : 1 or end time : -1
        int i = 0;
        for (int[] interval: intervals) {
            pair[i][0] = interval[0];
            pair[i++][1] = 1;
            pair[i][0] = interval[1];
            pair[i++][1] = -1;
        }

        Arrays.sort(pair, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int roomCnt = 0;
        int curRoom = 0;
        for (int[] room: pair) {
            curRoom += room[1];
            roomCnt = Math.max(roomCnt, curRoom);
        }
        return roomCnt;
    }
}
