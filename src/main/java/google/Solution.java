package google;

import java.util.*;
import java.io.*;

public class Solution {




    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        //the cnt of the cases
        String line = in.nextLine();
//        System.out.println("line: " + line);
        int totalCase = Integer.parseInt(line);
        //loop each case
        for(int i = 1 ; i <= totalCase ; ++i){
//            line = in.nextLine();
//            System.out.println("line: " + line);
            int cntOfBuses = Integer.parseInt(in.nextLine());
            String[] ranges = in.nextLine().split(" ");
            int[] rangesOfBus = new int[ranges.length];
            for(int j = 0 ; j < ranges.length ; ++j){
                //System.out.print("range: " + ranges[j]);
                rangesOfBus[j] = Integer.parseInt(ranges[j]);
            }
            int cntOfInterests = Integer.parseInt(in.nextLine());
            //
            System.out.print("Case #" + i + ":");
            for(int k = 0 ; k < cntOfInterests ; ++k){
                int cnt = 0;
                int serOfBus = Integer.parseInt(in.nextLine());
                for(int x = 0 ; x < cntOfBuses ; ++x){
                    if(serOfBus >= rangesOfBus[2*x] && serOfBus <= rangesOfBus[2*x + 1]){
                        ++cnt;
                    }
                }
                System.out.print(" " + cnt);
            }
            System.out.println();
            in.nextLine();//skip blank line
        }
    }
}
