package google.google2020.google_加油少年_VO;


/*
要求有一个 color 的 set
color = {R, G, B, Y}
例子: answer "RGGB"
guess: RYBY

return "AB"

answer "RRRB"
guess: "RRRB"
return "AAAA"


没有怎么优化答案，配合 dict遍历两次，复杂度没增加也就无所谓了
edge case：输入的 guess 含有不在 color set 里面的字母，个人是直接 raise exception 了

follow up 是根据 return 值在最少次数内得到 answer，来不及code，大概就说了一下思路
每次guess 都可以去掉一些完全不符合的错误答案这样，有点像 guess word 的做法，只不过这里的 return 值对位置的对应关系更弱
细节的实现来不及细想，给足够时间的话感觉还是可以做出来的


leetcode 843


 */

import java.util.*;

public class NewBullAndCow {

    public String getABs(String answer, String guess) {
        final int N = answer.length();
        Map<Character, Integer> charWrongPosCnt = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //first for loop to get cnt of A
        for (int i = 0; i < N; ++i) {
            if (answer.charAt(i) == guess.charAt(i)) {
                sb.append('A');
            } else {
                charWrongPosCnt.put(answer.charAt(i), charWrongPosCnt.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }
        //second for loop to get cnt of B
        for (int i = 0; i < N; ++i) {
            if (answer.charAt(i) != guess.charAt(i)
                    && charWrongPosCnt.containsKey(guess.charAt(i))
                    && charWrongPosCnt.get(guess.charAt(i)) > 0) {
                sb.append('B');
                charWrongPosCnt.put(guess.charAt(i), charWrongPosCnt.get(guess.charAt(i)) - 1);
            }
        }

        return sb.toString();
    }

    /**
     * 假设我猜了第一次，然后后面的次数就是就是通过这次之后推演出来的，
     * 并且后面我拿candidate与firstTry或者别的比较是不用算做尝试的次数的
     * 只有和answer比较才算是次数
     */

    Set<Character> colors = new HashSet<>(Arrays.asList('r','g','b','y'));
    String ANSWER = "rggbgbyg";

    public int getMinTry(String firstTry) {
        String ret = getABs(ANSWER,firstTry);
        Set<String> set = new HashSet<>();
        int cnt = 1;
        if (ret.equals("AAAA")) {
            return cnt;
        }
        intializeSet(0, firstTry.length(), set, new StringBuilder(), firstTry, ret);

        while (set.size() > 1) {
            cnt++;
            Set<String> nextSet = new HashSet<>();
            //choose random will speed the process
            String first = set.iterator().next();
            ret = getABs(ANSWER, first);
            if (ret.equals("AAAA")) {
                return cnt;
            }
            for (String candidate: set) {
                if (getABs(first, candidate).equals(ret)) {
                    nextSet.add(candidate);
                }
            }
            set = nextSet;
        }

        return cnt;
    }

    private void intializeSet(int i, final int N, Set<String> set, StringBuilder sb, String comp, String ret) {
        if (i == N) {
            if (getABs(comp, sb.toString()).equals(ret)) {
                set.add(sb.toString());
            }
            return;
        }

        for (char c: colors) {
            sb.append(c);
            intializeSet(i + 1, N, set, sb, comp, ret);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    public static void main(String[] args) {
        NewBullAndCow newBullAndCow = new NewBullAndCow();
        System.out.println(newBullAndCow.getABs("rggb", "gggr"));

        //try 几次
        System.out.println(newBullAndCow.getMinTry("rybgrggb"));
    }



}
