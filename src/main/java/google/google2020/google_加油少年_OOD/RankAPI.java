package google.google2020.google_加油少年_OOD;

/*

刚刚google店面，问了api设计， rank(score, name)
getRank(name);
getRank(Position)
不知道怎么优化算法。。 就是每次给一个人名和分数，自动得到位置。


yansonghuang 发表于 2019-10-9 02:07
就用了hashmap没想出来啥好方法。。。

我想的是用一个ArrayList存出现过的分数，
用一个ArrayList存名字，
每次放进一个分数的时候，用binary search找位置，
再在名字的list里相应的地方加入名字，
这样getRank()不管是名字还是分数，
直接返回在list里面的坐标就好了

 */

import java.util.HashMap;
import java.util.Map;

/**
 * 我的想法：
 *
 * clarification：
 * what is the size of the input , is it a stream?
 * what is the range of the score?
 *
 * 如果只是0-100分的话 就用
 * int[] scores = new int[101]
 * 我找到成绩就是O（1）
 * 然后scores【i】的value就是这个成绩下面有几个人
 *
 * 用一个map记录（人名， 成绩）
 *
 * 然后当我输入人名的时候
 * 就找100 - 这个人 之前的sum （算scores在一个范围的和）
 *
 */

public class RankAPI {

    int[] scores = new int[101];
    Map<String, Integer> nameScoresMap = new HashMap<>();

    public void rank(int score, String name) {
        scores[score]++;
        nameScoresMap.put(name, score);
    }

    public int rank (String name) {
        if (!nameScoresMap.containsKey(name)) {
            System.out.println(name + " is not in the team");
            return -1;
        }
        int score = nameScoresMap.get(name);
        int sum = 1;
        for (int i = 100; i > score; --i) {
            sum += scores[i];
        }
        return sum;
    }

}
