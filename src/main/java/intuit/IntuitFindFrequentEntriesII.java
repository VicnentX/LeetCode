package intuit;

import java.util.*;

public class IntuitFindFrequentEntriesII {
    public List<List<String>> findFrequentEntries(List<String[]> input){
        List<List<String>> ret = new ArrayList<>();
        HashMap<String , List<Integer>> map = new HashMap<>();
        //O(nlongn)
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
                Deque<Integer> queue = new LinkedList<>();
                Iterator<Integer> queueIterator = map.get(s).iterator();
                while(queueIterator.hasNext()) {
                    int tem = queueIterator.next();
                    if(queue.isEmpty()){
                        queue.add(tem);
                    }else {
                        if(tem - queue.peekFirst() <= 60) {
                            queue.add(tem);
                        }else {
                            if(queue.size() >= 3){
                                List<String> record = new ArrayList<>();
                                record.add(s + ":");
                                while(!queue.isEmpty()){
                                    int time = queue.pollFirst();
                                    int hourminute = time / 60 * 100 + time % 60;
                                    record.add(String.valueOf(hourminute));
                                }
                                ret.add(record);
                                continue OUT;
                            }else{
                                while(queue.size() != 0 && tem - queue.peekFirst() > 60) {
                                    queue.pollFirst();
                                }
                                queue.addLast(tem);
                            }
                        }
                    }
                }
                if(queue.size() >= 3) {
                    List<String> record = new ArrayList<>();
                    record.add(s + ":");
                    while(!queue.isEmpty()){
                        int time = queue.pollFirst();
                        int hourminute = time / 60 * 100 + time % 60;
                        record.add(String.valueOf(hourminute));
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
        IntuitFindFrequentEntriesII ffe = new IntuitFindFrequentEntriesII();
        List<List<String>> ret = ffe.findFrequentEntries(input);
//        for(List<String> ls : ret){
//            for(String s : ls){
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }
        System.out.println(ret);

    }
}

