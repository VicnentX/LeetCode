package leetcode.HARD;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
Seen this question in a real interview before?

 */


import java.util.*;
public class WordLadderII126 {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Map<String , Integer> distance = new HashMap<>();
        Map<String , List<String>> neighbor = new HashMap<>();
        List<List<String>> ret = new ArrayList<>();
        if (!set.contains(end)) return ret;
        set.add(start);

        bfs(start , end , set , neighbor , distance);
        dfs(start , end , neighbor , distance , ret , new ArrayList<>());
        return ret;
    }

    private void bfs (String start , String end , Set<String> set ,
                      Map<String , List<String>> neighbor , Map<String , Integer> distance) {
        for (String str : set) {
            neighbor.put(str, new ArrayList<String>());
        }

        distance.put(start , 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        OUT:
        while (!queue.isEmpty()) {
            int n = queue.size();
            boolean foundEnd = false;
            for (int i = 0 ; i < n ; ++i) {
                String cur = queue.poll();
                int curdistance = distance.get(cur);

                List<String> nbs = findNeighbors(cur , set);

                for (String nb : nbs) {
                    neighbor.get(cur).add(nb);
                    if (!distance.containsKey(nb)) {
                        distance.put(nb , distance.get(cur) + 1);
                        if (nb.equals(end)) {
                            foundEnd = true;
                        } else {
                            queue.add(nb);
                        }
                    }
                }

                if (foundEnd == true) {
                    break;
                }
            }
        }
    }
    private List<String> findNeighbors (String s , Set<String> set) {
        char[] cur = s.toCharArray();
        List<String> ret = new ArrayList<>();
        for(char ch = 'a' ; ch < 'z' ; ++ch) {
            for (int i = 0 ; i < cur.length ; ++i) {
                if (ch == cur[i]) continue;
                char oldChar = cur[i];
                cur[i] = ch;
                if (set.contains(String.valueOf(cur))) {
                    ret.add(String.valueOf(cur));
                }
                cur[i] = oldChar;
            }
        }
        return ret;
    }

    private void dfs (String cur , String end ,
                      Map<String , List<String>> neighbor , Map<String , Integer> distance ,
                      List<List<String>> ret , List<String> path) {
        path.add(cur);
        if (cur.equals(end)) {
            ret.add(new ArrayList<>(path));
            return ;
        }

        for (String next : neighbor.get(cur)) {
            if (distance.get(next) == distance.get(cur) + 1) {
                dfs(next , end , neighbor , distance , ret , path);
                path.remove(path.size() - 1);
            }
        }
    }
}
