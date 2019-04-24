package Wepay;

/*
The Alerter is a simple monitoring tool, intended to help detect increases in response time for some process.

It does that by computing a few statistics about the process across a 'window' of a certain number of runs,

and alerting (returning true) if certain thresholds are met.

It takes the following parameters:

- inputs : A list of integer times for the process. This list may be very long.

- window size : how many runs long a window is, as an integer

- allowedIncrease : how far over 'average' a window or value is allowed to be, as a percent.

This is represented as a decimal value based on one, so a 50% allowable increase would be represented as 1.5.. From 1point 3acres bbs

Your Alerter should return true if either of the following conditions are met :

* Any value is more than the allowed increase above the window average in ALL windows in which it appears.

For example :

alert({1, 2, 100, 2, 2}, 3, 1.5) should return alert:

The value 100 appears in three windows, and in all cases is more than 50% over the average window.

alert({1, 2, 4, 2, 2}, 3, 2) should not alert:

The largest outlier is 4, and that value appears in a window with average 2.6, less than 100% of

that average.

* Any window's average is more than the acceptable increase over any previous window's average value.

For example :

alert({1, 2, 100, 2, 2}, 2, 2.5) should alert:

Even though individual value causes an alert, there is a window with average 1.5 and a later window

with an average more than 2.5 times larger.

 */

import java.util.*;

public class Alerter {
    public boolean isAlerter (List<Integer> times , int k , double gain) {
        //k is the size of windows
        if (times.size() == 0 || times.size() < k) return false;
        int n = times.size();
        double[] avg = new double[n - k + 1];
        // avg[i] is the average of window whose beginning index is i
        int sum = 0;
        //calculate slide window average , and text the second condition
        int i ;
        for (i = 0 ; i < k ; ++i) {
            sum += times.get(i);
        }
        avg[i - k] = sum * 1.0 / k;
        double minAvg = avg[i - k];
        for(; i < n ; ++i) {
            sum = sum - times.get(i - k) + times.get(i);
            avg[i - k + 1] = sum * 1.0 / k;
            if (avg[i - k + 1] / minAvg > gain) {
                return true;
            }
            minAvg = Math.min(minAvg , avg[i - k + 1]);
        }
        //calculate slide window max of avg and test the first condition
        Deque<Integer> queue = new LinkedList<>();//存的是avg的index
        for(i = 0 ; i <= n - k ; ++i) {
            if (!queue.isEmpty() && queue.peek() == i - k) {
                queue.poll();
            }
            while (!queue.isEmpty() && avg[i] >= avg[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.add(i);
            if (times.get(i) / avg[queue.peek()] > gain) return true;
        }

        //这里之后已经没有avg［i］了
        for ( ; i < n ; ++i) {
            if (!queue.isEmpty() && queue.peek() == i - k) {
                queue.poll();
            }
            if (times.get(i) / avg[queue.peek()] > gain) return true;
        }

        //all alert requirement has not been met
        return false;
    }

    public static void main (String[] args) {
        Alerter alerter = new Alerter();
        System.out.println(alerter.isAlerter(new ArrayList<>(Arrays.asList(1,2,100,2,2)) , 3 , 1.5));
        System.out.println(alerter.isAlerter(new ArrayList<>(Arrays.asList(1,2,4,2,2)) , 3 , 2));
        System.out.println(alerter.isAlerter(new ArrayList<>(Arrays.asList(10000,2,100,99,2)) , 3 , 1.1));
    }
}
