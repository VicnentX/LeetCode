package bloomreach.bloomreach_加油少年_VO;

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

import java.util.*;

/**
 * There may be multiple valid order of letters, return any one of them is fine.
 *
 * 先建立一个ininDegreeMap的map
 *
 *
 * use ininDegreeMap map to track next-level letters gradually
 */

public class AlienDictionary269 {
    public String alienOrder(String[] words) {
        // keep the char and all his next possible chars
        Map<Character, Set<Character>> map=new HashMap<>();
        // map of char and his in degree
        Map<Character, Integer> inDegreeMap=new HashMap<>();
        String result="";
        if(words==null || words.length==0) return result;
        //find all possible key in the inDegreeMap
        for(String s: words){
            for(char c: s.toCharArray()){
                inDegreeMap.put(c,0);
            }
        }
        //fill the map
        for(int i=0; i<words.length-1; i++){
            String cur=words[i];
            String next=words[i+1];
            int length=Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char curChar=cur.charAt(j);
                char nextChar=next.charAt(j);
                if(curChar!=nextChar){
                    Set<Character> set=new HashSet<>();
                    if(map.containsKey(curChar)) set=map.get(curChar);
                    if(!set.contains(nextChar)){
                        set.add(nextChar);
                        map.put(curChar, set);
                        inDegreeMap.put(nextChar, inDegreeMap.get(nextChar)+1);
                    }
                    break;
                }
            }
        }
        Queue<Character> queue =new LinkedList<>();
        for(char c: inDegreeMap.keySet()){
            if(inDegreeMap.get(c)==0) queue.add(c);
        }
        while(!queue.isEmpty()){
            char c=queue.remove();
            result += c;
            if(map.containsKey(c)){
                for(char nextChar: map.get(c)){
                    inDegreeMap.put(nextChar,inDegreeMap.get(nextChar) - 1);
                    if(inDegreeMap.get(nextChar)==0) queue.add(nextChar);
                }
            }
        }
        // means there is some loop
        if(result.length()!=inDegreeMap.size()) return "";
        return result;
    }
}
