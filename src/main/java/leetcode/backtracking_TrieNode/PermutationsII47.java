package leetcode.backtracking_TrieNode;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
Seen this question in a real interview before?
 */

import java.util.*;
public class PermutationsII47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer>[] visited = new HashSet[nums.length];
        for (int i = 0 ; i < nums.length ; ++i) {
            visited[i] = new HashSet<>();
        }
        int[] indexVisited = new int[nums.length];
        if (nums.length == 0) return ret;
        dfs (nums , 0 , new ArrayList<>() , visited , indexVisited , ret);
        return ret;
    }

    private void dfs (int[] nums , int level , List<Integer> path , Set<Integer>[] visited , int[] indexVisited , List<List<Integer>> ret) {
        if (level == nums.length) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0 ; i < nums.length ; ++i) {
            if (indexVisited[i] == 0 && !visited[level].contains(nums[i])) {
                indexVisited[i] = 1;
                visited[level].add(nums[i]);
                path.add(nums[i]);
                dfs (nums , level + 1 , path , visited , indexVisited, ret);
                if (level < nums.length - 1) visited[level + 1].clear();
                indexVisited[i] = 0;
                path.remove(path.size() - 1);
            }
        }
    }


    public List<List<Integer>> permuteUniqueqw(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, result, path, visited);
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] visited) {
        //base case
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //general case;
        Integer pre = null;
        for (int i = 0; i < nums.length; i++) {
            if (pre != null && pre == nums[i]) {
                continue;
            }
            if (!visited[i] && (pre == null || pre != nums[i]) ) {
                path.add(nums[i]);
                visited[i] = true;
                dfs(nums, result, path, visited);
                visited[i] = false;
                path.remove(path.size() - 1);
                pre = nums[i];
            }
        }
    }

    public static void main (String[] args) {
        PermutationsII47 p2 = new PermutationsII47();
        System.out.println(p2.permuteUnique(new int[] {1,1,2}));
        System.out.println(p2.permuteUniqueqw(new int[] {1,1,2}));
        System.out.println(p2.permuteUnique(new int[] {1,2,3,2}));
        System.out.println(p2.permuteUniqueqw(new int[] {1,2,3,2}));
    }
}
