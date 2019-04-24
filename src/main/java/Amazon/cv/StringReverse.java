package Amazon.cv;

public class StringReverse {
    public String reverseWords(String s) {
        String[] parts = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i = parts.length - 1 ; i >= 0 ; --i) {
            sb.append(parts[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static void main (String[] args) {
        StringReverse sr = new StringReverse();
        System.out.println(sr.reverseWords("  hello world!    "));
    }
}
