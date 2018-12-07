package leetcode.ListNode_or_TreeNode;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Seen this question in a real interview before?

 */

import java.util.HashMap;
import java.util.Map;

public class CopyListwithRandomPointer138 {
    public class RandomListNode{
        int val;
        RandomListNode random , next;
        RandomListNode(int x){
            val = x;
        }
    }
    public RandomListNode copyList(RandomListNode head){
        if(head == null){
            return null;
        }
        Map<RandomListNode , RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        //make copy of nodea and val
        while(node != null){
            map.put(node , new RandomListNode(node.val));
            node = node.next;
        }
        //assign next and random
        node = head;
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}
