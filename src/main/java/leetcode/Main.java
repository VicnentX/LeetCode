package leetcode;

import java.util.*;

import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.apache.commons.math3.stat.descriptive.rank.Median.*;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import static java.util.Arrays.*;

public class Main {

    private final static int SKIP_BIT = 16;

    public static void main(String[] args) {

//        PriorityQueue<Object[]> heap = new PriorityQueue<>((a,b) -> (int)a[1] - (int)b[1]);
//        heap.add(new Object[] {'a' , 2});
//        heap.add(new Object[] {'b' , 1});
//        heap.add(new Object[] {'c' , 3});
//        System.out.println((int)heap.peek()[1]);
//
//        String name = "Vicnent";
//        String home = "CA";
//
//        System.out.println(String.format("this is %s and he is in %s", name, home));
//
//
//        final Map<Integer , Integer> processOrder = new HashMap<Integer, Integer>() {{
//            put(1, 1);
//            put(2, 2);
//            put(3, 3);
//        }};
//        System.out.println(processOrder.containsKey(null));
//
//        String s = "[111:222]";
//        String[] ss = s.split(":");
//        System.out.println(ss.length);
//        for (String sss: ss) {
//            System.out.println(sss);
//        }
//
//        for (int i=0; i<256; i++){
//            System.out.println(Integer.toBinaryString(0x100 | i).substring(1));
//        }

//        for (int i = 0; i < (1 << SKIP_BIT); i++) {
//            //这里0x1000 和 SKIP_BIT是相呼应的
//            String skipPattern = Integer.toBinaryString(0x10000 | i).substring(1);
//            if (skipPattern.length() != 16) {
//                System.out.println("wrong");
//                break;
//            }
//            System.out.println(skipPattern);
//        }

//        double[] a = new double[] {1,2,3,4,5,6,8,9,99};
//        System.out.println(new Median().evaluate(a));
//        System.out.println(new Percentile().evaluate(a, 90));
//
//        int[] array = new int[]{1,2,3,4,5,6,4,3,3,2,3,4,5,6};

        int[] aaa = new int[] {1,2,3};
        plusone(aaa);
        for (int element: aaa) {
            System.out.println(element);
        }



//        int[] booksArray = new int[] {1,2,3};
//        PriorityQueue<Integer> bookIdHeap = new PriorityQueue<>((a,b) -> booksArray[a] - booksArray[b]);
//        bookIdHeap.add(1);
//        bookIdHeap.add(2);
//        bookIdHeap.add(0);
//        while (!bookIdHeap.isEmpty()) {
//            System.out.println(bookIdHeap.poll());
//        }
//
//        int[] arrayA = new int[]{1,2,3};
//        int[] arrayB = arrayA.clone();
//        arrayA[0] = 100;
//        System.out.println(arrayB[0]);
//        String[] books = new String[] {"1", "2", "3"};
//
//        String s = String.join(" ", Arrays.copyOfRange(books, 0, 3));
//        System.out.println(s);
    }

    private static void plusone(int[] aaa) {
        for (int i = 0 ; i < aaa.length; ++i) {
            aaa[i] += 1;
        }
    }
}
