package GaoSheng;

import java.util.*;

public class StrangerSort {
    public class sortObj {
        String ori;
        String real;
        int pos;

        sortObj(String s, int p) {
            this.ori = s;
            this.pos = p;
        }
    }

    public String[] strangeSort(int[] mapping, String[] nums) {
        if (nums == null || nums.length == 0) return new String[0];

        List<sortObj> ls = new ArrayList<>();
        HashMap<Character, Character> map = new HashMap<>(); //wrong -> real
        for (int i : mapping) {
            map.put((char) ('0' + mapping[i]), (char) ('0' + i));
        }

        for (int i = 0; i < nums.length; i++) {
            sortObj obj = new sortObj(nums[i], i);
            StringBuilder sb = new StringBuilder();
            boolean zerostart = true;
            for (char c : nums[i].toCharArray()) {
                char correctc = map.get(c);
                if (zerostart && Character.getNumericValue(correctc) != 0)
                    zerostart = false;
                if (!zerostart)
                    sb.append(correctc);
            }
            obj.real = sb.toString();
            ls.add(obj);
        }
        Collections.sort(ls, new Comparator<sortObj>() {
            public int compare(sortObj a, sortObj b) {
                if (a.real.length() == b.real.length())
                    return a.real.compareTo(b.real);
                return a.real.length() - b.real.length();

            }
        });

        String[] res = new String[ls.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ls.get(i).ori;
        }

        return res;
    }

    public String[] vincentStrangeSort(int[] mapping, String[] nums) {
        Map<Integer, Integer> map = getMap(mapping);
        Arrays.sort(nums, (a,b) -> convertFromStringToStangeDouble(a, map).compareTo(convertFromStringToStangeDouble(b, map)));
        return nums;
    }

    private Map<Integer, Integer> getMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < arr.length ; ++i) {
            map.put(arr[i], i);
        }
        return map;
    }

    private Double convertFromStringToStangeDouble(String s, Map<Integer, Integer> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c: s.toCharArray()) {
            if (c == '.') {
                stringBuilder.append('.');
            } else {
                stringBuilder.append(map.get(Character.getNumericValue(c)));
            }
        }
        return Double.valueOf(stringBuilder.toString());
    }

    public static void main(String[] args) {
        StrangerSort test = new StrangerSort();
        int[] mapping = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] sort = {"000", "113", "1112", "119"};
        String[] re = test.strangeSort(mapping, sort);
        for (String r : re)
            System.out.println(r);
        System.out.println("______________________________");

        String[] ree = test.vincentStrangeSort(mapping, sort);
        for (String r : ree)
            System.out.println(r);


        /**
         * 我的可以handle小数情况 但是刘姐网上下载的并不可以
         */
        String[] sort1 = {"3.45", "113", "3.76", "8.91"};
//        String[] re1 = test.strangeSort(mapping, sort1);
//        for (String r : re1)
//            System.out.println(r);
//        System.out.println("______________________________");

        String[] ree1 = test.vincentStrangeSort(mapping, sort1);
        for (String r : ree1)
            System.out.println(r);
    }
}

