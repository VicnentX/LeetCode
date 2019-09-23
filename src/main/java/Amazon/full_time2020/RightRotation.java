package Amazon.full_time2020;

/**
 * Right Rotation
 * return 1 if string1 can rotate to stirng2, otherwise return -1
 */

public class RightRotation {
    public static int rightRotate(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0 || word1.length() != word2.length()) {
            return -1;
        }
        String str = word1 + word1;
        return str.contains(word2)? 1 : -1;
    }
}
