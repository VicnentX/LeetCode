package leetcode.Number;
import java.util.*;

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {

        List<String> ret=new ArrayList<String>();

        for(int i=0;i<nums.length;i++){
            int str=nums[i];
            while(i+1<nums.length   &&  nums[i+1]-nums[i]==1){
                i++;
            }
            if(str==nums[i]){
                ret.add(str+"");
            }else{
                ret.add(str+"->"+nums[i]);
            }
        }

        return ret;
    }


    public static void main(String[] args){
        SummaryRanges sr=new SummaryRanges();
        System.out.println(sr.summaryRanges(new int[]{3,4,5,6,8,9,22,333,4555}));
    }
}
