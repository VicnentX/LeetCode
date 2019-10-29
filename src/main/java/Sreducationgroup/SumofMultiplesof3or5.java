package Sreducationgroup;

/*
Sum of all multiples of 3 or 5 less than 1000,
and how you calculated it
(use any language or mathematical formula you want). *
 */

public class SumofMultiplesof3or5 {

    public int getSumofMultiples(int factor1, int factor2, int limit) {
        return factor1 * unitSum(limit / factor1) + factor2 * unitSum(limit / factor2) - factor1 * factor2 * unitSum(limit / (factor1 * factor2));
    }

    private int unitSum(int i) {
        return i * (i + 1) / 2;
    }

    public static void main(String[] args) {
        SumofMultiplesof3or5 sumofMultiplesof3or5 = new SumofMultiplesof3or5();
        System.out.println(sumofMultiplesof3or5.getSumofMultiples(3, 5, 1000 - 1));
    }

}
