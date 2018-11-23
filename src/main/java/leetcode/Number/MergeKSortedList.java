package leetcode.Number;

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class MergeKSortedList {
    // 方法一：用recursion
    //先建立一个大的叫mergerLists的函数 ，return的时候 调用自己写的合并函数DIYmergeKLists
//    public ListNode mergeKLists(ListNode[] lists) {
//        return DIYmergeKLists(lists, 0, lists.length - 1);
//    }
//
//    //这里就不断拆分知道只剩下1列 或者两列。剩下两列的时候 就用自己写的合并两列的函数mergeTwoLists
//    private ListNode DIYmergeKLists(ListNode[] lists, int leftBegin, int rightEnd) {
//        if (leftBegin == rightEnd) return lists[leftBegin];
//        if (leftBegin > rightEnd) return null;
//
//        int leftEnd = leftBegin + (rightEnd - leftBegin) / 2;
//        int rightBegin = leftEnd + 1;
//
//        ListNode left = DIYmergeKLists(lists, leftBegin, leftEnd);
//        ListNode right = DIYmergeKLists(lists, rightBegin, rightEnd);
//        return mergeTwoLists(left, right);
//    }
//
//    //这个函数解决最后两列合并的难题，也就是最小问题。
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null) {
//            return l2;
//        } else if (l2 == null) {
//            return l1;
//        } else if (l1.val <= l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        } else { /// l1.val > l2
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }
//    }

    //方法二：在最小堆的方法中 这个方法是很不错的方法 比较简介 解释的也比较清楚
    public ListNode mergeKLists(ListNode[] lists) {

        // initial check
        if (lists == null || lists.length == 0) {
            return null;
        }
        // add first node of each list to the minHeap

        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        // if no node was added to the minHeap we return null...我认为这种情况没有必要讨论了 因为在一开始已经讨论过了。。。
        // if (minHeap.isEmpty()) {
        //     return null;
        // }

        // save head since we need to return it
        ListNode head = minHeap.peek();

        while(!minHeap.isEmpty()) {
            // extract node from minHeap
            ListNode node = minHeap.poll();
            if (node.next != null) {
                // add next node to minHeap
                minHeap.add(node.next);
            }
            // set node.next
            if (!minHeap.isEmpty()) {
                node.next = minHeap.peek();
            }
        }
        return head;

    }
}
