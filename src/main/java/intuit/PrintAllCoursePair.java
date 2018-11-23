package intuit;

// You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

// Write a function that takes in a list of (student ID number, course name) tuples and returns, for every pair of students, a list of all courses they share.

// Sample Input:
// student_course_pairs = [
// ["58", "Software Design"],
// ["58", "Linear Algebra"],
// ["94", "Art History"],
// ["94", "Operating Systems"],
// ["17", "Software Design"],
// ["58", "Mechanics"],
// ["58", "Economics"],
// ["17", "Linear Algebra"],
// ["17", "Political Science"],
// ["94", "Economics"]
// ]

// Sample Output (in any order)


// find_pairs(student_course_pairs) =>
// {
// [58, 17]: ["Software Design", "Linear Algebra"]
// [58, 94]: ["Economics"]
// [17, 94]: []
// }
/*
map<string , list<Stirng>  :  key =name  value = courses he takes
int[map.size()] : name
for(i = 0 ;to end - 1){
  for(j = i+ 1  to end){

  }
}
*/


import java.io.*;
import java.util.*;


public class PrintAllCoursePair {

//   time comlexity: O(n2)
//     space O(n)

   public List<List<String>> printpair(String[][] s){
     List<List<String>> ret = new ArrayList<>();
     Map<String , HashSet<String>> map = new HashMap<>();
     //fill the map
     for(String[] k : s){
       if(!map.containsKey(k[0])){
         map.put(k[0] , new HashSet<>());
       }
       map.get(k[0]).add(k[1]);
     }


     //build name String[]
     int m = map.size();
     String[] name = new String[m];
     int index = 0;
     for(String k : map.keySet()){
       name[index] = k;
       index++;
       System.out.println(k + map.get(k));
     }
     //fill the ret
     for(int i = 0 ; i < m - 1 ; ++i){
       for(int j = i + 1 ; j < m ; ++j){
         List<String> tem = new ArrayList<>();
         tem.add(name[i] + "," + name[j] + ":");
         for(String k : map.get(name[i])){
           if(map.get(name[j]).contains(k)){
             tem.add(k);
           }
         }
         ret.add(tem);
       }
     }
     return ret;
   }

   public static void main(String[] args) {

       String[][] studentCoursePairs = {
               {"58", "Software Design"},
               {"58", "Linear Algebra"},
               {"94", "Art History"},
               {"17", "Software Design"},
               {"58", "Mechanics"},
               {"58", "Economics"},
               {"17", "Linear Algebra"},
               {"17", "Political Science"},
               {"94", "Economics"},
               {"123", "Art History"}
       };

       PrintAllCoursePair sol = new PrintAllCoursePair();
       System.out.println(sol.printpair(studentCoursePairs));
   }
}
