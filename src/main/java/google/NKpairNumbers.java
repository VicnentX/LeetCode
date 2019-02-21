package google;

/*
n = 3
output:
abc
aab
abb
aba
aaa
 */

/*
n = length;
k = # of distinct char
dp[n][k] = # of combination kind of k distinct char in n length string
 */

import java.util.HashMap;
import java.util.*;

public class NKpairNumbers {
    public int findCombinations(int n) {
        int[][] dp = new int[n + 1][n + 1];
        //initialize
        for(int i = 1 ; i <= n ; ++i) {
            dp[i][1] = 1;
        }
        //fill the matrix
        for (int i = 2 ; i <= n ; ++i) {
            for (int j = 2 ; j <= i ; ++j) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] * j;
            }
        }
        //output
        int ret = 0;
        for (int i = 1 ; i <= n ; ++i) {
            ret += dp[n][i];
        }
        return ret;
    }

    //dfs
    private int cnt = 0;
    public int findCombinationsDFS(int n) {
        return dfs(0 , n , 0); // index , n , used
    }
    private int dfs( int i , int n , int used) {
        if (i == n) {
            return 1;
        }

        return dfs(i + 1 , n , used + 1) + used * dfs (i + 1 , n , used);
    }

    //dfs optimal
    public int findCombinationsDFSOptimal(int n) {
        Map<List<Integer> , Integer> map = new HashMap<>();
        return dfsOptimal(0 , n , 0 , map); // i = index of pointer , n is the length , used is # of distinct kind of letter
    }
    private int dfsOptimal (int i , int n , int used , Map<List<Integer> , Integer> map) {

        if (map.containsKey(new ArrayList<>(Arrays.asList(i + 1 , used)))) {
            return map.get(new ArrayList<>(Arrays.asList(i + 1 , used)));
        }
        if (i == n) {
            return 1;
        }

        int cnt = dfsOptimal(i + 1 , n , used + 1 , map) + used * dfsOptimal(i + 1 , n , used , map);
        map.put(new ArrayList<>(Arrays.asList(i + 1 , used)) , cnt);

        return cnt;
    }

    //dfs optimal + print all result
    public List<String> findCombinationsDFSOptimalPrint(int n) {
        Map<List<Integer> , List<String>> map = new HashMap<>();
        return dfsOptimalPrint(0 , n , 0 , map); // i = index of pointer , n is the length , used is # of distinct kind of letter
    }
    private List<String> dfsOptimalPrint (int i , int n , int used , Map<List<Integer> , List<String>> map) {

        List<String> ret = new ArrayList<>();

        if (map.containsKey(new ArrayList<>(Arrays.asList(i + 1 , used)))) {
            return map.get(new ArrayList<>(Arrays.asList(i + 1 , used)));
        }
        if (i == n) {
            ret.add("");
            return ret;
        }

        for (String str : dfsOptimalPrint(i + 1 , n , used + 1 , map)) {
            ret.add(String.valueOf((char)('A' + used)) + str);
        }

        for (String str : dfsOptimalPrint(i + 1 , n , used , map)) {
            for (int ii = 0 ; ii < used ; ++ii) {
                ret.add(String.valueOf((char)('A' + ii)) + str);
            }
        }

        //int cnt = dfsOptimal(i + 1 , n , used + 1 , map) + used * dfsOptimal(i + 1 , n , used , map);
        map.put(new ArrayList<>(Arrays.asList(i + 1 , used)) , ret);

        return ret;
    }



    public static void main (String[] args) {
        NKpairNumbers nk = new NKpairNumbers();
        //n = 5 -> 52
        System.out.println(nk.findCombinations(5));
        System.out.println(nk.findCombinationsDFS(5));
        System.out.println(nk.findCombinationsDFSOptimal(5));
        //n = 6 -> 203
        System.out.println(nk.findCombinations(6));
        System.out.println(nk.findCombinationsDFS(6));
        System.out.println(nk.findCombinationsDFSOptimal(6));

        for (String str : nk.dfsOptimalPrint(0 , 3 , 0 , new HashMap<>())) {
            System.out.println(str);
        }
        System.out.println("_____________________________");


        List<String> input = nk.dfsOptimalPrint(0 , 5 , 0 , new HashMap<>());
        System.out.println(input.size());
        for (String str : input) {
            System.out.println(str);
        }
    }
}
