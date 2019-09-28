package intuit.Intuit2020;

import java.util.*;


/**
 * clarify this quesiton:
 *      the input pattern / format
 *      is all the input is valid 0 - 2359, ex: [-100, 250] ?
 *      this quesiont means we only have one meeting room right?
 */
/*
第一题：输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 ->1300， 9：-> 930， 看新的meeting 能不能安排到meetings
ex: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;
 */

public class MeetingScheduleQuestionSet2 {
    public boolean canAddNewMeeting(int[][] existingMeeting, int[] newMeeting) {
        int n = existingMeeting.length;
        final int TIME_POINT = n + 1;
        int[][] allMeetingPoint = new int[TIME_POINT * 2][2];
        int roomNeeded = 0;
        int curRoomUsed = 0;

        //fill allMeetingPoint
        //make as 1 when it is start, and -1 when it is end
        int index = 0;
        for (int[] interval: existingMeeting) {
            allMeetingPoint[index][0] = interval[0];
            allMeetingPoint[index++][1] = 1;
            allMeetingPoint[index][0] = interval[1];
            allMeetingPoint[index++][1] = -1;
        }
        allMeetingPoint[index][0] = newMeeting[0];
        allMeetingPoint[index++][1] = 1;
        allMeetingPoint[index][0] = newMeeting[1];
        allMeetingPoint[index][1] = -1;

        //same time point, close first then open meeting room
        Arrays.sort(allMeetingPoint, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int[] timePoint: allMeetingPoint) {
            if (timePoint[1] == 1) {
                ++curRoomUsed;
                roomNeeded = Math.max(roomNeeded, curRoomUsed);
            } else {
                --curRoomUsed;
            }
        }

        return roomNeeded == 1;
    }

/**
 * clarify quesitons
 *      p means person ?
 *      so I need to get all the timeslot where no one has meeting , right?
 *
 */

/*
"Follow up:
We are writing a tool to help users manage their calendars. Given an unordered list of times of day when people are busy, write a function that tells us the intervals during the day when ALL of them are available.

Sample input:

p1_meetings = [
  {"start": 1230, "end": 1300},
  {"start":  845, "end":  900},
  {"start": 1300, "end": 1500},
]
p2_meetings = [
  {"start":  930, "end": 1200},
  {"start": 1600, "end": 2359},
]
p3_meetings = [
  {"start":  845, "end":  915},
  {"start": 1515, "end": 1545},
]
schedules = [p1_meetings, p2_meetings, p3_meetings]

Expected output:

find_available_time(schedules)
=> [    0,  845 ],
    [  915,  930 ],
    [ 1200, 1230 ],
    [ 1500, 1515 ],
    [ 1545, 1600 ]
    /*解法:与LC的尔雾散解法类似：两个vector，一个放所有的开始时间，一个放所有的结束时间。都进行排序。然后就会发现神奇的发现：
    {845，845，930，1230，1300，1515，1600}
    {900，915，1200，1300，1500，1545，2359}
    进行一轮遍历就能得到他们的meeting时间并集
    {
        {845,915} {930,1200} {1230,1500}{1515,1545}{1600,2359}
    }那么答案：空闲时间就是
    {
        {0,845},{915,930},{1200,1230},{1500,1515},{1545,1600}
        "
 */

    public List<int[]> findAllAvailableTimeslots(List<List<int[]>> existingMeeting) {
        List<int[]> ret = new ArrayList<>();
        int meetingCnt = 0;

        for (List<int[]> personMeeting: existingMeeting) {
            meetingCnt += personMeeting.size();
        }
        int[][] allMeetingPoint = new int[meetingCnt * 2][2];
        int curRoomUsed = 0;

        //fill allMeetingPoint
        //make as 1 when it is start, and -1 when it is end
        int index = 0;
        for (List<int[]> personMeeting: existingMeeting) {
            for (int[] meeting: personMeeting) {
                allMeetingPoint[index][0] = meeting[0];
                allMeetingPoint[index++][1] = 1;
                allMeetingPoint[index][0] = meeting[1];
                allMeetingPoint[index++][1] = -1;
            }
        }

        //same time point, close first then open meeting room
        Arrays.sort(allMeetingPoint, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);


        //find all available timeslots
        int timeslotStart = 0;
        for (int[] timePoint: allMeetingPoint) {
            if (curRoomUsed == 0) {
                if (timeslotStart < timePoint[0]) {
                    ret.add(new int[] {timeslotStart, timePoint[0]});
                }
            }

            curRoomUsed += timePoint[1];

            if (curRoomUsed == 0) {
                timeslotStart = timePoint[0];
            }
        }
        //conner case
        if (timeslotStart < 2359) {
            ret.add(new int[] {timeslotStart, 2359});
        }

        return ret;
    }


