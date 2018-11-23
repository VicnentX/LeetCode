package leetcode.DifferentKindSort;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {

        //--nlogn
        // HashMap<Character , Integer> map = new HashMap<>();
        // for(char c : s.toCharArray()){
        //     map.put(c , map.getOrDefault(c , 0) + 1);
        // }
        // List<Map.Entry<Character , Integer>> list = new ArrayList<>();
        // list.addAll(map.entrySet());
        // Collections.sort(list , (a,b) -> b.getValue() - a.getValue());
        // StringBuilder sb = new StringBuilder();
        // list.forEach(entry -> {
        //     for(int i = 0 ; i < entry.getValue() ; ++i){
        //         sb.append(entry.getKey());
        //     }
        // });
        // return sb.toString();

        //bucket sort
        int[] map = new int[128];
        int max = 0;
        for(char c : s.toCharArray()){
            ++map[c];
            max = Math.max(max , map[c]);
        }
        String[] bucket = new String[max + 1];
        for(int i = 0 ; i < 128 ; ++i){
            String tem = bucket[map[i]];
            if(map[i] > 0){
                bucket[map[i]] = tem == null ? ""+ (char)i : tem + (char)i ;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = max ; i > 0 ; --i){
            if(bucket[i] != null){
                for(char c : bucket[i].toCharArray()){
                    for(int j = 0 ; j < i ; ++j){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();

    }
}
