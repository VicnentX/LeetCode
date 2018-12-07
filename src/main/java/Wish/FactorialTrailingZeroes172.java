package Wish;

public class FactorialTrailingZeroes172 {
    public int FTZ(int n){
        //Since 10 = 2*5, and there are always more 2's than 5's, we only need to count the number of 5 factor in n!
        return n == 0 ? 0 : n / 5 + FTZ(n / 5);
    }
}
