package twitter;

/*
see pdf for question
 */

import java.util.*;

public class ImageMatching {

    /*
     * Complete the 'countMatches' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING_ARRAY grid1
     *  2. STRING_ARRAY grid2
     */
    private int cnt = 0;
    private Set<Integer> set = new HashSet<>();
    private int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public  int countMatches(List<String> grid1, List<String> grid2) {
        // Write your code here
        //check;
        cnt = 0;
        if (grid1.size() != grid2.size()) return cnt;
        for (int i = 0 ; i < grid1.size() ; ++i) {
            if (grid1.get(i).length() != grid2.get(i).length()) return cnt;
        }
        int m = grid1.size();
        int n = grid1.get(0).length();
        //initilize
        List<int[]> delete = new ArrayList<>();
        int[] roots = new int[m * n];
        Arrays.fill(roots , -1);
        //add
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (grid1.get(i).charAt(j) == '1' && grid2.get(i).charAt(j) == '1') {
                    int pos = i * n + j;
                    roots[pos] = pos;
                    ++cnt;
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        int neighbor = x * n + y;
                        if (x >= 0 && x < m && y >= 0 && y < n && roots[neighbor] != -1) {
                            int neighborRoot = find(roots, neighbor);
                            if (pos != neighborRoot) {
                                roots[pos] = neighborRoot;
                                pos = neighborRoot;
                                --cnt;
                            }
                        }
                    }
                } else if ( grid1.get(i).charAt(j) == '0' && grid2.get(i).charAt(j) == '1'
                        || grid1.get(i).charAt(j) == '1' && grid2.get(i).charAt(j) == '0'){
                    delete.add(new int[] {i , j});
                }
            }
        }
        //delete
        for (int[] p : delete) {
            Deleteconnection(p[0] , p[1] , roots , m , n);
        }
        //OUTPUT
        return cnt;
    }

    private void Deleteconnection(int i , int j , int[] roots , int m , int n) {
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            int neighbor = x * n + y;
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (roots[neighbor] == -1) continue;
                int neighborRoot = find(roots, neighbor);
                if (!set.contains(neighborRoot)) {
                    set.add(neighborRoot);
                    --cnt;
                }
            }
        }
    }
    private int find (int[] roots , int id) {
        while (roots[id] != id) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }


    //dfs method
    private boolean allTwos;
    public int countMatchesDFS(List<String> grid1, List<String> grid2) {
        cnt = 0;
        if (grid1.size() != grid2.size()) return cnt;
        for (int i = 0; i < grid1.size(); ++i) {
            if (grid1.get(i).length() != grid2.get(i).length()) return cnt;
        }
        int m = grid1.size();
        int n = grid1.get(0).length();
        int[][] matrix = new int[m][n];
        //fill the matrix
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid1.get(i).charAt(j) == '1' && grid2.get(i).charAt(j) == '1') {
                    matrix[i][j] = 2;
                } else if (grid1.get(i).charAt(j) == '0' && grid2.get(i).charAt(j) == '0') {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 2) {
                    allTwos = true;
                    dfs(i , j , matrix , m , n);
                    if (allTwos) ++cnt;
                }
            }
        }
        return cnt;
    }
    private void dfs(int i , int j , int[][] matrix , int m , int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] <= 0) return ;
        if (matrix[i][j] == 1) allTwos = false;
        matrix[i][j] *= -1;
        dfs(i + 1 , j , matrix , m , n );
        dfs(i - 1 , j , matrix , m , n );
        dfs(i , j + 1 , matrix , m , n );
        dfs(i , j - 1 , matrix , m , n );
    }

    public static void main(String[] args) {
        ImageMatching s = new ImageMatching();

        System.out.println(s.countMatchesDFS(new ArrayList<>(Arrays.asList("001", "011", "100")),
                new ArrayList<>(Arrays.asList("001", "011", "101"))));

        System.out.println(s.countMatchesDFS(new ArrayList<>(Arrays.asList("0100", "1001", "0011", "0011")),
                new ArrayList<>(Arrays.asList("0101", "1001", "0011", "0011"))));

        System.out.println(s.countMatchesDFS(new ArrayList<>(Arrays.asList("0010", "0111", "0100", "1111")),
                new ArrayList<>(Arrays.asList("0010", "0111", "0110", "1111"))));
        System.out.println("________________________________");
        System.out.println(s.countMatches(new ArrayList<>(Arrays.asList("001", "011", "100")),
                new ArrayList<>(Arrays.asList("001", "011", "101"))));

        System.out.println(s.countMatches(new ArrayList<>(Arrays.asList("0100", "1001", "0011", "0011")),
                new ArrayList<>(Arrays.asList("0101", "1001", "0011", "0011"))));

        System.out.println(s.countMatches(new ArrayList<>(Arrays.asList("0010", "0111", "0100", "1111")),
                new ArrayList<>(Arrays.asList("0010", "0111", "0110", "1111"))));
    }
}



//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int grid1Count = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> grid1 = IntStream.range(0, grid1Count).mapToObj(i -> {
//            try {
//                return bufferedReader.readLine();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        })
//                .collect(toList());
//
//        int grid2Count = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> grid2 = IntStream.range(0, grid2Count).mapToObj(i -> {
//            try {
//                return bufferedReader.readLine();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        })
//                .collect(toList());
//
//        int result = Result.countMatches(grid1, grid2);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}
