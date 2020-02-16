package leetcode.dfs_and_memo;

/*
Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..

Students must be placed in seats in good condition.



Example 1:


Input: seats = [["#",".","#","#",".","#"],
                [".","#","#","#","#","."],
                ["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
Example 2:

Input: seats = [[".","#"],
                ["#","#"],
                ["#","."],
                ["#","#"],
                [".","#"]]
Output: 3
Explanation: Place all students in available seats.

Example 3:

Input: seats = [["#",".",".",".","#"],
                [".","#",".","#","."],
                [".",".","#",".","."],
                [".","#",".","#","."],
                ["#",".",".",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.


Constraints:

seats contains only characters '.' and'#'.
m == seats.length
n == seats[i].length
1 <= m <= 8
1 <= n <= 8
 */

import java.util.*;

public class MaximumStudentsTakingExam1349 {
    Map<String, Integer> map = new HashMap<>();

    public int maxStudents(char[][] seats) {
        return dfs(0, seats, seats.length, map, "");
    }

    private int dfs(int level, char[][] nums, int m, Map<String, Integer> map, String preLine) {

        if (map.containsKey(level + "-" + preLine)) {
            return map.get(level + "-" + preLine);
        }

        int tempMax = 0;

        if (level == m) {
            return 0;
        }

        //layout 里面是 这行的样子 + 这行我加了几个人
        List<List<Object>> layouts = new ArrayList<>();
        produceLine(0, level, nums, nums[0].length, 0, "", preLine, layouts);

        for (List<Object> layout: layouts) {
            tempMax = Math.max(tempMax, (int)layout.get(1) + dfs(level + 1, nums, m, map, (String)layout.get(0)));
        }

        map.put(level + "-" + preLine, tempMax);
        return tempMax;
    }

    private void produceLine(int i, int level, char[][] nums, int n, int personCnt, String line, String preLine, List<List<Object>> layouts) {
        if (i == n) {
            layouts.add(new ArrayList<>(Arrays.asList(line, personCnt)));
            return;
        }
        if (nums[level][i] == '.' && (i == 0 || line.charAt(i - 1) != '1') && (level == 0 || i == 0 || preLine.charAt(i - 1) != '1') && (level == 0 || i == n - 1 || preLine.charAt(i + 1) != '1')) {
            //addPerson
            produceLine(i + 1, level, nums, n, personCnt + 1, line + "1", preLine, layouts);
            //not add
            produceLine(i + 1, level, nums, n, personCnt, line + nums[level][i], preLine, layouts);
        } else {
            //can not add
            produceLine(i + 1, level, nums, n, personCnt, line + nums[level][i], preLine, layouts);
        }
    }
}
