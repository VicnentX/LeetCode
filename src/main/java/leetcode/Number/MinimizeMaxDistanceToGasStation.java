package leetcode.Number;
import java.util.*;

public class MinimizeMaxDistanceToGasStation {
    public double minmaxGasDist(int[] stations, int k) {


            //这道题用二分法；
            //二分的是我们猜测的最小间隔；
            //time complexity = O(nlogW);
            //n = stations.length ; W = Stations[n - 1] - stations[0]

            double l = 0.0;
            double r = stations[stations.length - 1] - stations[0];

            while(r - l > 1e-6){
                double d = (r + l) / 2 ; //这个就是我们用二分法猜测的距离
                int cnt = 0;
                for(int i = 0 ; i < stations.length - 1 ; ++i){
                    cnt += (int)((stations[i + 1] - stations[i])/d);
                }
                if(cnt <= k) r = d;
                else{l = d;}
            }
            return l;
        }

    public static void main(String[] args){
        MinimizeMaxDistanceToGasStation ms = new MinimizeMaxDistanceToGasStation();
        //System.out.println(ms.minmaxGasDist(new int[]{23,24,36,39,46,56,57,65,84,98} , 1));
        System.out.println(ms.minmaxGasDist(new int[]{10,19,25,27,56,63,70,87,96,97} , 3));

    }
}
