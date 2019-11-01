package Freewire;

/*
Given a string of characters S and a single character C,
produce an array (the same length as S)
which contains the shortest distance from each character in S to an occourance of the character C.
For example, given the string "atlassian"
and the character 'a' the resulting array would be [0,1,1,0,1,2,1,0,1].

a t l a s s i a n
[0,1,1,0,1,2,1,0,1].
 */

public class ClosestCharacter {
    public int[] getClosestDistance(String s, char c) {
        //store the result
        if (s == null || s.length() <= 0) {
            System.out.println("Invalid input");
            return new int[] {};
        }
        //ret to store the result
        final int N = s.length();
        int[] ret = new int[N];
        //will loop twice
        //first loop to update ret with the closest c on the left side (inclusive) of i
        //second loop to update ret with the closest c on the right side (inclusive) of i if needed
        int left = Integer.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            if (s.charAt(i) == c) left = i;
            ret[i] = (left == Integer.MAX_VALUE ? left : i - left);
        }
        int right = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; --i) {
            if (s.charAt(i) == c) right = i;
            ret[i] = Math.min(right == Integer.MAX_VALUE ? right : right - i , ret[i]);
        }

        return ret;
    }

    public static void main(String[] args) {
        ClosestCharacter closestCharacter = new ClosestCharacter();
        //a t l a s s i a n
        //[0,1,1,0,1,2,1,0,1].
        for (int dis: closestCharacter.getClosestDistance("atlassian", 'a')) {
            System.out.print(dis + " ");
        }
    }
}
