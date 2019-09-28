package leetcode;

import java.util.HashMap;
import java.util.Map;
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


        final Map<Integer , Integer> processOrder = new HashMap<Integer, Integer>() {{
            put(1, 1);
            put(2, 2);
            put(3, 3);
        }};
        System.out.println(processOrder.containsKey(null));

        String s = "[111:222]";
        String[] ss = s.split(":");
        System.out.println(ss.length);
        for (String sss: ss) {
            System.out.println(sss);
        }
    }
}
