package google.google2020.google_加油少年_VO;

/*
一个水滴在空中落下，在它跟地面之间有许多木板，
当到达木板时水滴只能左右移动，然后到木板边缘后可以继续下落，
求水滴到地面的最短路径。楼主当时卡了一会儿，
后面没时间了写了个dfs的伪代码，记录每个位置的步数。
 */

public class WaterDropShortestDistance {

    private int shortest = Integer.MAX_VALUE;

    public int solve(int start, int[][] benches) {
        dfs(0, start, benches.length, benches, 0);
        return shortest;
    }

    private void dfs(int level, int position, int n, int[][] benches, int time) {
        if (level == n) {
            shortest = Math.min(shortest, time);
            return;
        }

        if (position < benches[level][0] || position > benches[level][1]) {
            dfs(level + 1, position, n, benches, time);
        } else {
            dfs(level + 1, benches[level][0], n, benches, time + position - benches[level][0]);
            dfs(level + 1, benches[level][1], n, benches, time + benches[level][1] - position);
        }
    }

    public static void main(String[] args) {
        WaterDropShortestDistance waterDropShortestDistance = new WaterDropShortestDistance();
        System.out.println(waterDropShortestDistance.solve(4, new int[][] {{1,3},{3,7}}));
    }
}
