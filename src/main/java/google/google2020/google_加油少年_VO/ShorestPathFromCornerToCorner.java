package google.google2020.google_加油少年_VO;

/*
给一个m * n 0/1 grid, 0代表能走，1代表不能走。
问左上角到右下角的最短路径的距离。
应该秒了的，结果我脑子一抽写了个two way bfs（complexity还说错了）。
应该有follow up要output最短路径吧，写的太慢了刚刚写完第一问。

 */

import java.util.*;

/**
 * 如果只是输出最短路径的长度 bfs可以 但是要输出最短路径的path 感觉还是dfs方便
 */

public class ShorestPathFromCornerToCorner {

    /**
     * dfs + mem 我写的有点问题 之后再看吧
     *
     *
     *
     * !!!!!!!!!!!!! 这题有起点和终点 根本不用mem啊！！！！
     *
     *
     * dfs就解决了。。。。
     * @param args
     */

//    public List<List<String>> getShortestPath(int[][] grid) {
//        final int M = grid.length;
//        final int N = grid[0].length;
//        //Stirng ("i" + "j") as key
//        Map<String, List<List<String>>> mem = new HashMap<>();
//        if (grid[M - 1][N - 1] == 1) return new ArrayList<>();
//        return dfs(grid, 0, 0, M - 1, N - 1, mem, new int[M][N]);
//    }
//
//    private List<List<String>> dfs(int[][] grid, int i, int j, int endX, int endY, Map<String, List<List<String>>> mem, int[][] visited) {
//
//        visited[i][j] = 1;
//
//        if (mem.containsKey(i + "_" + j)) {
//            visited[j][j] = 0;
//            return mem.get(i + "_" + j);
//        }
//
//        if (i == endX && j == endY) {
//            String point = i + "_" + j;
//            List<List<String>> paths = new ArrayList<>();
//            List<String> path = new LinkedList<>();
//            path.add(point);
//            paths.add(new ArrayList<>(path));
//            visited[i][j] = 0;
//            return paths;
//        }
//
//        List<List<String>> tempShortestPaths = new ArrayList<>();
//
//        int[][] dirs = new int[][] {{-1,0},{0,1},{0,-1},{1,0}};
//        for (int[] dir: dirs) {
//            int ii = i + dir[0];
//            int jj = j + dir[1];
//            if (ii >= 0 && ii <= endX && jj >= 0 && jj <= endY && grid[ii][jj] == 0 && visited[ii][jj] == 0) {
//                List<List<String>> temp = dfs(grid, ii, jj, endX, endY, mem, visited);
//                if (temp.size() != 0) {
//                    if (tempShortestPaths.size() == 0 || temp.get(0).size() < tempShortestPaths.get(0).size()) {
//                        tempShortestPaths = new ArrayList<>(temp);
//                    } else if (temp.get(0).size() == tempShortestPaths.get(0).size()) {
//                        for (List<String> path: temp) {
//                            tempShortestPaths.add(new LinkedList<>(path));
//                        }
//                    }
//                }
//            }
//        }
//
//        //add curnode to each shortestPaths
//
//
//        mem.put(i + "_" + j, new ArrayList<>());
//
//        if (tempShortestPaths.size() != 0) {
//
//            for (List<String> path: tempShortestPaths) {
//                path.add(0, i + "_" + j);
//                mem.get(i + "_" + j).add(new LinkedList<>(path));
//            }
//        }
//
////        mem.put(i + "_" + j, (ArrayList<List<String>>) ((ArrayList<List<String>>) tempShortestPaths).clone());
//
//        visited[i][j] = 0;
//
//        return tempShortestPaths;
//    }

    private int[][] dirs = new int[][] {{0,1},{0,-1},{-1,0},{1,0}};

    public List<String> findShortestPathsBFS(int[][] grid) {
        if (grid == null || grid.length == 0) return new ArrayList<>();
        final int M = grid.length;
        final int N = grid[0].length;
        int[][] visited = new int[M][N];
        //here the first element is the point and second element is the path to this point;
        Queue<String[]> queue = new LinkedList<>();
        queue.add(new String[] {"0_0", "0_0"});
        visited[0][0] = 1;
        List<String> ret = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            Queue<String[]> nextQueue = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                String[] cur = queue.remove();
                String node = cur[0];
                String path = cur[1];
                for (int[] dir: dirs) {
                    int x = Integer.parseInt(node.split("_")[0]) + dir[0];
                    int y = Integer.parseInt(node.split("_")[1]) + dir[1];
                    if (x == M - 1 && y == N - 1) {
                        ret.add(path + "->" + (x + "_" + y));
                    }
                    if (x >= 0 && x < M && y >= 0 && y < N && visited[x][y] == 0 && grid[x][y] == 0) {
                        nextQueue.add(new String[] {x + "_" + y, path + "->" + (x + "_" + y)});
                    }
                }
            }
            //add to visited
            for (String[] strs: nextQueue) {
                String node = strs[0];
                int x = Integer.parseInt(node.split("_")[0]);
                int y = Integer.parseInt(node.split("_")[1]);
                visited[x][y] = 1;
            }
            //
            queue = nextQueue;
        }

        return ret;
    }


    public static void main(String[] args) {
        ShorestPathFromCornerToCorner shorestPathFromCornerToCorner = new ShorestPathFromCornerToCorner();
        List<String> ret = shorestPathFromCornerToCorner.findShortestPathsBFS(new int[][] {{0,0,0},{0,0,1},{0,0,0}});
        //3
        System.out.println(ret.size());
        for (String s: ret) {
            System.out.println(s);
        }

    }


}