    /**
     * clarify the question:
     *      every room could be used as many times as I can? not the same time.
     */
/*
第三题是给会议分配房间。已知每个会议的mingzi , 人数、开始时间、结束时间，以及每个房间的容量。
输入：
会议列表：每个会议有名称、人数、开始时间、结束时间
房间列表：每个房间有名称、容量。
输出：
每个会议安排在哪个房间，格式是“会议名:房间名”
如果没法都安排，输出"impossible
 */
    private static class Meeting {
        private String meetingName;
        private int personCnt;
        private int startTime;
        private int endTime;

        Meeting(String meetingName, int personCnt, int startTime, int endTime) {
            this.meetingName = meetingName;
            this.personCnt = personCnt;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    private static class Room {
        private String roomName;
        private int cap;

        Room(String roomName, int cap) {
            this.roomName = roomName;
            this.cap = cap;
        }
    }

    public void printMeetingRoomPair(List<Meeting> meetings, List<Room> rooms) {
        Collections.sort(meetings, (a,b) -> a.startTime == b.startTime ? a.endTime - b.endTime : a.startTime - b.startTime);
        TreeSet<Room> avaliableRooms = new TreeSet<Room>((a,b) -> a.cap - b.cap);
        avaliableRooms.addAll(rooms);
        TreeMap<Integer, Room> usedNow = new TreeMap<>();
        //store the possible result "meetName:roomName"
        List<String> ret = new ArrayList<>();

        for (Meeting meeting: meetings) {
            int timeNow = meeting.startTime;

            //put room back if possible
            for (int timeEnd: usedNow.keySet()) {
                if (timeEnd <= timeNow) {
                    avaliableRooms.add(usedNow.remove(timeEnd));
                } else {
                    break;
                }
            }
            //find the room (ceiling is binary search)
            Room room = avaliableRooms.ceiling(new Room("", meeting.personCnt));
            if (room == null) {
                System.out.println("impossible");
                return;
            }
            usedNow.put(meeting.endTime, room);
            avaliableRooms.remove(room);
            ret.add(meeting.meetingName + " : " + room.roomName);
        }

        //canArrangeRoom so print result
        for (String s: ret) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        MeetingScheduleQuestionSet2 meetingScheduleQuestionSet = new MeetingScheduleQuestionSet2();

        //_____________________1______________________
        //true
        System.out.println(meetingScheduleQuestionSet.canAddNewMeeting(
                new int[][]{{1300, 1500}, {930, 1200}, {830, 845}}, new int[]{820, 830}
        ));
        //false
        System.out.println(meetingScheduleQuestionSet.canAddNewMeeting(
                new int[][]{{1300, 1500}, {930, 1200}, {830, 845}}, new int[]{1450, 1500}
        ));

        //_____________________2______________________
        //
        List<List<int[]>> input = new ArrayList<>();
        List<int[]> input1 = new ArrayList<>();
        input1.add(new int[] {1230, 1300});
        input1.add(new int[] {845, 900});
        input1.add(new int[] {1300, 1500});
        input.add(input1);
        List<int[]> input2 = new ArrayList<>();
        input2.add(new int[] {930, 1200});
        input2.add(new int[] {1600, 2300});
        input.add(input2);
        List<int[]> input3 = new ArrayList<>();
        input3.add(new int[] {845, 915});
        input3.add(new int[] {1515, 1545});
        input.add(input3);
        List<int[]> output = meetingScheduleQuestionSet.findAllAvailableTimeslots(
                input
        );
        for (int[] out: output) {
            System.out.println(out[0] + "  " + out[1]);
        }

        //_____________________3______________________
        //
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("1st meeting", 10, 1, 3));
        meetings.add(new Meeting("2st meeting", 10, 2, 7));
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("lingOfferRoom", 11));
        meetingScheduleQuestionSet.printMeetingRoomPair(meetings, rooms);
    }
}
