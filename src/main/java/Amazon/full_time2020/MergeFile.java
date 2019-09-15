package Amazon.full_time2020;

/*
Merge N files. 有N个文件，要把它们merge成一个文件。
每一次只能合并2个文件，并且合并的cost等于这两个文件的size之和。合并后的fi‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍le可以再与别的file合并。
要求：找出最小的cost.

Input: 一个list包含N个文件的大小，以及N。
output: 输出一个int，表示最小的合并cost.

把所有的size 都放进一个min heap。用Greedy的方法，
每次（包括第一次）合并完以后放入一个min heap，
再从min heap中找出其中两个最小的文件进行合并，直到heap中只有一个元素。
 */

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeFile {
    public int minCostToMergeFile(int n, List<Integer> files) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int costs = 0;
        for (int file: files) {
            minHeap.add(file);
        }

        while (minHeap.size() > 1) {
            int file1 = minHeap.poll();
            int file2 = minHeap.poll();
            costs += (file1 + file2);
            minHeap.add(file1 + file2);
        }

        return costs;
    }

    public static void main(String[] args) {
        MergeFile mergeFile = new MergeFile();
        System.out.println(mergeFile.minCostToMergeFile(5, Arrays.asList(1,2,3)));
    }
}
