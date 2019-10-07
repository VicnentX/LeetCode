package Amazon.full_time2020.final_round_加油少年_coding;


/*
第二轮，印度老姐，
口音感人且不带耳机，
信号还不好，考虑survey反馈一下...
并查集的问题，判断一堆edges能构建多少个clusters，输出clusters数目；
 */

/**
 * clarify:
 *  index from 0 or from 1
 *  do i know how many nodes there are
 *
 */

public class EdgesCluster {

    public int unionFindCluster(int n, int[][] edges) {

        int[] roots = new int[n];
        for (int i = 0; i < n; ++i) {
            roots[i] = i;
        }

        for (int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            int root1 = findRoot(node1, roots);
            int root2 = findRoot(node2, roots);

            if (root1 != root2) {
                roots[root2] = root1;
                n--;
            }
        }

        return n;
    }

    private int findRoot(int id, int[] roots) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots[id], roots);
        return roots[id];
    }

    public static void main(String[] args) {
        EdgesCluster edgesCluster = new EdgesCluster();
        System.out.println(edgesCluster.unionFindCluster(
                7, new int[][]{{0,2}, {1,2}, {3,2}, {4,2}, {0,4}}
        ));
    }
}
