package google.google2020.google_加油少年_VO;

/*
找出所有的factor
 */

/**
 * 方法一： 从1搜到n开根， 如果 i 能被 n整除 就把 i 和 n/i 放入结果(当心duplicate)
 */

/**
 * 方法二： 找出所有的质因数（duplicate or occurrence）
 * 然后他们的combination + 1 + n 就是所有的因子
 * 举出24这个例子
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllFactors {

    public List<Integer> gerAllfactors(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); ++i) {
            if (gcd(i, n) == i) {
                ret.add(i);
                if (i != Math.sqrt(n)) ret.add(n / i);
            }
        }
        return ret;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


    /**
     * 找质因数的方法
     */
// Program to print all prime factors
// A function to print all prime factors
    // of a given number n
    public static List<Integer> primeFactors(int n)
    {
        List<Integer> ret = new ArrayList<>();
        // Print the number of 2s that divide n
        while (n%2==0)
        {
            ret.add(2);
            System.out.print(2 + " ");
            n /= 2;
        }

        // n must be odd at this point. So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                ret.add(i);
                System.out.print(i + " ");
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2) {
            ret.add(n);
            System.out.print(n);
        }

        return ret;
    }

    public static void main(String[] args) {
        FindAllFactors findAllFactors = new FindAllFactors();

        List<Integer> ret = findAllFactors.gerAllfactors(24);
        for (int i: ret) {
            System.out.println(i);
        }
        System.out.println("--------------");

        ret = findAllFactors.gerAllfactors(9999999);
        for (int i: ret) {
            System.out.println(i);
        }
        System.out.println("--------------");

        ret = findAllFactors.gerAllfactors(16);
        for (int i: ret) {
            System.out.println(i);
        }
        System.out.println("--------------");

        //set of prime factors
        List<Integer> primefactorsList = primeFactors(24);

        System.out.println();
        // print all result
        getCombination(0, primefactorsList.size(), primefactorsList, 1, new HashSet<>());

    }

    private static void getCombination(int i, int n, List<Integer> primefactorsList, int product, Set<Integer>visited) {
        if (i == n) {
            if (visited.add(product)) {
                System.out.print(product + " ");
            }
            return;
        }

        getCombination(i + 1, n , primefactorsList, product * primefactorsList.get(i), visited);
        if (i != 0) {
            getCombination(i + 1, n, primefactorsList, product, visited);
        }
    }



}
