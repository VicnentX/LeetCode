package leetcode.UnionFind;

//union find with compression means the tree is flat
//only change the root to be the same

public class NumberOfConnectedComponentInAnUndirectedGraph323 {
    public int countComponents(int n, int[][] edges) {

//         int[] visited = new int[n];
//         int ret = 0;
//         HashMap<Integer , HashSet<Integer>> map = new HashMap<>();
//         for(int[] k : edges){
//             if(!map.containsKey(k[0])) map.put(k[0] , new HashSet<>());
//             map.get(k[0]).add(k[1]);
//             if(!map.containsKey(k[1])) map.put(k[1] , new HashSet<>());
//             map.get(k[1]).add(k[0]);
//         }
//         for(int i = 0 ; i < n ; ++i){
//             if(visited[i] == 0){
//                 HashSet<Integer> node = new HashSet<>();
//                 node.add(i);
//                 while(!node.isEmpty()){
//                     HashSet<Integer> tem = new HashSet<>();
//                     for(int k : node){
//                         if(visited[k] == 0){
//                             if(map.containsKey(k)){
//                                 tem.addAll(map.get(k));
//                             }
//                             visited[k] = 1;
//                         }
//                     }
//                     node = tem;
//                 }
//                 ++ret;
//             }
//         }
//         return ret;

        //union find with compression
        int[] roots = new int[n];
        for(int i = 0 ; i < n ; ++i){
            roots[i] = i;
        }
        for(int[] k : edges){
            int root1 = find(roots , k[0]);
            int root2 = find(roots , k[1]);
            if(root1 != root2){
                roots[root1] = root2;
                --n;
            }
        }
        return n;
    }
    private int find(int[] roots , int id){
//        while(roots[id] != id){
//            roots[id] = roots[roots[id]];
//            id = roots[id];
//        }
//        return id;

        if (roots[id] == id) return id;
        roots[id] = find(roots, roots[id]);
        return roots[id];
    }
}
