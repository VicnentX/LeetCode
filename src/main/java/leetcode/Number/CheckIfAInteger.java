package leetcode.Number;

public class CheckIfAInteger {
    public boolean isInteger(double x) {
        return (double)((int)x) == x;
    }

    public static void main(String[] args) {
        CheckIfAInteger checkIfAInteger = new CheckIfAInteger();
        System.out.println(checkIfAInteger.isInteger(5.5));
        System.out.println(checkIfAInteger.isInteger(5.6));
        System.out.println(checkIfAInteger.isInteger(5.0));
        System.out.println(checkIfAInteger.isInteger(5));
    }
}
