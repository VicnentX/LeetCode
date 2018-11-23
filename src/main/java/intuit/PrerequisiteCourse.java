package intuit;

import java.util.*;

// You’re developing a system for scheduling advising meetings with students in a Computer Science program. Each meeting should be scheduled when a student has completed 50% of their academic program.

// Each course at our university has at most one prerequisite that must be taken first. No two courses share a prerequisite. There is only one path through the program.

// Write a function that takes a list of (prerequisite, course) pairs, and returns the name of the course that the student will be taking when they are halfway through their sequence of courses.

// Sample input:
//   prereqs_courses = [
//     ["Data Structures", "Algorithms"],
//     ["Foundations of Computer Science", "Operating Systems"],
//     ["Computer Networks", "Computer Architecture"],
//     ["Algorithms", "Foundations of Computer Science"],
//     ["Computer Architecture", "Data Structures"],
//     ["Software Design", "Computer Networks"]
//   ]

// In this case, the order of the courses in the program is:
//   Software Design
//   Computer Networks
//   Computer Architecture
//   Data Structures
//   Algorithms
//   Foundations of Computer Science
//   Operating Systems

// Sample output:
//   "Data Structures"

public class PrerequisiteCourse {

    public String printcourse(String[][] s){
        Map<String , String> map = new HashMap<>();
        Map<String , Integer> indegree = new HashMap<>();
        //fill the map and indgree
        for(String[] k : s){
            map.put(k[0] , k[1]);
            indegree.put(k[1] , 1);
            indegree.put(k[0] , indegree.getOrDefault(k[0] , 0));
        }
        //now we have two maps : map is the relationship and indegree is the indegree map and we use indegree map only once

        //find the first class
        String token = "";
        for(String k : indegree.keySet()){
            if(indegree.get(k) == 0){
                token = k;
                break;
            }
        }
        //
        int m = s.length + 1;
        int target = (m - 1)/2;
        int timer = 0;
        if(target == 0){
            return token;
        }

        String value = "";
        for(timer = 1 ; timer <= target ; ++timer){
            value = map.get(token);
            token = value;
        }

        return value;
    }

    public static void main(String[] args){
        String[][] prereqs_courses = {
                {"Data Structures", "Algorithms"},
                {"Foundations of Computer Science", "Operating Systems"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };

        PrerequisiteCourse sol = new PrerequisiteCourse();
        System.out.println(sol.printcourse(prereqs_courses));
    }

}
