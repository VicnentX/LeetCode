package google;

import java.util.Arrays;

//time complexity O(n)
//space complexity O(n)

public class StoresAndHouses {
    public int[] minDistance(int[] houses , int[] stores){
        int n = stores.length;
        int[][] s = new int[n][2];
        for(int i = 0 ; i < n ; ++i){
            s[i][0] = i;
            s[i][1] = stores[i];
        }
        Arrays.sort(s , (a , b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int m = houses.length;
        int[] ret = new int[m];
        for(int i = 0 ; i < m ; ++i){
            int value = houses[i];//house position
            int l = 0;
            int r = n - 1;
            while(l < r){
                int mid = (l + r) /2;
                if(value <= s[mid][1]){
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            int upper = l;//这里出来的l是upper bound 特别要注意！！！！
            if(l == 0) ret[i] = s[0][1];
            else{
                int lower = l - 1;
                if(Math.abs(value - s[upper][1]) >= Math.abs(value - s[lower][1])) ret[i] = s[lower][1];//这里是小于等于要特别当心
                else ret[i] = s[upper][1];
            }
        }
        return ret;
    }

    public static void main(String[] args){
        StoresAndHouses sh = new StoresAndHouses();
        int[] result = sh.minDistance(new int[]{0,2,5,10} , new int[]{1,3,5,7,8,9});
        for(int k : result){
            System.out.print(k + " ");
        }
        System.out.println();
    }
}
