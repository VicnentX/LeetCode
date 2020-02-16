package leetcode;

import java.util.*;

public class TweetCounts {

    Map<String, Integer> map = new HashMap<>();

    public int maxStudents(char[][] seats) {
        return dfs(0, seats, seats.length, map, "");
    }

    private int dfs(int level, char[][] nums, int m, Map<String, Integer> map, String preLine) {

        if (map.containsKey(level + "-" + preLine)) {
            return map.get(level + "-" + preLine);
        }

        int tempMax = 0;

        if (level == m) {
            return 0;
        }

        //layout 里面是 这行的样子 + 这行我加了几个人
        List<List<Object>> layouts = new ArrayList<>();
        produceLine(0, level, nums, nums[0].length, 0, "", preLine, layouts);

        for (List<Object> layout: layouts) {
            tempMax = Math.max(tempMax, (int)layout.get(1) + dfs(level + 1, nums, m, map, (String)layout.get(0)));
        }

        map.put(level + "-" + preLine, tempMax);
        return tempMax;
    }

    private void produceLine(int i, int level, char[][] nums, int n, int personCnt, String line, String preLine, List<List<Object>> layouts) {
        if (i == n) {
            layouts.add(new ArrayList<>(Arrays.asList(line, personCnt)));
            return;
        }
        if (nums[level][i] == '.' && (i == 0 || line.charAt(i - 1) != '1') && (level == 0 || i == 0 || preLine.charAt(i - 1) != '1') && (level == 0 || i == n - 1 || preLine.charAt(i + 1) != '1')) {
            //addPerson
            produceLine(i + 1, level, nums, n, personCnt + 1, line + "1", preLine, layouts);
            //not add
            produceLine(i + 1, level, nums, n, personCnt, line + nums[level][i], preLine, layouts);
        } else {
            //can not add
            produceLine(i + 1, level, nums, n, personCnt, line + nums[level][i], preLine, layouts);
        }
    }


    public int countNegatives(int[][] grid) {
        int ret = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] < 0) {
                    ret += ((m - i) * (n - j));
                    n = j;
                }
            }
        }
        return ret;
    }

    List<Integer> postProduct;

    public TweetCounts() {
        postProduct = new ArrayList<>();
    }

    public void add(int num) {
        for (int i = 0; i < postProduct.size(); ++i) {
            postProduct.set(i, postProduct.get(i) * num);
        }
        postProduct.add(num);
    }

    public int getProduct(int k) {
        return postProduct.get(postProduct.size() - k - 1);
    }

    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.add(3);
        tweetCounts.add(0);
        tweetCounts.add(2);
        tweetCounts.add(5);
        tweetCounts.add(4);
    }


}
