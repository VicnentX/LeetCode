package leetcode.Number;

import java.util.*;

public class FindMedianFromDataStream {

    PriorityQueue<Integer> maxHeap=new PriorityQueue<>();
    PriorityQueue<Integer> minHeap=new PriorityQueue<>(Collections.reverseOrder());//(Collections.reverseOrder())



    /** initialize your data structure here. */
    public FindMedianFromDataStream() {

    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if(maxHeap.size()<minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }


    public double findMedian() {
        return maxHeap.size()==minHeap.size()?  (double)((minHeap.peek()+maxHeap.peek()))/2 : (double)maxHeap.peek();
    }


    public static void main(String[] args){
        FindMedianFromDataStream fm=new FindMedianFromDataStream();
        fm.addNum(3);
        fm.addNum(6);
        System.out.println(fm.findMedian());
        fm.addNum(8);
        System.out.println(fm.findMedian());
    }

}


//Q：如果要求第n/10个数字该怎么做？
// A：改变两个堆的大小比例，当求n/2即中位数时，两个堆是一样大的。而n/10时，说明有n/10个数小于目标数，9n/10个数大于目标数。所以我们保证最小堆是最大堆的9倍大小就行了。



/*
这边的写上去是为了记住怎么建立最大堆 特别注意这里的最大堆最小堆的名字和我程序是反的
  public MedianFinder(){//这个是构造函数 名字我这边改成了文件名 略有不同 。。。我用Collections.reverseOrder()来节省了在构造函数里面写这些了
        // 新建最大堆
        maxheap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        // 新建最小堆
        minheap = new PriorityQueue<Integer>();
    }

*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */