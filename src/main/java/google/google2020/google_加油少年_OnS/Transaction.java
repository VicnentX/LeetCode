package google.google2020.google_加油少年_OnS;

/*
给一个旅行团的bill，问这些人之间如何互相最少次的转账，
可以让大家平均分所有的钱。{a:30, b:50, c:20, d:0} 如果让每个人都平均付25元
 */

/*
分钱，一堆人一起出去玩，每个人都付了一些钱，然后让你实现一个算法，
optimize所有的给钱方式，最后使得人与人之前的transaction次数总和最少，
要求输出这些所有的transaction，不允许用dfs这种brute force，
leetcode原题，最后给的解法是按个人整合之后排序，首尾双指针往中间处理，
queue也提了，说时间复杂度差不多，面试官认可，就让我写了，
其实个人觉得这个算法不太对，至少我不能证明是对的，
这一轮写之前跟面试官确认了函数的定义，写的中途面试官不断让我改变函数的定义，
还跟我说没事，你的code大部分都可以保留，也不能说他黑我吧，态度还是挺nice的，
但我估计我那个不太好的feedback是这个人给的。LC465
 */

import java.util.*;

public class Transaction {
    //assume all the amount of money and average is int
    public List<String> getTransactionHistory(Object[][] personMoneyPair) {
        final int N = personMoneyPair.length;
        //ret
        List<String> ret = new ArrayList<>();
        //key is the amount of money , value is the name of the owner
        Map<Integer, List<String>> map = new HashMap<>();
        //get average
        int sum = 0;
        for (Object[] pair: personMoneyPair) {
            sum += (int)pair[1];
        }
        int average = sum / N;
        //fill map
        for (Object[] pair: personMoneyPair) {
            String name = (String)pair[0];
            int amount = (int)pair[1];
            if (map.containsKey(average - amount)) {
                String removedName = map.get(average - amount).remove(map.get(average - amount).size() - 1);
                ret.add(removedName + "->" + name + ":::" + (average - amount) / 2);
                if (map.get(average - amount).size() == 0) {
                    map.remove(average - amount);
                }
            } else {
                if (!map.containsKey(amount)) {
                    map.put(amount, new ArrayList<>());
                }
                map.get(amount).add(name);
            }
        }
        //use a list to store the remain
        //list is pair of name and amount
        List<Object[]> list = new ArrayList<>();
        for (int amount: map.keySet()) {
            for (String name: map.get(amount)) {
                list.add(new Object[] {name, amount});
            }
        }

        //sort by amount
        Collections.sort(list, (a,b) -> (int)b[1] - (int)a[1]);

        int n = list.size();
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int a = (int)list.get(start)[1] - average;
            int b = average - (int)list.get(end)[1];
            String nameA = (String)list.get(start)[0];
            String nameB = (String)list.get(end)[0];
            if (a == b) {
                ret.add(nameA + "->" + nameB + ":::" + a);
                start++;
                end--;
            } else if (a > b) {
                ret.add(nameA + "->" + nameB + ":::" + b);
                end--;
                list.get(start)[1] = (a - b) + average;
            } else {    //a < b
                ret.add(nameA + "->" + nameB + ":::" + a);
                start++;
                list.get(end)[1] = average - b + a;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        //{a:30, b:50, c:20, d:0}
        for (String pair: transaction.getTransactionHistory(new Object[][] {{"a",30},{"b",50},{"c",20},{"d",0}})) {
            System.out.println(pair);
        }
    }
}
