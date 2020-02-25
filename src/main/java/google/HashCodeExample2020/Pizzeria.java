package google.HashCodeExample2020;

import java.io.*;
import java.util.*;

public class Pizzeria {

    private final static String PATH = "/Users/Vincent_Xia/IdeaProjects/LeetCode/src/main/java/google/HashCodeExample2020/";
    private final static int LIMIT = 1000000000;
    //SKIP_BIT 是取4的倍数 这样设置长度的时候方便很多
    private final static int SKIP_BIT = 8;

    private static int globalMax;
    private static int[] globalCombination;

    public static void solve(final File folder) throws IOException {
        //get data from file
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                solve(fileEntry);
            } else if(fileEntry.getName().endsWith(".in")
//                    && !fileEntry.getName().endsWith("big.in")
            ) {
                getMorePizza(fileEntry.getName());
            }
        }
    }


    String s = "src/main/java/google/HashCodeExample2020/answer of a_example.out";
    private static void getMorePizza(String fileName) throws IOException {
        //System.out.println(fileName);
        BufferedReader br = new BufferedReader(new FileReader(PATH + fileName));
        try {
            StringBuilder sb = new StringBuilder();
            /**
             * 用 "./"  的话就是说明是在当前目录下面，这个当前目录是src这一层.但不是src
             * "../" 代表当前目录的parent 就是IdeaProjects后面一层
             */
            PrintWriter writer = new PrintWriter("./src/main/java/google/HashCodeExample2020/" + "answer_of_" + fileName.substring(0, fileName.length() - 2) + "out", "UTF-8");

            //get first line
            String line = br.readLine();
            String[] token = line.split(" ");
            final int MAX = Integer.valueOf(token[0]);
            final int PIZZA_TYPES_SIZE = Integer.valueOf(token[1]);
            System.out.println(MAX + " " + PIZZA_TYPES_SIZE);

            //get second line
            line = br.readLine();
            token = line.split(" ");

            //MAX > 250000000
            /**
             * MAX 大的话就没有办法用01snapsack了
             * PIZZA_TYPES_SIZE 大的话就不能用dfs了
             */

            if (PIZZA_TYPES_SIZE >= 1000) { //It depends on the amount of virtual memory allocated to the stack.
                //I tested on my system and didn't find any constant value, sometimes stack overflow occurs after 8900 calls,
                // sometimes only after 7700, random numbers.

                //greedy algorithm n^2 * SKIP
                //我可以每次对于第二大的可选项进行可选可不选的操作
                //也就是我允许跳过几个大的数字 参数是skip

                List<Integer> optimalList = new ArrayList<>();
                globalMax = 0;

                for (int i = 0; i < (1 << SKIP_BIT); i++){
                    //这里0x100 和 SKIP_BIT是相呼应的
                    String skipPattern = Integer.toBinaryString(0x100 | i).substring(1);
                    int skipPatternLength = skipPattern.length();
                    //System.out.println(skipPattern);

                    List<Integer> indexList = new ArrayList<>();
                    int cnt = 0;
                    int sum = 0;
                    for(int j = PIZZA_TYPES_SIZE - 1; j >= 0; --j) {
                        int cur = Integer.valueOf(token[j]);
                        if (cur <= MAX - sum) {
                            if (cnt >= skipPatternLength || skipPattern.charAt(cnt) == '1') {
                                indexList.add(j);
                                sum += cur;
                            }
                            cnt++;
                        }
                    }
                    if (sum > globalMax) {
                        globalMax = sum;
                        optimalList = new ArrayList<>(indexList);
                    }
                }

                //write into output file
                writer.println(globalMax);
                for (int i = optimalList.size() - 1; i >= 0; --i) {
                    writer.print(optimalList.get(i) + " ");
                }
                writer.println();
                writer.close();

            } else {
                /**
                 * 其实这里else的方法可以不用了，上面就是使用的有效元素内穷举，超过了就greedy
                 */
                //dp[i] is the sum of nums[0] to nums[i], it will set it to 10 ^ 9 if it is larger than 10 ^ 9
                int[] dp = new int[PIZZA_TYPES_SIZE];
                Arrays.fill(dp, 1000000000);
                dp[0] = Integer.valueOf(token[0]);
                for (int i = 1; i < PIZZA_TYPES_SIZE; ++i) {
                    int cur = Integer.valueOf(token[i]);
                    if (cur + (double)dp[i - 1] < LIMIT) {
                        dp[i] = cur + dp[i - 1];
                    } else {
                        break;
                    }
                }

                //reset globalMax and do dfs
                globalMax = 0;
                globalCombination = new int[PIZZA_TYPES_SIZE];
                getOptimalCombination(PIZZA_TYPES_SIZE - 1, token, 0, dp, new int[PIZZA_TYPES_SIZE], MAX);

                //write into output file
                writer.println(globalMax);
                for (int i = 0; i < PIZZA_TYPES_SIZE; ++i) {
                    if (globalCombination[i] == 1) {
                        writer.print(i + " ");
                    }
                }
                writer.println();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    private static void getOptimalCombination(int i, String[] token, int sum, int[] dp, int[] combination, int MAX) {
        if (i == -1) {
            if (sum > globalMax) {
                globalMax = sum;
                globalCombination = combination.clone();
            }
            return;
        }

        if (sum < globalMax - dp[i]) return;

        int curPizza = Integer.valueOf(token[i]);

        if (sum <= MAX - curPizza) {
            combination[i] = 1;
            getOptimalCombination(i - 1, token, sum + curPizza, dp, combination, MAX);
            combination[i] = 0;
        }
        getOptimalCombination(i - 1, token, sum, dp, combination, MAX);
    }


    public static void main(String[] args) throws IOException {
        solve(new File(PATH));
    }
}
