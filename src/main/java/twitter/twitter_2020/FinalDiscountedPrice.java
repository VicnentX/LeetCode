package twitter.twitter_2020;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 这题就是输出两行
 * 题目是每个商品的价格等于自身价格减去右边第一个比他小或等于的价格 形成最终价格
 * 第一行输出所有商品final price的sum
 * 第二行是一个list 输出所有以原价卖出的东西的index
 * key tips 因为队列内部保证从头到尾递增，所有从尾部试起
 *
 * https://blog.csdn.net/shf4715/article/details/47052385
 */

public class FinalDiscountedPrice {
    public void final_price_process(int[] input) {
        Deque<Integer> dq = new LinkedList<>();
        long sum = 0;
        int n = input.length;
        for(int i = 0 ; i < n ; ++i) {
            while(!dq.isEmpty() && input[dq.peekLast()] >= input[i]) {
                input[dq.peekLast()] -= input[i];
                sum += input[dq.peekLast()];
                dq.pollLast();
            }
            dq.offer(i);
        }
        System.out.println(sum);
        List<Integer> fullPrice = new ArrayList<>();
        while(!dq.isEmpty()) {
            fullPrice.add(dq.pop());
        }
        System.out.println(fullPrice);
    }

    public static void main(String[] args) {
        FinalDiscountedPrice fdp = new FinalDiscountedPrice();
        fdp.final_price_process(new int[] {2,3,1,2,4,2});
    }
}
