package google.google2020.google_加油少年_OOD;

/*
设计一个trading system，从外部接收数据，数据有时间，表项，以及对应的数据，
大致是[timestamp,{symbol1: value1, symbol2: value2, ...}]
你设计的系统能够存储这些信息，并且能提取出某个时间段内的数据，
比如[ts1, ts2]之间的数据，并且根据要求的query返回指定的值，
例如加权平均，最大值的等等
 */

/**
assume data is time ordered
 */

import javafx.util.Pair;

import java.util.*;

public class Trading {

    public class Info {
        private int highest;
        private int highestTimePoint;
        //time map to index in the prices list
        private TreeMap<Integer, Integer> timeToIndex;
        //pair of time and price
        private List<Pair<Integer, Integer>> prices;

        public Info() {
            highest = Integer.MIN_VALUE;
            highestTimePoint = -1;
            prices = new ArrayList<>();
            timeToIndex = new TreeMap<>();
        }

        public void add(int time, int price) {
            if (price > highest) {
                setHighest(price);
            }
            highestTimePoint = time;
            prices.add(new Pair<>(time, price));
            timeToIndex.put(time, prices.size() - 1);
        }

        public int getHighest() {
            return highest;
        }

        public void setHighest(int highest) {
            this.highest = highest;
        }

        public int getHighestTimePoint() {
            return highestTimePoint;
        }

        public void setHighestTimePoint(int highestTimePoint) {
            this.highestTimePoint = highestTimePoint;
        }

        public TreeMap<Integer, Integer> getTimeToIndex() {
            return timeToIndex;
        }

        public void setTimeToIndex(TreeMap<Integer, Integer> timeToIndex) {
            this.timeToIndex = timeToIndex;
        }

        public List<Pair<Integer, Integer>> getPrices() {
            return prices;
        }

        public void setPrices(List<Pair<Integer, Integer>> prices) {
            this.prices = prices;
        }
    }

    Map<String, Info> table = new HashMap<>();

    public void insertData(int timeStamp, Pair<String, Integer>[] pairs) {
        for (Pair<String, Integer> pair: pairs) {
            String stock = pair.getKey();
            int price = pair.getValue();
            if (!table.containsKey(stock)) {
                table.put(stock, new Info());
            }
            table.get(stock).add(timeStamp, price);
        }
        return;
    }

    public int getHighestPrice(int ts1, int ts2, String stock) {
        if (!table.containsKey(stock)) {
            return -1;
        }
        if (table.get(stock).getHighestTimePoint() <= ts2
                && table.get(stock).getHighestTimePoint() >= ts1) {
            return table.get(stock).getHighest();
        }
        return getPriceHelper(ts1, ts2, stock)[0];
    }

    public int getAveragePrice(int ts1, int ts2, String stock) {
        if (!table.containsKey(stock)) {
            return -1;
        }
        return getPriceHelper(ts1, ts2, stock)[1];
    }

    private int[] getPriceHelper(int ts1, int ts2, String stock) {
        int start = table.get(stock).getTimeToIndex().ceilingEntry(ts1).getValue();
        int end = table.get(stock).getTimeToIndex().floorEntry(ts2).getValue();
        int localHighestPrice = -1;
        int priceSum = 0;
        for (int i = start; i <= end; ++i) {
            int curPrice = table.get(stock).getPrices().get(i).getValue();
            localHighestPrice = Math.max(localHighestPrice, curPrice);
            priceSum += curPrice;
        }
        return end >= start ? new int[] {localHighestPrice, priceSum / (end - start + 1)} : new int[] {-1,-1};
    }

    public static void main(String[] args) {
        Trading trading = new Trading();
        trading.insertData(0, new Pair[] {new Pair("STOCK1", 100), new Pair("STOCK2", 10), });
        trading.insertData(4, new Pair[] {new Pair("STOCK1", 102), new Pair("STOCK2", 12), });
        trading.insertData(6, new Pair[] {new Pair("STOCK1", 104), new Pair("STOCK2", 14), });
        System.out.println(trading.getHighestPrice(4,7, "STOCK1"));
        System.out.println(trading.getHighestPrice(2,3, "STOCK1"));
        System.out.println(trading.getHighestPrice(4,6, "STOCK2"));
        System.out.println(trading.getAveragePrice(0,6, "STOCK2"));
    }
}
