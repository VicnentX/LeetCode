package leetcode.dfs_and_memo;


/*

You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).

Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.

Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.



Example 1:

Input: word = "CAKE"
Output: 3
Explanation:
Using two fingers, one optimal way to type "CAKE" is:
Finger 1 on letter 'C' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
Finger 2 on letter 'K' -> cost = 0
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
Total distance = 3
Example 2:

Input: word = "HAPPY"
Output: 6
Explanation:
Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6
Example 3:

Input: word = "NEW"
Output: 3
Example 4:

Input: word = "YEAR"
Output: 7


Constraints:

2 <= word.length <= 300
Each word[i] is an English uppercase letter.
 */
public class MinimumDistancetoTypeaWordUsingTwoFingers1320 {

    //dfs without memo
    private int ret = Integer.MAX_VALUE;
    public int minimumDistance(String word) {
        //dfs
        char left = ' ';
        char right = ' ';
        dfs(left, right, 0, word, 0);
        return ret;
    }

    private void dfs(char left, char right, int i, String word, int sum) {
        if (i == word.length()) {
            ret = Math.min(ret, sum);
            return;
        }


        //move left
        if (left == ' ') {
            dfs(word.charAt(i), right, i + 1, word, sum);
        } else {
            int x = Math.abs((word.charAt(i) - 'A') / 6 - (left - 'A') / 6);
            int y = Math.abs((word.charAt(i) - 'A') % 6 - (left - 'A') % 6);
            dfs(word.charAt(i), right, i + 1, word, sum + x + y);
        }
        //move right
        if (right == ' ') {
            dfs(left, word.charAt(i), i + 1, word, sum);
        } else {
            int x = Math.abs((word.charAt(i) - 'A') / 6 - (right - 'A') / 6);
            int y = Math.abs((word.charAt(i) - 'A') % 6 - (right - 'A') % 6);
            dfs(left, word.charAt(i), i + 1, word, sum + x + y);
        }
    }


    //dfs + memo
    /*
    We have two choices - type the next character using either left or right index finger. So, we run DFS to find the minimum cost. Without memoisation, the runtime complexity is O(2 ^ n).

The memoisation dimensions here are quite intuitive - both fingers' locations and position in the input string. We have 27 locations for each finger, including the initial 'hovering' state.
     */


    int[][][] memo = new int[27][27][300];

    public int minimumDistanceMemo(String word) {
        return minDist(word, 0, null, null);
    }

    private int minDist(String word, int pos, Character c1, Character c2) {
        if (pos >= word.length())
            return 0;
        int idx1 = c1 == null ? 0 : c1 - 'A' + 1;
        int idx2 = c2 == null ? 0 : c2 - 'A' + 1;
        if (memo[idx1][idx2][pos] == 0) {
            memo[idx1][idx2][pos] = Math.min(getDist(c1,word.charAt(pos)) + minDist(word,pos+1,word.charAt(pos),c2),
                    getDist(c2,word.charAt(pos)) + minDist(word,pos+1,c1,word.charAt(pos)));
        }
        return memo[idx1][idx2][pos];
    }

    private int getDist(Character c1, Character c2) {
        if (c1 == null || c2 == null) return 0;
        int d1 = c1 - 'A', d2 = c2 - 'A';
        int x1 = d1 / 6, y1 = d1 % 6;
        int x2 = d2 / 6, y2 = d2 % 6;
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
