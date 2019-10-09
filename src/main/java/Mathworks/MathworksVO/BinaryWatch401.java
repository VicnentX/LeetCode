package Mathworks.MathworksVO;

import java.util.ArrayList;
import java.util.List;

/*
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
Seen this question in a real interview before?

 */


/**
 * 60 占用5位 所以相当以把'hour"的val 往右移动6位 avoid overlap
 * 然后 count的bit
 * 满足条件就放到答案里面
 */

public class BinaryWatch401 {
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h=0; h<12; h++)
            for (int m=0; m<60; m++)
                if (Integer.bitCount(h * 64 + m) == num)
                    times.add(String.format("%d:%02d", h, m));
        return times;
    }

    public static void main(String[] args) {
        BinaryWatch401 binaryWatch401 = new BinaryWatch401();
        System.out.println("num = 1 is as below: ");
        for (String time: binaryWatch401.readBinaryWatch(1)) {
            System.out.println(time);
        }
        System.out.println("num = 2 is as below: ");
        for (String time: binaryWatch401.readBinaryWatch(2)) {
            System.out.println(time);
        }
    }
}
