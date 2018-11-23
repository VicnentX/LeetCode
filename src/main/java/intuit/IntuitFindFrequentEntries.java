package intuit;

import java.util.*;

public class IntuitFindFrequentEntries {
    public List<List<String>> findFrequentEntries(List<String[]> input){
        List<List<String>> ret = new ArrayList<>();
        HashMap<String , List<Integer>> map = new HashMap<>();
        input.sort((a,b) -> a[0].equals(b[0])?  a[1].length() == b[1].length()? a[1].compareTo(b[1]) : a[1].length() - b[1].length()  : a[0].compareTo(b[0]));
        for(String[] in : input){
            if(!map.containsKey(in[0])) map.put(in[0] , new ArrayList<Integer>());
            int tem = Integer.parseInt(in[1]);
            map.get(in[0]).add(tem / 100 * 60 + tem % 100);//convert hours to minutes
        }

        OUT:
        for(String s : map.keySet()){
            if(map.get(s).size() < 3){
                continue;
            }else{
                int st = 0;
                int ed = 0;
                while(ed < map.get(s).size()){
                    if(map.get(s).get(ed) - map.get(s).get(st) <= 60){
                        ++ed;
                    }else{
                        if(ed - st >= 3){
                            List<String> record = new ArrayList<>();
                            record.add(s);
                            for(int i = st ; i < ed ; ++i){
                                int time = map.get(s).get(i);
                                int hourminute = time / 60 * 100 + time % 60;
                                record.add(Integer.toString(hourminute));
                            }
                            ret.add(record);
                            continue OUT;
                        }
                        ++st;
                    }
                }
                if(ed - st >= 3){
                    List<String> record = new ArrayList<>();
                    record.add(s);
                    for(int i = st ; i < ed ; ++i){
                        int time = map.get(s).get(i);
                        int hourminute = time / 60 * 100 + time % 60;
                        record.add(Integer.toString(hourminute));
                    }
                    ret.add(record);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args){
        List<String[]> input = new ArrayList<>();
        input.add(new String[]{"paul" , "1355"});
        input.add(new String[]{"jennifer" , "1910"});
        input.add(new String[]{"marcel" , "830"});
        input.add(new String[]{"paul" , "1315"});
        input.add(new String[]{"marcel" , "835"});
        input.add(new String[]{"paul" , "1405"});
        input.add(new String[]{"paul" , "1630"});
        input.add(new String[]{"marcel" , "855"});
        input.add(new String[]{"marcel" , "930"});
        input.add(new String[]{"marcel" , "915"});
        input.add(new String[]{"jennifer" , "1335"});
        input.add(new String[]{"jennifer" , "730"});
        input.add(new String[]{"marcel" , "1630"});
        IntuitFindFrequentEntries ffe = new IntuitFindFrequentEntries();
        List<List<String>> ret = ffe.findFrequentEntries(input);
        for(List<String> ls : ret){
            for(String s : ls){
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
}
