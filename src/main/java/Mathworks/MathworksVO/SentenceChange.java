package Mathworks.MathworksVO;

/*
输入一个字符串(sentence)，
把其中的字母按如下规则转变:
[A - M] to ‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍[N - Z]; [N - Z] to [A - M]，
大小写需要保持不变。

 */

public class SentenceChange {
    public String convertSentence(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            if (c >= 'A' && c <= 'M' || c >= 'a' && c <= 'm') {
                sb.append((char)(c + 13));
            } else {
                sb.append((char)(c - 13));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SentenceChange sentenceChange = new SentenceChange();
        System.out.println(sentenceChange.convertSentence("aAMnNZz"));
    }
}
