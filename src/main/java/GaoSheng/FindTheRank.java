package GaoSheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 就是有n个学生 每个学生有正好参加了K门考试 让找出来总分排名第R个的同学，总分一样的就是按照之前index的顺序
 */

public class FindTheRank {
    public int findTheRankOfStudent(List<List<Integer>> performance, int rank) {
        final int cntStudent = performance.size();
        int[][] pairscoreIndex = new int[cntStudent][2];

        //build pair(index, total score of this index student)
        for (int i = 0 ; i < cntStudent ; ++i) {
            int totalScores = 0;
            for (int score: performance.get(i)) {
                totalScores += score;
            }
            pairscoreIndex[i] = new int[]{i , totalScores};
        }

        //sort
        Arrays.sort(pairscoreIndex, (a, b) -> a[1] != b[1] ? b[1] - a[1] : a[0] - b[0]);

        return pairscoreIndex[rank - 1][0];
    }

    public static void main(String[] args) {
        FindTheRank findTheRank = new FindTheRank();
        List<List<Integer>> performance = new ArrayList<>();
        performance.add(Arrays.asList(79,89,15));
        performance.add(Arrays.asList(85,89,92));
        performance.add(Arrays.asList(71,96,88));
        System.out.println(findTheRank.findTheRankOfStudent(performance, 2));
        System.out.println(findTheRank.findTheRankOfStudent(performance, 3));
    }
}
