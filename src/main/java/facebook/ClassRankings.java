package facebook;

import java.util.*;

/**
 * see the questions under the dir
 */

public class ClassRankings {

    public int countComparisons(List<List<Integer>> input) {
        int ret = 0;
        int m = input.size();
        int n = input.get(0).size();
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                for (int k = 0 ; k < n; ++k) {
                    ret++;
                    if (!input.get(i).get(k).equals(input.get(j).get(k))) {
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public int countComparisonsDfs(List<List<Integer>> input, int level) {
        int ret = 0;
        int remainingStudentCount = input.size();

        Map<Integer, List<List<Integer>>> curHighestRankingSubjectScoreRemainingSubjectScoresPair = new HashMap<>();
        for (List<Integer> remainingSubjectsPerStudent : input) {
            int remainingSubjectCount = remainingSubjectsPerStudent.size();
            int curHighestRankingSubjectScore = remainingSubjectsPerStudent.get(0);
            if (!curHighestRankingSubjectScoreRemainingSubjectScoresPair.containsKey(curHighestRankingSubjectScore)) {
                curHighestRankingSubjectScoreRemainingSubjectScoresPair.put(curHighestRankingSubjectScore, new ArrayList<>());
            }
            curHighestRankingSubjectScoreRemainingSubjectScoresPair.get(curHighestRankingSubjectScore).add(remainingSubjectCount != 1 ? remainingSubjectsPerStudent.subList(1, remainingSubjectCount) : Collections.singletonList(0));

        }

        for (Map.Entry<Integer, List<List<Integer>>> entry: curHighestRankingSubjectScoreRemainingSubjectScoresPair.entrySet()) {
            int curStudentCountPerScore = entry.getValue().size();
            remainingStudentCount -= curStudentCountPerScore;
            ret += (curStudentCountPerScore * remainingStudentCount) * level;
            if (entry.getValue().get(0).get(0) != 0) {
                ret += countComparisonsDfs(entry.getValue(), level + 1);
            } else {
                ret += factorialUsingForLoop(curStudentCountPerScore) / (factorialUsingForLoop(2) * factorialUsingForLoop(curStudentCountPerScore - 2)) * level;
            }
        }

        return ret;
    }

    private int factorialUsingForLoop(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) {
        ClassRankings classRankings = new ClassRankings();
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1,2));
        input.add(Arrays.asList(1,3));
        input.add(Arrays.asList(2,1));
        input.add(Arrays.asList(2,3));
        input.add(Arrays.asList(2,1));
        System.out.println(classRankings.countComparisons(input));
        System.out.println(classRankings.countComparisonsDfs(input, 1));
        //
        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(Arrays.asList(1,2,4));
        input2.add(Arrays.asList(1,3,3));
        input2.add(Arrays.asList(2,1,6));
        input2.add(Arrays.asList(2,3,1));
        input2.add(Arrays.asList(2,1,1));
        input2.add(Arrays.asList(3,1,4));
        input2.add(Arrays.asList(3,1,7));
        input2.add(Arrays.asList(3,1,2));
        System.out.println(classRankings.countComparisons(input2));
        System.out.println(classRankings.countComparisonsDfs(input2, 1));
    }
}
