package Amazon.full_time2020.final_round_加油少年_coding;

/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.



Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


Note:

You must return the copy of the given head as a reference to the cloned list.
 */


import java.util.HashMap;
import java.util.Map;

/**
 * clarify if there is null for next or random point?
 * 
 */

public class CopyListwithRandomPointer138 {

    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    /**
     * 这是一个需要额外空间的方法
     * @param head
     * @return
     */

    public Node copyRandomListWithSpace(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<Node, Node>();

        // loop 1. copy all the nodes
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val, null, null));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    /**
     * 这是一个 ！！！不 ！！！需要额外空间的方法
     * @param head
     * @return
     */


    public Node copyRandomListWithoutSpace(Node head) {
        Node iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        // node1 -> copyNode1 -> node2 - > copyNode2 ...
        while (iter != null) {
            next = iter.next;

            Node copy = new Node(iter.val, null, null);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        //node1 - node2 - node3
        //copynode1 - copynode2 - ....
        iter = head;
        Node newCopyHead = new Node(0, null, null);
        Node copy, copyIter = newCopyHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return newCopyHead.next;
    }

}
