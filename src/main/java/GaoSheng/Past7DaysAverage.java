package GaoSheng;

import java.text.DecimalFormat;

public class Past7DaysAverage {
    public String[] calculateAverage(int[] prices) {
        final int N = prices.length;
        DecimalFormat df = new DecimalFormat("#.00");

        String[] ret = new String[N - 6];
        float tempSum = 0;

        for (int i = 0 ; i < N ; ++i) {
            if (i <= 5) {
                tempSum += prices[i];
            } else {
                if (i >= 7) tempSum -= prices[i - 7];
                tempSum += prices[i];
                ret[i - 6] = df.format(tempSum / 7);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Past7DaysAverage past7DaysAverage = new Past7DaysAverage();
        String[] ret = past7DaysAverage.calculateAverage(new int[] {7,8,8,11,9,7,5,6});
        for (String i : ret) {
            System.out.print(i + " ");
        }
        System.out.println();
        ret = past7DaysAverage.calculateAverage(new int[] {1,1,1,1,1,1,1,7});
        for (String i : ret) {
            System.out.print(i + " ");
        }
    }
}
