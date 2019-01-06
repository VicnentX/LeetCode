package twitter;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'primeQuery' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY first
     *  3. INTEGER_ARRAY second
     *  4. INTEGER_ARRAY values
     *  5. INTEGER_ARRAY queries
     */

    /**
     * 用visited
     * @param args
     */
//    private static int[] prime;
//    public static List<Integer> primeQuery(int n, List<Integer> first, List<Integer> second, List<Integer> value, List<Integer> query) {
//        // Write your code here
//        List<Integer> ret = new ArrayList<>();
//        Map<Integer , List<Integer>> map = new HashMap<>();//key is  node number and value is the list of nodes that are connected with this key node;
//        //fill the map
//        for(int i = 0 ; i < first.size() ; ++i){
//            int a = first.get(i);
//            int b = second.get(i);
//            if(!map.containsKey(a)){
//                map.put(a , new ArrayList<>());
//            }
//            map.get(a).add(b);
//            if (!map.containsKey(b)) {
//                map.put(b, new ArrayList<>());
//            }
//            map.get(b).add(a);
//        }
//        int[] visited = new int[n + 1];//0 means not visited;
//        prime = new int[n + 1];
//
//        //Arrays.fill(prime , -1);
//        dfs_bfs(1 , prime , map , visited , value);
//        for(int i =  0 ; i < query.size() ; ++i){
//            ret.add(prime[query.get(i)]);
//        }
//        return ret;
//    }
//    private static int dfs_bfs(int id , int[] prime , Map<Integer , List<Integer>> map , int[] visited , List<Integer> value){
//
//        visited[id] = 1;
//
//        if (isPrime(value.get(id))) {
//            prime[id] = 1;
//        } else {
//            prime[id] = 0;
//        }
//        for(int k : map.get(id)){
//            if(visited[k] == 0){
//                //visited[k] = 1;
//                prime[id] += dfs_bfs(k, prime, map , visited , value);
//            }
//        }
//
//        return prime[id];
//
//    }
//
//    private static boolean isPrime(int a){
//        if(a == 1){
//            return false;
//        }
//        boolean flag = true;
//        for (int i = 2; i <= Math.sqrt(a); i++) {
//            if (a % i == 0) {
//                flag = false;
//                break;
//            }
//        }
//        return flag;
//    }

    /**
     * 用parent
     */
    private static int[] prime;
    public static List<Integer> primeQuery(int n, List<Integer> first, List<Integer> second, List<Integer> value, List<Integer> query) {
        // Write your code here
        List<Integer> ret = new ArrayList<>();
        Map<Integer , List<Integer>> map = new HashMap<>();//key is  node number and value is the list of nodes that are connected with this key node;
        //fill the map
        for(int i = 0 ; i < first.size() ; ++i){
            int a = first.get(i);
            int b = second.get(i);
            if(!map.containsKey(a)){
                map.put(a , new ArrayList<>());
            }
            map.get(a).add(b);
            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
        }
        //int[] visited = new int[n + 1];//0 means not visited;
        prime = new int[n + 1];

        dfs(1 , prime , map ,  value ,-1);
        for(int i =  0 ; i < query.size() ; ++i){
            ret.add(prime[query.get(i)]);
        }
        return ret;
    }
    private static int dfs(int id , int[] prime , Map<Integer , List<Integer>> map , List<Integer> value , int parent){


        if (isPrime(value.get(id - 1))) {//！！！！！这里减一很重要 不能漏
            prime[id] = 1;
        } else {
            prime[id] = 0;
        }
        for(int k : map.get(id)){
            if(k != parent){
                //visited[k] = 1;
                prime[id] += dfs(k, prime, map , value , id);
            }
        }

        return prime[id];

    }

    private static boolean isPrime(int a){
        if(a == 1){
            return false;
        }
        boolean flag = true;
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args){
        Result re = new Result();
        System.out.println(re.primeQuery(10 , Arrays.asList(6,8,3,6,4,1,8,5,1) , Arrays.asList(9,9,5,7,8,8,10,8,2) , Arrays.asList(17,29,3,20,11,8,3,23,5,15) , Arrays.asList(1,8,9,6,4,3)));
    }
}



