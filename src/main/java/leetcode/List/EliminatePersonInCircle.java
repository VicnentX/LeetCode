package leetcode.List;

/*
给两个数字作为输入， 一个是n 代表n个人 index from 0
第二个数字是 k 就是数k个就删掉那个人，然后从他下一个再开始数

output 把删掉的人的index按删掉的顺序输出
 */

import java.util.ArrayList;
import java.util.List;

public class EliminatePersonInCircle {

    class Node {
        int val;
        Node next;
        Node (int x) {
            val = x;
        }
    }

    public List<Integer> removePersonFromCircle(int n, int k) {
        //make a circle list
        Node head = new Node (0);
        Node cur = head;
        for (int i = 1; i < n; ++i) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        cur.next = head;

        //fill the ret;
        List<Integer> ret = new ArrayList<>();
        int len = n;
        while (len > 1) {
            int step = (k % len == 0 ? len : k % len);
            while (step > 1) {
                head = head.next;
                step--;
            }
            ret.add(head.next.val);
            head.next = head.next.next;
            head = head.next;
            len--;
        }

        //return
        return ret;
    }

    public static void main(String[] args) {
        EliminatePersonInCircle eliminatePersonInCircle = new EliminatePersonInCircle();
        //2 0 4 1
        for (int i: eliminatePersonInCircle.removePersonFromCircle(5, 2)) {
            System.out.println(i);
        }
    }

}
