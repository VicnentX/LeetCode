package intuit;

import java.util.*;
public class IntuitFindFrequentEntriesIII {
    public void printFre (List<List<String>> input) {

        Collections.sort(input , (a , b) -> a.get(0).equals(b.get(0)) ? Integer.valueOf(a.get(1)) - Integer.valueOf(b.get(1)) : a.get(0).compareTo(b.get(0)));
        System.out.println(input);
        String cur = "";
        List<Integer> list = new ArrayList<>();


        OUT:
        for (int i = 0 ; i < input.size() ; ++i) {
            if (cur.equals("")) {
                cur = input.get(i).get(0);
            }
            if (input.get(i).get(0).equals(cur)) {
                int tem = Integer.parseInt(input.get(i).get(1));
                list.add(tem / 100 * 60 + tem % 100);//convert hours to minutes
            } else {
                if (list.size() < 3) {
                    cur = "";
                    --i;
                } else {
                    int start = 0;
                    int end = 0;

                    while(end < list.size()) {
                        if (list.get(end) - list.get(start) <= 60) {
                            ++end;
                        } else {
                            if (end - start >= 3) {
                                StringBuilder ret = new StringBuilder();
                                ret.append(cur).append(":");
                                for (int j = start ; j < end ; ++j) {
                                    ret.append(" ").append(list.get(j));
                                }
                                System.out.println(ret.toString());
                                --i;
                                cur = "";
                                continue OUT;
                            }
                            ++start;
                        }
                    }


                    if (end - start >= 3) {
                        StringBuilder ret = new StringBuilder();
                        ret.append(cur).append(":");
                        for (int j = start ; j < end ; ++j) {
                            ret.append(" ").append(list.get(j));
                        }
                        System.out.println(ret.toString());
                    }
                    cur = "";
                    --i;
                }
            }
        }
    }

    public static void main (String[] args) {
        /*
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
         */

        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("paul" , "1355"));
        input.add(Arrays.asList("jennifer" , "1910"));
        input.add(Arrays.asList("marcel" , "830"));
        input.add(Arrays.asList("paul" , "1315"));
        input.add(Arrays.asList("marcel" , "835"));
        input.add(Arrays.asList("paul" , "1405"));
        input.add(Arrays.asList("paul" , "1630"));
        input.add(Arrays.asList("marcel" , "855"));
        input.add(Arrays.asList("marcel" , "930"));
        input.add(Arrays.asList("marcel" , "915"));
        input.add(Arrays.asList("jennifer" , "1335"));
        input.add(Arrays.asList("jennifer" , "730"));
        input.add(Arrays.asList("marcel" , "1630"));

        IntuitFindFrequentEntriesIII iff = new IntuitFindFrequentEntriesIII();
        iff.printFre(input);
    }
}
