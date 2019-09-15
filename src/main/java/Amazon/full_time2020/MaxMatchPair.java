package Amazon.full_time2020;

/**
 * 2. 内存容量限定后找最match的任务pair。
 * 输入：机器最大内存，前台任务表和后台任务表，
 * 每个任务两个数字：一个是ID， 第二个是需要消耗的内存数。
 * 要求从两个表中取出一个pair，
 * 使得他们的内存消耗最大限度 <= 机器最大内存。求出所有的pair。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 例如： 5，[[1,2] [2,3],[3,2]], [[2,2] [3,3]] ->
 * 结果就是 [1,3] [3,3]
 */
public class MaxMatchPair {
    public List<List<Integer>> findAllMatchPair(int limit, List<List<Integer>> frontend, List<List<Integer>> backend) {
        return new ArrayList<>();
    }
}
