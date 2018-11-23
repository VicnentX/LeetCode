package leetcode.Number;

public class TrappingRainWater {

    public int trap(int bar[]){
        int leftmax = 0;
        int rightmax = 0;
        int sum = 0;
        int i = 0;
        int j = bar.length - 1;
        while(i < j){
            leftmax = Math.max(leftmax , bar[i]);
            rightmax = Math.max(rightmax , bar[j]);
            if(leftmax < rightmax){
                sum += (leftmax - bar[i]);
                ++i;
            }else{
                sum += (rightmax - bar[j]);
                --j;
            }
        }
        return sum;
    }

    public static void main(String[] args){
        TrappingRainWater trw = new TrappingRainWater();
        System.out.println(trw.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }

}
