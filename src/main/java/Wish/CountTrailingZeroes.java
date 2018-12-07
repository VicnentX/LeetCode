package Wish;

public class CountTrailingZeroes {
    public int CTZ(int num){
        int n = String.valueOf(num).length();
        int start = 1;
        int end = n - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            double divisor = Math.pow(10 , n - 1 - mid + 1);
            if(num % divisor == 0){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(num % Math.pow(10 , n - 1 - start + 1) == 0){
            return n - 1 - start + 1;
        }else if(num % Math.pow(10 , n - 1 - end + 1) == 0){
            return n - 1 - end + 1;
        }else{
            return 0;
        }
    }

    public static void main(String[] args){
        CountTrailingZeroes ct = new CountTrailingZeroes();
        System.out.println(ct.CTZ(24000500));
    }
}
