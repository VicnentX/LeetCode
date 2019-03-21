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

public class Alerter {
}
