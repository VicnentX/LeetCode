package google.google2020.google_加油少年_VO;

/*
From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.



Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".


ConstraintargetArray:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestWaytoFormString1055 {

    /**
     * follow up 2: fine. what's the time complexity for above solutions. O(MN). could u make it better?
     * the time complexity is better than O (MN), should be O(logM * N) or O (N)
     * to find a logM way, it is easy to think of binary search. for each char in tar, we need loop from j to end, to find a char same as tar[i].
     * we can build a map which key is from 'a' -> 'z', the value is idx for this char in src. because idx is add from small to big. when we iterate tar[i], we can easily to find the tar[i]'s idx list. to search is there a idx is larger or equal than j+1. it is logM. and we have N char in tar, so the time complexity is N * logM
     * the time is to build the map is O(M);
     * @param source
     * @param target
     * @return
     */
    public int shortestWayONlogM(String source, String target) {
        char[] sourceArray = source.toCharArray(), targetArray = target.toCharArray();
        int ret = 1;
        List<Integer>[] idx = new List[26];
        for (int i = 0; i < 26; i++) idx[i] = new ArrayList<>();
        for (int i = 0; i < sourceArray.length; i++) idx[sourceArray[i] - 'a'].add(i);
        int j = 0;
        for (int i = 0; i < targetArray.length; ) {
            List<Integer> tar = idx[targetArray[i] - 'a'];
            if (tar.isEmpty()) return -1;
            int k = Collections.binarySearch(tar,j);
            if (k < 0) k = -k - 1;
            if (k == tar.size()) {
                ret++;
                j = 0;
            } else {
                j = tar.get(k) + 1;
                i++;
            }

        }
        return ret;
    }

    /**
     * 这里很有意思，就是map(or int[][])把每个点都记下来，就是的key(or [i][j])是这个字母 加 起始搜索的位置（针对于source），map的value就是下一个可以选取的在source里面的位置
     * follow up 3: great. could u improve it more?
     * so we have to think a solution which is O(N), how should we use O(1) to know the next J pos?
     * maybe we can use more to save time.
     * in binary search solution we will have a map like a ->{1,3,7,16} (total src length is 20), so we need binary search.
     * if we can flatten them, i mean for each pos in 20 length, we just save the next idx, we can use O 1 to find the next J.
     * a -> {1,1,3,3,7,7,7,7,16,16,16,16,16,16,16,16,16,0,0,0}
     * for example if now j is 4, we can just check map[4] = 7; we know 7 pos have an 'a', so next j will be 7 + 1.
     * if now j is 17, we get map[17] = 0, we know there is no more j after. so j = 0, ret++;
     * the time complexity is O (N) , and build the map cost 26 * M
     */

    public int shortestWayOn(String source, String target) {
        char[] sourceArray = source.toCharArray(), targetArray = target.toCharArray();
        int[][] idx = new int[26][sourceArray.length];
        for (int i = 0; i < sourceArray.length; i++) idx[sourceArray[i] - 'a'][i] = i + 1;
        for (int i = 0; i < 26; i++) {
            for (int j = sourceArray.length - 1, pre = 0; j >= 0; j--) {
                if (idx[i][j] == 0) idx[i][j] = pre;
                else pre = idx[i][j];
            }
        }
        int ret = 1, j = 0;
        for (int i = 0; i < targetArray.length; i++) {
            if (j == sourceArray.length) {
                j = 0;
                ret++;
            }
            if (idx[targetArray[i] - 'a'][0] == 0) return -1;
            j = idx[targetArray[i] - 'a'][j];
            if (j == 0 ) {
                ret++;
                i--;
            }
        }
        return  ret;
    }


    public int shortestWay(String source, String target) {

        //O(MN)
        int ret = 0;
        int j = 0;
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        while (j < t.length) {
            int i = 0;
            boolean isMatch = false;
            while (i < s.length && j < t.length) {
                if (s[i] == t[i]) {
                    isMatch = true;
                    ++j;
                }
                ++i;
            }
            if (!isMatch) return -1;
            ret++;
        }
        return ret;
    }

}
