package google.google2020.google_加油少年_VO;

/*
就是我开始的时候在array的头部， 我每次只能走左右或者到任何和我数值一样的点，请问最快走几步
 */

import java.util.*;

public class shortestStepsToEndOfArray {
    //用bfs吧
    public int sovle(int[] nums) {
        //放的是 数值 和 list of 对应的index
        Map<Integer, List<Integer>> map = new HashMap<>();
        final int N = nums.length;
        for (int i = 0; i < N; ++i) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        Set<Integer> visited = new HashSet<>();
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int curIndex = queue.remove();
                visited.add(curIndex);
                if (curIndex == N - 1) return level + 1;
                //update queue
                if (curIndex - 1 >= 0 && visited.add(curIndex - 1)) {
                    queue.add(curIndex - 1);
                }
                if (visited.add(curIndex + 1)) queue.add(curIndex + 1);
                if (map.containsKey(nums[curIndex])) {
                    for (int index: map.get(nums[curIndex])) {
                        if (visited.add(index)) queue.add(index);
                    }
                }
            }
            level++;
        }
        //will not be here
        return level;
    }
}
