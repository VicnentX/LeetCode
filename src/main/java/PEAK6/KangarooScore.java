package PEAK6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class KangarooScore {

    private Map<String, String[]> synonymsMap;
    private Map<String, String[]> antonymsMap;
    private Map<String, Integer> synonCntMap;
    private Map<String, Integer> antonCntMap;

    public int findKangarooScore(List<String> words, List<String> wordsToSynonyms, List<String> wordsToAntonyms) {

        synonymsMap = new HashMap<>();
        antonymsMap = new HashMap<>();
        synonCntMap = new HashMap<>();
        antonCntMap = new HashMap<>();

        int cnt = 0;

        for (String wTos : wordsToSynonyms) {
        String key = wTos.split(":")[0].trim().toLowerCase();
        String[] values = wTos.split(":")[1].split(",");
        synonymsMap.put(key, values);
    }

        for (String wToa : wordsToAntonyms) {
        String key = wToa.split(":")[0].trim().toLowerCase();
        String[] values = wToa.split(":")[1].split(",");
        antonymsMap.put(key, values);
    }

        for (String word: words) {
            word = word.toLowerCase();
        if (synonymsMap.containsKey(word)) {
            for (String value: synonymsMap.get(word)) {
                value = value.trim().toLowerCase();
                if (isJoeyWord(word, value)) {
                    synonCntMap.put(value, synonCntMap.getOrDefault(value, 0) + 1);
                }
            }
        }

        if (antonymsMap.containsKey(word)) {
            for (String value: antonymsMap.get(word)) {
                value = value.trim().toLowerCase();
                if (isJoeyWord(word, value)) {
                    antonCntMap.put(value, antonCntMap.getOrDefault(value, 0) + 1);
                }
            }
        }
    }

        for (int num: synonCntMap.values()) {
        cnt += num + num * (num - 1) / 2;
    }

        for (int num: antonCntMap.values()) {
        cnt -= num + num * (num - 1) / 2;
    }

        return cnt;
}

    private boolean isJoeyWord(String word, String value) {
        int i = 0;
        int j = 0;
        int start = -1;

        while (i < word.length() && j < value.length()) {
            if (word.charAt(i) == value.charAt(j)) {
                if (j == 0)  start = i;
                ++i;
                ++j;
            } else {
                ++i;
            }
        }

        if (j == value.length()
                && word.lastIndexOf(String.valueOf(value.charAt(value.length() - 1))) - start + 1 > value.length()) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        KangarooScore kangarooScore = new KangarooScore();

        //5
        System.out.println(kangarooScore.findKangarooScore(
                Arrays.asList("illuminated","animosity","deoxyribonucleic","container","lit","amity","encourage","lighted"),
                Arrays.asList("encourage:urge,boost,inspire","container:tin,can,bag,bottle","lighted:lit","illuminated:lit"),
                Arrays.asList("encourage:discourage","animosity:amity,like","lighted:dark")));

        //2
        System.out.println(kangarooScore.findKangarooScore(
                Arrays.asList("aStouND","DeSK","AmicABLe","blossOM","hEllO","AnIMOsitY","BARren","cUrTaiL","DePArTed","ScioN","gOOdBYE"),
                Arrays.asList("GOOdbye:adIeu,Adios,CiAO,paRTINg","ANIMOSItY:haTe","BaRren:bARE","sCioN:SoN,HEir,bRaNCH","curtaIL:cUt"),
                Arrays.asList("animoSiTY:aMIty","HeLLO:gOOdbyE")));
    }
}
