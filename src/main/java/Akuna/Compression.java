package Akuna;

/**
 * alaasass -> ala2sas2
 */

public class Compression {
    public String compressedString(String s) {
        int cnt = 1;
        StringBuilder compact = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compact.append(s.charAt(i)).append(cnt == 1 ? "" : cnt);
                cnt = 1;
            } else {
                cnt++;
            }
        }

        return compact.toString();
    }

    public static void main(String[] args) {
        Compression compression = new Compression();
        System.out.println(compression.compressedString("alaasass"));
        System.out.println(compression.compressedString("aaaaa"));
        System.out.println(compression.compressedString("a"));
    }
}
