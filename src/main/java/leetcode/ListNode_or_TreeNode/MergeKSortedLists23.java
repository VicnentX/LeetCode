package leetcode.ListNode_or_TreeNode;

import java.util.*;

public class MergeKSortedLists23 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Object[]> heap = new PriorityQueue<>((a,b) -> ((ListNode)a[0]).val - ((ListNode)b[0]).val);
        for (int i = 0 ; i < lists.length ; ++i) {
            if (lists[i] != null) {
                heap.add(new Object[] {lists[i] , i});
                lists[i] = lists[i].next;
            }
        }
        ListNode dummy = new ListNode(-1) , head = dummy;
        while (!heap.isEmpty()) {
            Object[] cur = heap.poll();
            head.next = (ListNode)cur[0];
            head = head.next;
            int index = (int)cur[1];
            if (lists[index] != null) {
                heap.add(new Object[] {lists[index] , index});
                lists[index] = lists[index].next;
            }
        }
        return dummy.next;
    }
}
