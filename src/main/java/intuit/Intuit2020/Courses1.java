package intuit.Intuit2020;

import java.util.*;

/*
1. 课程 -Ben
第一题：输入学生的ID和他上的课程，找到每两个学生上的相同的课程。
例如，输入{{"58", "A"},  {"94", "B"},  {"17", "A"},  {"58", "B"},  {"17", "B"},  {"58", "C"}}
输出:[58, 94]: [B]
        [58, 17]: [A, B]
        [94, 17]: [B]

第二题：给出一些课程和课程的先修课，每个课程有且只有一门先修课，并且保证学生只有一条path修完所有课程，求修到一半时的课程名称。
例如，输入{{A, B}, {C, D}, {B, C}, {E, F}, {D, E}, {F, G}}, 输出 D.

第三题: 第二题的follow up,假设每门课程可以有多门先修课,找出所有path修到一半课程的名称
我思路是把它看成图,然后记录每个点的indegree值从而找到indegree为0的点即为起始点,之后使用拓扑排序或者dfs来找到所有的路径(routes),再对每条路径使用第二题的函数来找到所有路径的中点.

 */
public class Courses1 {

    public Map<String, List<String>> question1(String[][] studentCoursesPair) {
        Map<String, List<String>> studentCoursesMap = new HashMap<>();
        Map<String, List<String>> commonCoursesMap = new HashMap<>();
        Set<String> students = new HashSet<>();

        //fill the map
        for (String[] pair: studentCoursesPair) {
            String student = pair[0];
            String course = pair[1];
            students.add(student);
            if (!studentCoursesMap.containsKey(pair[0])) {
                studentCoursesMap.put(student, new ArrayList<>());
            }
            studentCoursesMap.get(student).add(course);
        }

        List<String> studentsList = new ArrayList<>(students);
        final int N = studentsList.size();

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                List<String> list1 = studentCoursesMap.get(i);
                List<String> list2 = studentCoursesMap.get(j);
                list1.retainAll(list2);
                commonCoursesMap.put(i + ", " + j, list1);
            }
        }

        return commonCoursesMap;
    }

}
