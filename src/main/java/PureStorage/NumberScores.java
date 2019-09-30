package PureStorage;


/*
+5 for every 7 found
+6 for each pair of consecutive 2s, add +6 for each additional 2
+N2 points for a sequence of length N (N >= 1) where each digit is 1 less than the previous digit.
    for exampe: 9765320 = 1 ^ 2 + 3 ^ 2 + 2 ^ 2 + 1 ^ 2 = 15
+4 if the entire nnumber is a multiple of 3
+3 for each even digit (note that 0 is even)
 */

public class NumberScores {
    public int getNumberScore(int n) {
        String s = String.valueOf(n);
        int score = 0;
        //get +4
        if (n % 3 == 0) {
            score += 4;
        }
        //get +5, +6, +N2, +3
        int sequence = 1;
        for (int i = 0; i < s.length(); ++i) {
            int cur = Integer.parseInt(String.valueOf(s.charAt(i)));
            //check +7
            if (cur == 7) {
                score += 7;
            }
            //check +6
            if (i != 0 && cur == 2 && Integer.parseInt(String.valueOf(s.charAt(i - 1))) == 2) {
                score += 6;
            }
            //check +N2
            if (i != 0) {
                if (cur == Integer.parseInt(String.valueOf(s.charAt(i - 1))) +  1) {
                    sequence++;
                } else {
                    score += (sequence * sequence);
                    sequence = 1;
                }
            }
        }

        score += (sequence * sequence);

        return score;
    }
}
