package leetcode.UnionFind;

/*
Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.

Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.



Example 1:

Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
Example 2:

Input: A = "hello", B = "world", S = "hold"
Output: "hdld"
Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
Example 3:

Input: A = "leetcode", B = "programs", S = "sourcecode"
Output: "aauaaaaada"
Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".


Note:

String A, B and S consist of only lowercase English letters from 'a' - 'z'.
The lengths of string A, B and S are between 1 and 1000.
String A and B are of the same length.
 */

public class LexicographicallySmallestEquivalentString1061 {
    public String smallestEquivalentString(String a, String b, String s) {
        // initiate
        int[] roots = new int[26];
        for (int i = 0; i < 26; ++i) {
            roots[i] = i;
        }
        //
        int n = a.length();
        for (int i = 0; i < n; ++i) {
            int root1 = find(roots, a.charAt(i) - 'a');
            int root2 = find(roots, b.charAt(i) - 'a');
            if (root1 < root2) {
                roots[root2] = root1;
            } else if (root2 < root1) {
                roots[root1] = root2;
            }
        }

        //这边打印下roots的情况 但是注意这边不是最终的情况！！！！
        for (int i : roots) {
            System.out.print(i + " ");
        }
        System.out.println();

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append((char) (find(roots, c - 'a') + 'a'));
        }
        return sb.toString();
    }

    private int find(int[] roots, int id) {
          // method1 to implement
//          while (roots[id] != id) {
//              roots[id] = roots[roots[id]];
//              id = roots[id];
//          }
//          return id;

          //这两种方法还是有很大不同的
            //感觉第二种远比第一种好，因为path上面每个点都和最上面的root链接在了一起。

             //method 2 to implement

            if (roots[id] == id) return id;
            roots[id] = find(roots, roots[id]);
            return roots[id];
    }
}
