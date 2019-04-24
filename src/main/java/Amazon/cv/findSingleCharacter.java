package Amazon.cv;

public class findSingleCharacter {
    public int findSingleIndex (String s) {
        int n = s.length();
        for (int i = 0 ; i < n - 1 ; i = i + 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                return i;
            }
        }
        return n - 1;
    }

    public static void main (String[] args) {
        findSingleCharacter fs = new findSingleCharacter();
        System.out.println(fs.findSingleIndex("aabbccd"));
        System.out.println(fs.findSingleIndex("aabbcdd"));
    }
}
