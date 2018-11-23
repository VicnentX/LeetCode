package leetcode.Number;

import java.util.*;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
//方法一
//         if(n == 0) return tasks.length;

//         int[] c = new int[26];

//         for(char ch : tasks){
//             ++c[ch - 'A'];
//         }
//         Arrays.sort(c);
//         int i=25;
//         while(i >= 0 && c[i] == c[25]){
//             --i;
//         }

//         return Math.max(tasks.length , (c[25] -1) * (n+1) + (25 - i));

        //方法二 这个方法是做rearrange k distance那题的 hard

        if(n == 0) return tasks.length;

        PriorityQueue<Map.Entry<Character , Integer>> maxHeap = new PriorityQueue<>((a,b) -> (b.getValue() - a.getValue()));
        HashMap<Character , Integer> map = new HashMap<>();
        HashMap<Character , Integer> tem = new HashMap<>();

        for(char ch : tasks){
            map.put(ch, map.getOrDefault(ch , 0) + 1);
        }

        for(Map.Entry<Character , Integer> entry : map.entrySet()){
            maxHeap.add(entry);
        }

        int idle = 0;

        while(!maxHeap.isEmpty()){
            for(int i = 0; i < n + 1; ++i){
                if(!maxHeap.isEmpty()){
                    maxHeap.peek();
                    if(maxHeap.peek().getValue() - 1 != 0)  tem.put(maxHeap.peek().getKey() , maxHeap.peek().getValue() - 1);
                    maxHeap.poll();
                }else{
                    if(!tem.isEmpty()){
                        idle += (n + 1 - i);
                    }
                    break;
                }

            }
            for(Map.Entry<Character , Integer> entry : tem.entrySet()){
                maxHeap.add(entry);
            }
            tem.clear();
        }

        return tasks.length + idle;

    }

    public static void main(String[] args){
        TaskScheduler ts = new TaskScheduler();
        System.out.println(ts.leastInterval(new char[]{'A','A','A','B','B','B'} , 2));
    }

}
