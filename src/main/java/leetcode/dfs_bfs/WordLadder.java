package leetcode.dfs_bfs;

import java.util.*;

public class WordLadder {
    private int ret = 1;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        //HashSet<String> visited = new HashSet<>();
        HashSet<String> head = new HashSet<>();
        for(String k : wordList){
            set.add(k);
        }
        if(!set.contains(endWord)){
            return 0;
        }
        head.add(beginWord);
        while(!head.isEmpty()){
            for(String k : head){
                if(k.equals(endWord)){
                    return ret;
                }
                //set.remove(k);
            }

            //visited.addAll(head);
            HashSet<String> tem = new HashSet<>();
            HashSet<String> readyToDelete = new HashSet<>();
            for(String k : head){
                for(String s : set){
                    if(diff(k , s)){
                        tem.add(s);
                        readyToDelete.add(s);
                    }
                }
            }
            set.removeAll(readyToDelete);
            head = tem;
            ++ret;
        }
        return 0;
    }

    private boolean diff(String a , String b){
        int len = 0;
        for(int i = 0 ; i < a.length() ; ++i){
            if(a.charAt(i) != b.charAt(i)){
                ++len;
                if(len > 1){
                    return false;
                }
            }
        }
        return len == 1? true : false;
    }


    //___________________________________________

    public int ladderLength2(String start, String end, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }
        if (!set.contains(end)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int level = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0 ; i < n ; ++i) {
                String cur = queue.poll();
                if (cur.equals(end)) return level;
                findNeighbors(cur , set , queue);
            }
            ++level;
        }
        return 0;
    }
    private void findNeighbors(String cur , Set<String> set , Queue<String> queue) {
        char[] ch = cur.toCharArray();
        for (char c = 'a' ; c <= 'z' ; ++c) {
            for (int i = 0 ; i < ch.length ; ++i) {
                if (c == ch[i]) continue;
                char oldChar = ch[i];
                ch[i] = c;
                if (set.contains(String.valueOf(ch))) {
                    queue.add(String.valueOf(ch));
                    set.remove(String.valueOf(ch));
                }
                ch[i] = oldChar;
            }
        }
    }

    //_____________________________________________


    public static void main(String[] args){
        WordLadder wl = new WordLadder();
        List<String> input = new ArrayList<>();
        input.add("hot");
        input.add("dot");
        input.add("dog");
        input.add("lot");
        input.add("log");
        input.add("cog");
        System.out.println(wl.ladderLength2("hit" , "cog" , input));
    }
}
