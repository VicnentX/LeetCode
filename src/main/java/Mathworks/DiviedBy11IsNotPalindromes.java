package Mathworks;

/*
How many four digit numbers divisible by 11 are not palindromes?
 */

public class DiviedBy11IsNotPalindromes {
    public int countNotPalindroms() {
        int cnt = 0;
        for (int i = 1000; i <= 9999; ++i) {
            if (i % 11 == 0 && !isPalindromes(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isPalindromes(int num) {
        String s = String.valueOf(num);
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        DiviedBy11IsNotPalindromes diviedBy11IsNotPalindromes = new DiviedBy11IsNotPalindromes();
        System.out.println(diviedBy11IsNotPalindromes.countNotPalindroms());
    }
}
