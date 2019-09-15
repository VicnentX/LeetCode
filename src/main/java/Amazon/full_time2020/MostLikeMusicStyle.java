package Amazon.full_time2020;

import java.util.*;

/**
 * 给你两个map，第一个map是某个用户爱看什么专辑，结构是{"Tom": ["booka", "bookb"]; "Jack": ["booka", "bookc", "book‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‌d"]}；第二个map的结构是这个曲风下都有什么专辑，结构式{"happy": ["booka", "bookd"], "sad":["bookb", "bookc"]}，
 * 需要返回这个用户爱听什么曲风的专辑，说白了就是得到他听得专辑的曲风数一下，返回最多的曲风，{"Tom": ["happy", "sad"]; "Jack": ["happy"]}，因为存在票数相同的情况所以是一个list。补充一点，可能存在某个专辑没有曲风的情况，需要ignore掉这个专辑。
 */

public class MostLikeMusicStyle {
    public Map<String, List<String>> getFaroriteMap(Map<String, List<String>> nameBookMap, Map<String, List<String>> typeBookMap) {
        Map<String, List<String>> nameListTypeMap = new HashMap<>();

        if (nameBookMap.size() == 0) {
            return nameListTypeMap;
        }

        if (typeBookMap.size() == 0) {
            for (String name: nameBookMap.keySet()) {
                nameListTypeMap.put(name, new ArrayList<>());
            }
            return nameListTypeMap;
        }

        //get the map of (book, type)
        Map<String, String> bookTypeMap = new HashMap<>();
        for (String type: typeBookMap.keySet()) {
            List<String> books = typeBookMap.get(type);
            for (String book: books) {
                bookTypeMap.putIfAbsent(book, type);
            }
        }

        //calculate
        for (String name: nameBookMap.keySet()) {
            Map<String, Integer> typeCountMap = new HashMap<>();
            List<String> books = nameBookMap.get(name);
            int max = 0;
            for (String book: books) {
                if (bookTypeMap.containsKey(book)) {
                    String type = bookTypeMap.get(book);
                    typeCountMap.put(type, typeCountMap.getOrDefault(type, 0) + 1);
                    int typeCnt = typeCountMap.get(type);
                    if (typeCnt > max) {
                        nameListTypeMap.put(name, new ArrayList<>());
                        nameListTypeMap.get(name).add(type);
                        max = typeCnt;
                    }
                    else if (typeCnt == max) {
                        nameListTypeMap.get(name).add(type);
                    }
                }
            }
        }

        return nameListTypeMap;
    }

    public static void main(String[] args) {
        MostLikeMusicStyle mostLikeMusicStyle = new MostLikeMusicStyle();
        Map<String, List<String>> ret = mostLikeMusicStyle.getFaroriteMap(
                new HashMap<String, List<String>>() {{
                    put("tom", Arrays.asList("booka", "bookb"));
                    put("jack", Arrays.asList("booka", "bookc", "bookd"));
                }},
                new HashMap<String, List<String>>() {{
                    put("happy", Arrays.asList("booka", "bookd"));
                    put("sad", Arrays.asList("bookb", "bookc"));
                }}
        );
        System.out.println(ret);
    }
}
