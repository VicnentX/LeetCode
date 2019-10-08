package Amazon.full_time2020.final_round_加油少年_coding;


/*
三轮背靠背，全部怎么简单怎么来，但是还是感觉菜的一批小错误不断。。
第一个老manager疯狂挑刺，写一句问一句为啥这么写，这么写的原因。题目是一个两层的nested linkedlist，要求压平然后排序。直接抄出来然后快排就行了。然而没写完。。

我的理解就是 先全部读取出来 变成 list《integer》 然后再sort
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NestedLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
            next = null;
        }
    }

    public List<Integer> flatAndSort(List<ListNode> list) {
        List<Integer> ret = new ArrayList<>();

        for (ListNode head: list) {
            while(head != null) {
                ret.add(head.val);
                head = head.next;
            }
        }

        Collections.sort(ret);
        return ret;
    }
}
