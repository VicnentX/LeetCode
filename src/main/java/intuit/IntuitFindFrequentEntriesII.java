package intuit;

import java.util.*;

public class IntuitFindFrequentEntriesII {
    public void findFrequentEntries(List<List<String>> input){
        List<String> ret = new ArrayList<>();
        HashMap<String , List<Integer>> map = new HashMap<>();
        //O(nlongn)
        Collections.sort(input , (a , b) -> a.get(0).equals(b.get(0)) ? Integer.valueOf(a.get(1)) - Integer.valueOf(b.get(1)) : a.get(0).compareTo(b.get(0)));
//        System.out.println(input);
//        System.out.println("_________");

        for(List<String> in : input){
            if(!map.containsKey(in.get(0))) map.put(in.get(0) , new ArrayList<Integer>());
            int tem = Integer.parseInt(in.get(1));
            map.get(in.get(0)).add(tem / 100 * 60 + tem % 100);//convert hours to minutes
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
                                StringBuilder record = new StringBuilder();
                                record.append(s + ":");
                                while(!queue.isEmpty()){
                                    int time = queue.pollFirst();
                                    int hourminute = time / 60 * 100 + time % 60;
                                    record.append(" ").append(hourminute);
                                }
                                ret.add(record.toString());
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
                    StringBuilder record = new StringBuilder();
                    record.append(s + ":");
                    while(!queue.isEmpty()){
                        int time = queue.pollFirst();
                        int hourminute = time / 60 * 100 + time % 60;
                        record.append(" ").append(hourminute);
                    }
                    ret.add(record.toString());
                }
            }
        }
        if (ret.isEmpty()) {
            System.out.println("None");
        } else {
            for (String element : ret) {
                System.out.println(element);
            }
        }

    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("paul", "1355"));
        input.add(Arrays.asList("jennifer", "1910"));
        input.add(Arrays.asList("marcel", "830"));
        input.add(Arrays.asList("paul", "1315"));
        input.add(Arrays.asList("marcel", "835"));
        input.add(Arrays.asList("paul", "1405"));
        input.add(Arrays.asList("paul", "1630"));
        input.add(Arrays.asList("marcel", "855"));
        input.add(Arrays.asList("marcel", "930"));
        input.add(Arrays.asList("marcel", "915"));
        input.add(Arrays.asList("jennifer", "1335"));
        input.add(Arrays.asList("jennifer", "730"));
        input.add(Arrays.asList("marcel", "1630"));
        IntuitFindFrequentEntriesII ffe = new IntuitFindFrequentEntriesII();
//        List<List<String>> ret = ffe.findFrequentEntries(input);
////        for(List<String> ls : ret){
////            for(String s : ls){
////                System.out.print(s + " ");
////            }
////            System.out.println();
////        }
//        System.out.println(ret);
//
//    }
        ffe.findFrequentEntries(input);
    }
}

