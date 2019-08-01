package leetcode;

import java.util.PriorityQueue;

public class Main {



    public static void main(String[] args) {

        PriorityQueue<Object[]> heap = new PriorityQueue<>((a,b) -> (int)a[1] - (int)b[1]);
        heap.add(new Object[] {'a' , 2});
        heap.add(new Object[] {'b' , 1});
        heap.add(new Object[] {'c' , 3});
        System.out.println((int)heap.peek()[1]);

        String name = "Vicnent";
        String home = "CA";

        System.out.println(String.format("this is %s and he is in %s", name, home));
    }
}
