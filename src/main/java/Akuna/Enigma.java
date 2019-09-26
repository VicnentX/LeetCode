package Akuna;

/**
 * the rest of rotor should be set to a number which is coprime with setting of rotor 1
 * first input is a positive integer, rotorCount, the number of rotors used in the machine
 * second input is a positive integer, minRotorValue, the minimum number that can be set on a rotor
 * third input is a positive integer, maxRotorValue, the maximum number that can be set on a rotor
 */

public class Enigma {
    public int calculateTotalRotorConfiguration(int rotorCnt, int minRotorValue, int maxRotorValue) {
        int ret = 0;

        for (int firstRotor = minRotorValue; firstRotor <= maxRotorValue; ++firstRotor) {
            int remain = maxRotorValue - minRotorValue + 1;
            for (int i = minRotorValue; i <= maxRotorValue; ++i) {
                if (gcd(i, firstRotor) != 1) remain--;
            }
            ret += Math.pow(remain, rotorCnt - 1);
        }

        return ret;
    }

    private int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);

    }

    public static void main(String[] args) {
        Enigma enigma = new Enigma();
        System.out.println(enigma.calculateTotalRotorConfiguration(2,1,3));
    }
}
