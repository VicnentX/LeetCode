package Mathworks.MathworksVO;

import java.util.HashSet;
import java.util.Set;

/**
 * Redundancy in a linked list：
 * 给一个integer组成的linked list，unsorted，
 * 然后把含有重复数字的node去掉，
 * 比如3->1->2->3->5 变成 3->1->2->5.
 */

public class RedundancyInaLinkedList {
    public class Node {
        int val;
        Node next;
        Node (int x) {
            val = x;
        }
    }

    public Node removeRedundancy(Node head) {
        Set<Integer> repeated = new HashSet<>();
        Node newHead = new Node(-1);
        Node cur = newHead;
        cur.next = head;
        while (cur.next!= null) {
            if (repeated.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
                repeated.add(cur.val);
            }
        }
        return newHead.next;
    }
}
