package Wish;
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */

import org.checkerframework.common.value.qual.ArrayLen;

import java.util.Arrays;

public class AlienDictionary269 {
    //visited status
    //-1    : not exit
    //0     : not visited
    //1     : visiting
    //2     : visited
    private final int n = 26;
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[n][n];
        int[] visited = new int[n];
        buildGraph(words , adj , visited);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; ++i){
            if(visited[i] == 0){
                if(!dfs(adj , visited , sb , i)){
                    return "";
                }
            }
        }
        //这个过程中那些孤立的点还是会被排到里面去的 不用在意
        return sb.reverse().toString();
    }
    private void buildGraph(String[] words , boolean[][] adj , int[] visited){
        Arrays.fill(visited , -1);//-1 means not exited
        for(int i = 0 ; i < words.length ; ++i){
            for(char c : words[i].toCharArray()){
                visited[c - 'a'] = 0;
            }
            if(i > 0){
                String s1 = words[i - 1];
                String s2 = words[i];
                for(int j  = 0 ; j < Math.min(s1.length() , s2.length()) ; ++j){
                    char c1 = s1.charAt(j);
                    char c2 = s2.charAt(j);
                    if(c1 != c2){
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
    private boolean dfs(boolean[][] adj , int[] visited , StringBuilder sb , int i){
        visited[i] = 1; //means being visited
        for(int j = 0 ; j < n ; ++j){
            if(adj[i][j] == true){
                if(visited[j] == 1) return false; //means there is a circle here
                if(visited[j] == 0){
                    if(!dfs(adj , visited , sb , j)){
                        return false;
                    }
                }
            }
        }
        visited[i] = 2; //mean visited
        sb.append((char)(i + 'a'));
        return true;
    }

    public static void main(String[] args){
        AlienDictionary269 ad = new AlienDictionary269();
        System.out.println(ad.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println(ad.alienOrder(new String[]{"abcd","bacd"}));
    }
}
