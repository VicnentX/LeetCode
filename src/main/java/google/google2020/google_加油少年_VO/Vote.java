package google.google2020.google_加油少年_VO;

/*
一场选举，每张选票有两个属性，投给谁和timestamp
第一题是在给定timestamp之前的最高票数获得者名字

第二题是给定timestamp之前，最高k个票数获得者。

第三题是给定一个ranking list 形如[cand5, cand2, cand8], 要求出这个ranking出现的时间。

第一题把时间戳之前的所有选票累加起来，然后用O(N) 找到最大值，
第二题排序O(NlgN) 拿到top k， 或者maintain一个heap, O(Nlgk)，
或者quick select, O(N).
第三题， 一个naive solution是每次有rank list里cand的选票时候进行选票更新排序，
检查是否满足要求的排序，看看大家有没有更好的方法

 */

import java.util.*;

public class Vote {

    public String getNameTop(String[][] list, List<String> target) {
        //sort
        Arrays.sort(list, (a,b) -> a[1].compareTo(b[1]));

        Set<String> set = new TreeSet<>((a,b) -> b.compareTo(a)); // 1. 名字 2. 票数
        Map<String, Integer> map = new HashMap<>();  // 名字 -> 票数
        int i = 0;

        for (String[] pair: list) {
            String name = pair[0];
            String time = pair[1];
            map.put(name, map.getOrDefault(name, 0) + 1);
            //if there is oldKey, remove it
            if (map.get(name) != 1) {
                //这个format是为了补全用的 因为防止9在10前面 相当于 0 + 多少位 + d
                String setOldKey = String.format("%015d", map.get(name) - 1) + " " + name;
                set.remove(setOldKey);
            }
            //add newKey
            String setNewKey = String.format("%015d", map.get(name)) + " " + name;
            set.add(setNewKey);

            //check if requirement met
            if (map.size() >= target.size()) {
                int index = 0;
                for (String nameTimePair: set) {
                    if (index == target.size()) {
                        return time;
                    }
                    String person = nameTimePair.split(" ")[1];
                    if (!person.equals(target.get(index))) {
                        break;
                    }
                    index++;
                }
            }

            ++i;
        }

        return "NONE";
    }

    public static void main(String[] args) {
        Vote vote = new Vote();
        String[][] list = new String[][] {
                {"p1","001"},
                {"p2","003"},
                {"p1","002"},
                {"p2","005"},
                {"p3","007"},
                {"p3","006"},
                {"p2","004"},
                {"p3","008"},
                {"p4","009"},
                {"p3","010"}
        };
        List<String> target = new ArrayList<>(Arrays.asList("p3","p2","p1"));
        //010
        System.out.println(vote.getNameTop(list, target));
    }



}
