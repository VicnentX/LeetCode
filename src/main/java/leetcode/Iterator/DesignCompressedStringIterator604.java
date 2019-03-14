package leetcode.Iterator;

/*
Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
Seen this question in a real interview before?

 */

public class DesignCompressedStringIterator604 {
    int i;
    int n;
    int cnt;
    char[] ch;
    int target;
    int nextIndex;

    public DesignCompressedStringIterator604(String cs) {
        ch = cs.toCharArray();
        n = cs.length();
        i = 0;
        cnt = 0;
        target = 0;
        nextIndex = 0;
    }

    public char next() {
        if (i == n) return ' ';
        char cur = ch[i];
        if (target == 0) {
            int len = 1;
            while (len + i < n && ch[i + len] >= '0' && ch[i + len] <= '9') {
                target = target * 10 + ch[i + len] - '0';
                ++len;
            }
            nextIndex = i + len;
        }
        ++cnt;
        if (cnt == target) {
            i = nextIndex;
            target = 0;
            cnt = 0;
        }
        return cur;
    }

    public boolean hasNext() {
        return i < n;
    }
}
