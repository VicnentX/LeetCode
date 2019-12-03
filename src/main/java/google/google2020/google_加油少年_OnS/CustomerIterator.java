package google.google2020.google_加油少年_OnS;

/*
印度小哥：写一个CustomerIterator class，
构造函数输入是 两个 iterator，
作用是merge两个iterator并且跳过所有之前遇到过的数字。
写题的时候没有跟我说话，
静静看我写出bug自己跑test case的时候把bug改掉。。。凉。
 */

import java.util.*;

public class CustomerIterator {

    private Iterator<Integer> it1, it2;
    Set<Integer> set;

    public CustomerIterator(Iterator<Integer> it1, Iterator<Integer> it2) {
        this.it1 = it1;
        this.it2 = it2;
        set = new HashSet<>();
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }

    public int next() {
        while (it1.hasNext()) {
            int cur = it1.next();
            if (set.add(cur)) {
                return cur;
            }
        }

        if (it2.hasNext()) {
            int cur = it2.next();
            if (set.add(cur)) {
                return cur;
            }
        }

        System.out.println("here is the end of iterator");
        return -1;
    }
}
