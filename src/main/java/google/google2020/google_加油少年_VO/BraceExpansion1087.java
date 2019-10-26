package google.google2020.google_加油少年_VO;

/*
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.



Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]


Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion1087 {

    public String[] expand(String s) {
        s = s.replaceAll("\\{", " ").replaceAll("}", " ");
        String[] tokens = s.split(" ");
        List<List<String>> source = new ArrayList<>();
        for (String token: tokens) {
            if (token == null || token.length() == 0) continue;
            String[] letters = token.split(",");
            List<String> temp = new ArrayList<>();
            for (String letter: letters) {
                temp.add(letter.trim());
            }
            Collections.sort(temp);
            source.add(new ArrayList<>(temp));
        }
        List<String> ret = new ArrayList<>();
        dfs(0, source.size(), source, ret, "");
        return ret.toArray(new String[source.size()]);
    }

    private void dfs(int i, int n, List<List<String>> source, List<String> ret, String path) {
        if (i == n) {
            ret.add(path);
            return;
        }

        for (String letter: source.get(i)) {
            dfs(i + 1, n, source, ret, path + letter);
        }
    }

    public static void main(String[] args) {
        BraceExpansion1087 braceExpansion1087 = new BraceExpansion1087();
        //4
        for (String list: braceExpansion1087.expand("{b,a}c{e,d}f")) {
            System.out.println(list);
        }
        System.out.println();
        //6
        for (String list: braceExpansion1087.expand("_{a,c,b}{1,2}")) {
            System.out.println(list);
        }
        System.out.println();
    }

}
