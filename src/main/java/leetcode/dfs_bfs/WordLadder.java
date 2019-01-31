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

    public int ladderLength2(String bw, String ew, List<String> wordList) {
        if (wordList.size() == 0) return 0;

        int size = wordList.get(0).length();
        Set<String> lib = new HashSet<>();
        for (String s : wordList) {
            lib.add(s);
        }

        if (!lib.contains(ew)) return 0;

        Deque<String> queue = new LinkedList<>();
        queue.add(bw);
        int cnt = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                String s = queue.poll();
                for (int j = 0; j < size; ++j) {
                    char[] ch = s.toCharArray();
                    for (int k = 0; k < 26; ++k) {
                        ch[j] = (char) (k + 'a');
                        String str = String.valueOf(ch);
                        if (lib.contains(str)) {
                            if (ew.equals(str)) return cnt + 1;
                            queue.add(str);
                            lib.remove(str);
                        }
                    }
                }
            }
            ++cnt;
        }
        return 0;
    }


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
