package leetcode.Iterator;

import java.util.Iterator;
import java.util.*;

public class PeekingIterator {

    Integer next;
    Iterator<Integer> iter;
    boolean noElement;

    public PeekingIterator(Iterator<Integer> input) {
        iter = input;
        advanceIter();
    }

    public Integer peek() {
        return next;
    }

    public Integer next() {
        if (noElement) {
            throw new NoSuchElementException();
        }
        Integer res = next;
        advanceIter();
        return res;
    }

    public boolean hasNext() {
        return !noElement;
    }

    private void advanceIter() {
        if (iter.hasNext()) {
            next = iter.next();
        } else {
            noElement = true;
        }
    }
}
