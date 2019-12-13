package google.google2020.google_加油少年_ResidentVO;

/*
Rhyme scheme is a poet's deliberate pattern of lines that rhyme with other lines in a poem. The rhyme scheme, or pattern, can be identified by giving end words that rhyme with each other the same letter. For instance, take the poem 'Twinkle, Twinkle, Little Star', written by Jane Taylor in 1806.

Twinkle, twinkle, little star, [A]
How I wonder what you are!     [A]
Up above the world so high,    [B]
Like a diamond in the sky.     [B]
The rhyme scheme of this poem can be determined by looking at the end word in each line. The first line ends in the word star, and the second line ends in the word are. Because the two words rhyme, they both are given the letter A. A signifies that we have found the first rhyme in the poem.

The third line ends in the word high, and the fourth line ends in sky. These two words don't rhyme with the first two words, star and are, so they get the letter B. So we have a rhyme scheme of AABB.

Given an int n denoting the number of lines in a poem, return all possible rhyme schemes. Output should be in lexicographical order.

Example 1:

Input: n = 1 (possible chars are A)
Output: [A]
Example 2:

Input: n = 2 (possible chars are A, B)
Output: [AA, AB]
Explanation:
AA
AB
BA <- This is not possible as it collide with AB. How?
Look at the pattern AB says first line has different rhyme and second line has different rhyme then first line.
Similarly BA is also shows the same: first line has different rhyme and second line has different rhyme then first line. Hence discard.
Example 3:

Input: n = 3 (possible chars are A, B, C)
Output: [AAA, AAB, ABA, ABB, ABC]
Explanation: We can't have AAC as it collides with AAB (first two are same and last is different in both).
Similarly other BCA/BAC etc we'll discard them because of collide and lexicographical ordering.
Example 4:

Input: n = 4 (possible chars are A, B, C, D)
Output: [AAAA, AAAB, AABA, AABB, AABC, ABAA, ABAB, ABAC, ABBA, ABBB, ABBC, ABCA, ABCB, ABCC, ABCD] - 15
 */

import java.util.ArrayList;
import java.util.List;

public class RyhmeScheme {
    public List<String> solve(int n) {
        List<String> ret = new ArrayList<>();
        dfs(0, 0, n, "", ret);
        return ret;
    }

    //index 表示我取的是第几位
    //i 表示我这一位上面取得什么char
    private void dfs(int index, int maxIndex, int n, String cur, List<String> ret) {
        if (index == n) {
            ret.add(cur);
            return;
        }

        for (int i = 0; i <= maxIndex; ++i) {
            dfs(index + 1, Math.max(maxIndex, i + 1), n,cur + (char)(i + 'A'), ret);
        }
    }

    public static void main(String[] args) {
        RyhmeScheme ryhmeScheme = new RyhmeScheme();
        List<String> ret = ryhmeScheme.solve(3);
        for (String s: ret) {
            System.out.println(s);
        }
        System.out.println(ret.size());
    }
}
