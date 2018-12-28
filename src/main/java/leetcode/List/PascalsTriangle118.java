package leetcode.List;

/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */

import java.util.*;

public class PascalsTriangle118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows <= 0) return ret;
        for (int i = 0 ; i < numRows ; ++i) {
            List<Integer> tem = new ArrayList<>();
            for (int j = 0 ; j < i + 1 ; ++j) {
                if (j == 0 || j == i) {
                    tem.add(1);
                } else {
                    tem.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(new ArrayList<>(tem));
        }
        return ret;
    }
}
