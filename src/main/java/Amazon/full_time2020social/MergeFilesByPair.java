package Amazon.full_time2020social;

/*
题意：

给定n个文件， 和一个list of file size， 求minimum time to merge file



Example1:

Input:

files =  {8， 4， 6， 12}

numOfSubFiles = 4

Output:

58

Explaination:

先将 4 和 6 merge为 size 是 10 的新file。目前为止耗时为10。 files 变为 {8， 10， 12}

再将 8 和 10 merge为 size 是 18 的新file。目前为止耗时为 10 + 18 = 28。files 变为 {12， 18}

最后将 12 和 18 merge为 size 是 30 的新file。目前为止耗时为 10 + 18 + 30 = 58。 files 变为 {30}



Example2:

Input:

files =  {3, 1, 2}

numOfSubFiles = 3

Output:

9

Explaination:

1 + 2 = 3;

3 + 3 = 6;

3 + 6 = 9



Example3:

Input:

files =  {8, 3, 5, 2, 15}

numOfSubFiles = 5

Output:

66

Explaination:

2 + 3 = 5;

5+5 = 10;

10 + 8 = 18;

18 + 15 = 33;

5 + 10 + 18 + 33 = 66




思路： PriorityQueue

将files中所有元素放入minHeap，每次poll两个最小的，merge之后放回minHeap。直至minHeap中只剩一个元素。输出循环中的累计时间。



复杂度：

时间： O(nlogn) 实际是 4*nlogn

空间： O(n)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeFilesByPair {
    public int mergeFiles(List<Integer> files) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ret = 0;
        for (int i: files) {
            pq.add(i);
        }
        while (pq.size() >= 2) {
            int sum = pq.poll() + pq.poll();
            ret += sum;
            pq.add(sum);
        }
        return ret;
    }

    public static void main(String[] args) {
        MergeFilesByPair mergeFilesByPair = new MergeFilesByPair();
        System.out.println(mergeFilesByPair.mergeFiles(new ArrayList<>(Arrays.asList(8,4,6,12))));
    }
}
