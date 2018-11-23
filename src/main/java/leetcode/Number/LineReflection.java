package leetcode.Number;
import java.util.*;

public class LineReflection {
    public boolean isReflected(int[][] points) {
        // Write your code here


        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashSet<String> set = new HashSet<>();

        for (int[] p : points) {
            set.add(p[0] + "," + p[1]);
            min = Math.min(min, p[0]);
            max = Math.max(max, p[0]);
        }

        long sum = max + min;

        for (int[] p : points) {
            if (!set.contains(sum - p[0] + "," + p[1])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        LineReflection lr=new LineReflection();
        System.out.println(lr.isReflected(new int[][]{{3,4},{4,4},{5,2},{1,2}}));
    }
}
