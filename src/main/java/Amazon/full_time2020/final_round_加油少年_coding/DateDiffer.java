package Amazon.full_time2020.final_round_加油少年_coding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDiffer {
    public void giveDiffer() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2010, 7, 23);
        end.set(2010, 8, 26);
        Date startDate = start.getTime();
        Date endDate = end.getTime();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        DateFormat dateFormat = DateFormat.getDateInstance();
        System.out.println("The difference between "+
                dateFormat.format(startDate)+" and "+
                dateFormat.format(endDate)+" is "+
                diffDays+" days.");
    }

    public boolean moreThan30(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        DateFormat dateFormat = DateFormat.getDateInstance();
        System.out.println("The difference between "+
                dateFormat.format(startDate)+" and "+
                dateFormat.format(endDate)+" is "+
                diffDays+" days.");
        return diffDays > 30;
    }

    public static void main(String[] args) {
        DateDiffer dateDiffer = new DateDiffer();
        dateDiffer.giveDiffer();
        System.out.println(dateDiffer.moreThan30(
                new Date(2010,7,23),
                new Date(2010, 8, 26)));
    }
}
