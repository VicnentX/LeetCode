package DRW;

/**
 * text.contains("[a-zA-Z]+"
 * 切割句子 看最长的sentence的words数量 ， 有letter才能算word
 */

public class Solution {
    public int solution(String s) {
        int maxWordsCnt = 0;
        if(s == null || s.length() == 0) return maxWordsCnt;
        String[] sentences = s.split("\\.|\\?|!");
//        for (String sentence: sentences) {
//            System.out.println(sentence);
//        }
//        System.out.println("length" + sentences.length);
        for (String sentence: sentences) {
            if (!sentence.trim().isEmpty()) {
                int tempWordsCnt = 0;
                String[] words = sentence.trim().split("\\s+");
                for (String word: words) {
                    if (word.matches(".*[a-zA-Z].*")) {
                        ++tempWordsCnt;
                    }
                }
                maxWordsCnt = Math.max(maxWordsCnt, tempWordsCnt);
            }
        }
        return maxWordsCnt;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.solution("   ,     ,   ?    .   ...?,");
        System.out.println(ret);
    }
}
