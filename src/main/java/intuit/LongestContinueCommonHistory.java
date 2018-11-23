package intuit;

import java.util.*;

public class LongestContinueCommonHistory {
    /*


    //use a 2-d table dp[][]
    //x-axis to put input a (String by string)
    //y-axis to put input b(string by string)
    and each number dp[][] means the length of the common continue history
    for example:
    dp[i][j] = 2 means that a[i-1] = b[i-1] and a[i-2] = [b[j - 2]
    so the table size should be [a.length + 1][b.length + 1]


    */

    public List<String> userAccess(List<String> a , List<String> b){

        List<String> ret = new ArrayList<>();
        int m = a.size();
        int n = b.size();
        int[][] dp = new int[m + 1][n + 1];
        int end = 0;
        int max = 0;
        for(int i = 1 ; i <= m ; ++i){
            for(int j = 1 ; j <= n ; ++j){
                if(a.get(i - 1).equals(b.get(j - 1))){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        end = i;//use a to print the result
                    }
                }
            }
        }
        int start = end - max;
        for(int i = start ; i < end ; ++i){
            ret.add(a.get(i));
        }
        return ret;
    }

    public static void main(String[] args){
        LongestContinueCommonHistory lh = new LongestContinueCommonHistory();
        System.out.println(lh.userAccess(new ArrayList<>(Arrays.asList("aa","bb","cc")) , new ArrayList<>(Arrays.asList("aa","dd","bb","cc"))));
    }
}
