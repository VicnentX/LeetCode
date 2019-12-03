package google.google2020.google_加油少年_ResidentVO;

/*
感觉这题是维护一个size为capacity的queue(sliding window)，
然后一个size为k的minHeap: heap1,
然后一个size为capacity - k的maxHeap： heap2,
每次跟堆顶元素比就知道被移除的元素在那个堆里，然后remove掉。
同时维护一个sum，代表heap2里面的和，
然后return sum/heap2.size() 还是sum/(capacity - k).
这里我不太确定，因为条件不全。然后这个返回值就是average。
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SkipTopKAverage {
    public int capacity;
    public int topK;
    public static int sum = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
    Queue<Integer> queue = new LinkedList<>();

    public SkipTopKAverage(int c, int k) {
        capacity = c;
        topK = k;
    }

    public void input(int num) {
        if (queue.size() == capacity) {
            int head = queue.poll();
            queue.add(num);

            if (head >= minHeap.peek()) {   //head in minHeap
                minHeap.remove(head);
                if (num > maxHeap.peek()) { //add num to minHeap
                    minHeap.add(num);
                } else {                    //move maxHeap top to minHeap , then add num to minHeap
                    int minHeapTop = maxHeap.poll();
                    sum -= minHeapTop;
                    minHeap.add(minHeapTop);
                    minHeap.add(num);
                }
            } else {                        //head in maxHeap
                maxHeap.remove(head);
                sum -= head;
                if (num > minHeap.peek()) { //move minheap top to maxheap , then add num to minheap
                    int minHeapTop = minHeap.poll();
                    maxHeap.add(minHeapTop);
                    sum += minHeapTop;
                    minHeap.add(num);
                } else {                    //add num to maxheap
                    maxHeap.add(num);
                    sum += num;
                }
            }
        } else {
            queue.add(num);

            if (minHeap.size() < topK) {
                minHeap.add(num);
            } else {
                if (num > minHeap.peek()) {
                    int moved = minHeap.poll();
                    minHeap.add(num);
                    maxHeap.add(moved);
                    sum += moved;
                } else {
                    maxHeap.add(num);
                    sum += num;
                }
            }
        }
    }

    public double average() {
        return sum * 1.0 / queue.size();
    }

    public static void main(String[] args) {
        SkipTopKAverage skipTopKAverage = new SkipTopKAverage(4, 2);
        //30
        //20
        //100
        //10
        skipTopKAverage.input(30);
        System.out.println(skipTopKAverage.average());
        skipTopKAverage.input(20);
        System.out.println(skipTopKAverage.average());
        skipTopKAverage.input(100);
        System.out.println(skipTopKAverage.average());
        skipTopKAverage.input(10);
        System.out.println(skipTopKAverage.average());

    }

}
