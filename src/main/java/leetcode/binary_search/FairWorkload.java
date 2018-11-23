package leetcode.binary_search;

import java.util.*;
/*
this point I will show how all this talk can be used to solve a Topcoder problem. For this I have chosen a moderately difficult problem, FairWorkload, which was the division 1 level 2 problem in SRM 169.

In the problem, a number of workers need to examine a number of filing cabinets. The cabinets are not all of the same size and we are told for each cabinet how many folders it contains. We are asked to find an assignment such that each worker gets a sequential series of cabinets to go through and that it minimizes the maximum amount of folders that a worker would have to look through.

After getting familiar with the problem, a touch of creativity is required. Imagine that we have an unlimited number of workers at our disposal. The crucial observation is that, for some number MAX, we can calculate the minimum number of workers needed so that each worker has to examine no more than MAX folders (if this is possible). Let’s see how we’d do that. Some worker needs to examine the first cabinet so we assign any worker to it. But, since the cabinets must be assigned in sequential order (a worker cannot examine cabinets 1 and 3 without examining 2 as well), it’s always optimal to assign him to the second cabinet as well, if this does not take him over the limit we introduced (MAX). If it would take him over the limit, we conclude that his work is done and assign a new worker to the second cabinet. We proceed in a similar manner until all the cabinets have been assigned and assert that we’ve used the minimum number of workers possible, with the artificial limit we introduced. Note here that the number of workers is inversely proportional to MAX: the higher we set our limit, the fewer workers we will need.

Now, if you go back and carefully examine what we’re asked for in the problem statement, you can see that we are really asked for the smallest MAX such that the number of workers required is less than or equal to the number of workers available. With that in mind, we’re almost done, we just need to connect the dots and see how all of this fits in the frame we’ve laid out for solving problems using binary search.

With the problem rephrased to fit our needs better, we can now examine the predicate Can the workload be spread so that each worker has to examine no more than x folders, with the limited number of workers available? We can use the described greedy algorithm to efficiently evaluate this predicate for any x. This concludes the first part of building a binary search solution, we now just have to prove that the condition in the main theorem is satisfied. But observe that increasing x actually relaxes the limit on the maximum workload, so we can only need the same number of workers or fewer, not more. Thus, if the predicate says yes for some x, it will also say yes for all larger x.

To wrap it up, here’s an STL-driven snippet which solves the problem:
 */
public class FairWorkload {
    public int findMaxLoadLimit(int[] tasks , int workers){
        int l = 0;
        int h = 0;
        for(int k : tasks){
            l = Math.max(k , l);
            h += k;
        }
        while(l < h){
            int required = 1;
            int cur = 0;
            int mid = l + (h - l)/2;
            for(int k : tasks){
                if(cur + k <= mid){
                    //the current worker can handle this task
                    cur += k;
                }else{
                    //assign next worker
                    ++required;
                    cur = k;
                }
            }
            if(required <= workers){
                h = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String[] args){
        FairWorkload fw = new FairWorkload();
        System.out.println(fw.findMaxLoadLimit(new int[]{1,2,3,4,5,6,7,8,9} , 5));
    }
}
