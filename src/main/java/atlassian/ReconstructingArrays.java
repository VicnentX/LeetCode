package atlassian;

public class ReconstructingArrays {

    int ret = 0;

    public int findNumberOfArrays(int n , int m , int totalCost){

        helper(0 , n , m , totalCost ,0 , -1);
        return ret;
    }

    private void helper(int len , int n , int m , int totalCost ,int cost , int max){
        if(len == n){
            if(cost == totalCost + 1) ++ret;
            return;
        }else{
            for(int i = 1 ; i <= m ; ++i){
                if(i > max){
                    max = i;
                    helper(len + 1 , n , m , totalCost,cost + 1 , max);
                }else{
                    helper(len + 1 , n , m , totalCost ,cost , max);
                }
            }
        }
    }

    //if the input is the array or list , use mem to record the previous case .

    public static void main(String[] args){
        ReconstructingArrays ra = new ReconstructingArrays();
        System.out.println(ra.findNumberOfArrays(4,4,2));
    }
}
