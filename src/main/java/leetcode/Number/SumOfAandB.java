package leetcode.Number;

public class SumOfAandB {
    private int plus(int a, int b) {
        if(b == 0) {
            return a;
        }

        return plus(a ^ b, (a & b) << 1);
    }
    /*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        return plus(a, b);
    }

    public static void main(String[] args){
        SumOfAandB ab=new SumOfAandB();
        System.out.println(ab.aplusb(17,9));
    }


}
