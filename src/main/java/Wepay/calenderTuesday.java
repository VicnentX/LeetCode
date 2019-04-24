package Wepay;

/*
每年十月份的第二个星期二被认为是XXX节，给你年份，要求返回XXX节是当年十月的第几天？

https://www.jianshu.com/p/6ef54da8932e 这个是calendar类 定义

https://lw900925.github.io/java/java8-newtime-api.html 这篇文章讲的很清楚 相当好！！！！
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.lang.Object;


public class calenderTuesday {
    public int date (int year) {
        LocalDate date = LocalDate.of(year,10,1);//date 就是year.10.1
        //就有如下的可行method的
        /*
        LocalDate localDate = LocalDate.of(2017, 1, 4);     // 初始化一个日期：2017-01-04
        int year = localDate.getYear();                     // 年份：2017
        Month month = localDate.getMonth();                 // 月份：JANUARY
        int dayOfMonth = localDate.getDayOfMonth();         // 月份中的第几天：4
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();     // 一周的第几天：WEDNESDAY
        int length = localDate.lengthOfMonth();             // 月份的天数：31
        boolean leapYear = localDate.isLeapYear();          // 是否为闰年：false
         */
//        if (date.getDayOfWeek() == DayOfWeek.TUESDAY) {
//            // if the year.10.1 is tuesday ,
//            // date will be add one more week and then that date is the answer
//            return date.plusWeeks(1).getDayOfMonth();
//        }
        /*
        LocalDate date = LocalDate.of(2017, 1, 5);          // 2017-01-05

        LocalDate date1 = date.withYear(2016);              // 修改为 2016-01-05
        LocalDate date2 = date.withMonth(2);                // 修改为 2017-02-05
        LocalDate date3 = date.withDayOfMonth(1);           // 修改为 2017-01-01

        LocalDate date4 = date.plusYears(1);                // 增加一年 2018-01-05
        LocalDate date5 = date.minusMonths(2);              // 减少两个月 2016-11-05
        LocalDate date6 = date.plus(5, ChronoUnit.DAYS);    // 增加5天 2017-01-10
         */

        /*
        上面例子中对于日期的操作比较简单，但是有些时候我们要面临更复杂的时间操作，比如将时间调到下一个工作日，或者是下个月的最后一天，这时候我们可以使用with()方法的另一个重载方法，它接收一个TemporalAdjuster参数，可以使我们更加灵活的调整日期：

        LocalDate date7 = date.with(nextOrSame(DayOfWeek.SUNDAY));      // 返回下一个距离当前时间最近的星期日
        LocalDate date9 = date.with(lastInMonth(DayOfWeek.SATURDAY));   // 返回本月最后一个星期六
        要使上面的代码正确编译，你需要使用静态导入TemporalAdjusters对象：

        import static java.time.temporal.TemporalAdjusters.*;
        TemporalAdjusters类中包含了很多静态方法可以直接使用，下面的表格列出了一些方法：

        方法名	描述
        dayOfWeekInMonth	返回同一个月中每周的第几天
        firstDayOfMonth	返回当月的第一天
        firstDayOfNextMonth	返回下月的第一天
        firstDayOfNextYear	返回下一年的第一天
        firstDayOfYear	返回本年的第一天
        firstInMonth	返回同一个月中第一个星期几
        lastDayOfMonth	返回当月的最后一天
        lastDayOfNextMonth	返回下月的最后一天
        lastDayOfNextYear	返回下一年的最后一天
        lastDayOfYear	返回本年的最后一天
        lastInMonth	返回同一个月中最后一个星期几
        next / previous	返回后一个/前一个给定的星期几
        nextOrSame / previousOrSame	返回后一个/前一个给定的星期几，如果这个值满足条件，直接返回
         */

        return date.with(nextOrSame(DayOfWeek.TUESDAY)).plusWeeks(1).getDayOfMonth();
    }

    public static void main (String[] args) {
        calenderTuesday ct = new calenderTuesday();
        System.out.println(ct.date(2018));
        System.out.println(ct.date(2017));
        System.out.println(ct.date(2016));
    }
}
