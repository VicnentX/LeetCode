package google.HashCode2020;

/*
this is the contest of google hash code 2020
 */

import java.awt.print.Book;
import java.io.*;
import java.util.*;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class HashCodeSolve {

    private final static String PATH = "/Users/Vincent_Xia/IdeaProjects/LeetCode/src/main/java/google/HashCode2020/";
    //SKIP_BIT 是取4的倍数 这样设置长度的时候方便很多
    private final static int SKIP_BIT = 16;

    private static double totalBookScore;
//    private static double[] totalPercentile = new double[20];
//    private static int totalCumulativeDay = 0;

    public static void solveHashCode(final File folder) throws IOException {
        //get data from file
        for (int multiplier = 1; multiplier <= 1; ++multiplier) {
            totalBookScore = 0;

            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    solveHashCode(fileEntry);
                } else if (fileEntry.getName().endsWith(".txt")) {
                    getMaxSumScore(fileEntry.getName(), multiplier);
                }
            }

            System.out.println("totalBookScore is " + totalBookScore + " with multiplier = " + multiplier);
        }
    }

    private static void getMaxSumScore(String fileName, int multiplier) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(PATH + fileName));
        try {
            StringBuilder sb = new StringBuilder();
            /**
             * 用 "./"  的话就是说明是在当前目录下面，这个当前目录是src这一层.但不是src
             * "../" 代表当前目录的parent 就是IdeaProjects后面一层
             */
            PrintWriter writer = new PrintWriter("./src/main/java/google/HashCode2020/" + "answer_of_" + fileName.substring(0, fileName.length() - 3) + "out", "UTF-8");

            //get first line
            String line = br.readLine();
            String[] token = line.split(" ");
            final int B = Integer.valueOf(token[0]);
            final int L = Integer.valueOf(token[1]);
            final int D = Integer.valueOf(token[2]);

            //get second line
            line = br.readLine();
            token = line.split(" ");
            //把所有的书存起来 存成int[]
//            String[] bookArrayStringVersion = token.clone();
            int[] booksArray = new int[token.length];
            for(int i = 0; i < B; ++i) {
                booksArray[i] = Integer.valueOf(token[i]);
            }

            //get library and store libraries
            Library[] librariesArray = new Library[L];
            int libraryId = 0;
            double sumOfAllLibrariesPartBooksScore = 0;
            double[] totalPercentile = new double[20];
            int totalCumulativeDay = 0;

            while((line = br.readLine()) != null && !line.equals("")) {
                token = line.split(" ");
                final int N = Integer.valueOf(token[0]);
                final int T = Integer.valueOf(token[1]);
                final int M = Integer.valueOf(token[2]);
                line = br.readLine();
                //now token is curLibBookList(array of string)
                token = line.split(" ");
                //这里因为可能有书重复的情况，我在这里加一个系数
                //这个系数到时变一下试一下
                //还有一个就是把N个书都sort 这种我这里先不使用
                int curLibBookListSizeLimit = (int)Math.min(N, (1.0 * multiplier * (D - T) * M));
                PriorityQueue<Integer> bookIdHeap = new PriorityQueue<>((a,b) -> booksArray[a] - booksArray[b]);
                for(String bookIdString: token) {
                    int bookId = Integer.valueOf(bookIdString);
                    bookIdHeap.add(bookId);
                    if (bookIdHeap.size() == curLibBookListSizeLimit + 1) {
                        bookIdHeap.poll();
                    }
                }
                int[] sortedBookIdArray = new int[curLibBookListSizeLimit];
                int sumOfBookValues = 0;
                for (int i = curLibBookListSizeLimit - 1; i >= 0; --i) {
                    int curBookId = bookIdHeap.remove();
                    sumOfBookValues += booksArray[curBookId];
                    sortedBookIdArray[i] = curBookId;
                }
                //get prefix sum as to get percentile[] and add to totalPercentile[]
                int[] prefixSum = new int[curLibBookListSizeLimit];
                prefixSum[0] = booksArray[sortedBookIdArray[0]];
                for(int i = 1; i < curLibBookListSizeLimit; ++i) {
                    prefixSum[i] = prefixSum[i - 1] + booksArray[sortedBookIdArray[i]];
                }
                int[] curPercentile = getCurPercentileAndAddToTotalPercentileArray(prefixSum, curLibBookListSizeLimit, totalPercentile);
                //get total day
                int curLibraryDaysUsedToShipBooks = (int)Math.ceil(1.0 * curLibBookListSizeLimit / M) ;
                totalCumulativeDay += curLibraryDaysUsedToShipBooks;
                //get total sum
                sumOfAllLibrariesPartBooksScore += sumOfBookValues;
                //get total cumulative day
                double curMedian = getMedian(sortedBookIdArray, booksArray);
                int curAverage = getAverage(sumOfBookValues, curLibBookListSizeLimit);
                librariesArray[libraryId] = new Library(libraryId, sortedBookIdArray, sumOfBookValues, booksArray[sortedBookIdArray[0]], T, M, curMedian, curAverage, curPercentile, curLibraryDaysUsedToShipBooks);
                libraryId++;
            }

            //print percentile[]
            int index = 5;
            for (double element: totalPercentile) {
                System.out.println(index + " : " + element);
                index += 5;
            }

            //-------------------------------------------------------

            //we are to try many combination and want to get the optimal one
            double globalMaxBookScore = 0;
            List<String> globalDetails = new ArrayList<>();
            double tempMaxBookScore = 0;
            double optimalCoefficient = -1;


            for (double coefficient = 0.1; coefficient <= 0.1; coefficient = coefficient + 0.1) {
                //here we have list of library and we are to sort library base on
                /**
                 * 这里可以多试试不同的策略
                 */
                //(1) sumOfBookValues (2)mostValueBook (3) T
                //第一个没有第二个好
//            Arrays.sort(librariesArray, (a,b) -> a.sumOfBookValues == b.sumOfBookValues ? a.T == b.T ? b.mostValueBook - a.mostValueBook : a.T - b.T : b.sumOfBookValues - a.sumOfBookValues);
                //第二种方法
//            Arrays.sort(librariesArray, (a,b) -> a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues);
                //第三种方法
                double finalSumOfAllLibrariesPartBooksScore = sumOfAllLibrariesPartBooksScore;
                Arrays.sort(librariesArray, (a, b) -> Double.compare(a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T, b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T) == 0 ?
                    a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues : Double.compare(b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T, a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T));
                //第四种方法(这种方法比第三种差一些)
//            Arrays.sort(librariesArray, (a,b) -> Double.compare(b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T, a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T));
                //第五种方法 ，这种方法还是会报错，我将来再看看
                double finalTotalCumulativeDay = totalCumulativeDay;
                System.out.println("一共有多少天 ： " + finalTotalCumulativeDay);
//            Arrays.sort(librariesArray, (a,b) -> Double.compare(a.sumOfBookValues + (totalPercentile[(int)(1.0 - a.T / (finalTotalCumulativeDay / L)) * 20]) , b.sumOfBookValues + (totalPercentile[(int)(1.0 - a.T / (finalTotalCumulativeDay / L) * 20)])) == 0 ?
//                            Double.compare(a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T, b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T) == 0 ?
//                                    a.sumOfBookValues == b.sumOfBookValues ?
//                                            a.mostValueBook == b.mostValueBook ?
//                                                    a.T - b.T :
//                                                    b.mostValueBook - a.mostValueBook :
//                                            b.sumOfBookValues - a.sumOfBookValues :
//                                    Double.compare(b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T, a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T) :
//                    Double.compare(b.sumOfBookValues + (totalPercentile[(int)(1.0 - b.T / (finalTotalCumulativeDay / L) * 20)]), a.sumOfBookValues + (totalPercentile[(int)(1.0 - a.T / (finalTotalCumulativeDay / L) * 20)])));

                //第六种方法改进第三种方法 (现在这种方法最快)
//            Arrays.sort(librariesArray, (a, b) -> Double.compare(a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T / D, b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T / D) == 0 ?
//                    a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues : Double.compare(b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T / D, a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T / D));

                //第7种
                //Math.ceil((finalTotalCumulativeDay - a.curLibraryDaysUsedToShipBooks) / (L - 1))
                double finalCoefficient = coefficient;
//                Arrays.sort(librariesArray, (a, b) -> Double.compare(a.sumOfBookValues + finalCoefficient * (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * (1 - a.T / Math.ceil((finalTotalCumulativeDay - a.curLibraryDaysUsedToShipBooks) / (L - 1))), b.sumOfBookValues + finalCoefficient * (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * (1 - b.T / Math.ceil((finalTotalCumulativeDay - b.curLibraryDaysUsedToShipBooks) / (L - 1)))) == 0 ?
//                        a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues : Double.compare(b.sumOfBookValues + finalCoefficient * (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * (1 - b.T / Math.ceil((finalTotalCumulativeDay - b.curLibraryDaysUsedToShipBooks) / (L - 1))), a.sumOfBookValues + finalCoefficient * (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * (1 - a.T / Math.ceil((finalTotalCumulativeDay - a.curLibraryDaysUsedToShipBooks) / (L - 1)))));


                for (int i = 0; i < (1 << SKIP_BIT); i++) {
                    //这里0x1000 和 SKIP_BIT是相呼应的
                    String skipPattern = Integer.toBinaryString(0x10000 | i).substring(1);
                    int skipPatternLength = skipPattern.length();
                    //first to store library to LibrarySetupIdList

                    //int[] = [setup end date, libraryId]
                    List<Integer> indexList = new ArrayList<>();
                    int curDay = 0;
                    int cnt = 0;    //point to the skipPattern

                    //只照着正好的情况放会有些一些问题，就是之前的数据放完之后，后面还有一个数据都和前面重复了，这样有些地方就塞不满了
                    List<String> tempDetails = new ArrayList<>();
                    for(int j = 0; j < L; ++j) {
                        Library curLibrary = librariesArray[j];

                        if (cnt >= skipPatternLength) indexList.add(j);

                        else if (curDay + curLibrary.T < D) {
                            if (skipPattern.charAt(cnt) == '1') {
                                indexList.add(j);
                                curDay += curLibrary.T;
                            }
                            cnt++;
                        }
                    }
                    // get the tempMaxBookScore and details to see if it is larger than globalMaxBookScore
                    int[] booksArrayCopy = booksArray.clone();
                    //这个libraryUsed就是最终究竟有几个图书馆被用到
                    int libraryUsed = 0;
                    tempMaxBookScore = 0;
                    int buildDay = 0;

                    for (int librarySelectedIndex: indexList) {
                        // 因为这里的library已经是重新排过序的了！！！！！！！！！！！
                        int curLibraryId = librariesArray[librarySelectedIndex].libraryId;
                        int setupDate = librariesArray[librarySelectedIndex].T;
                        int BooksShippedPerDay = librariesArray[librarySelectedIndex].M;
                        int[] curLibraryAllBookIdList= librariesArray[librarySelectedIndex].sortedBookIdArray;
                        int curLibraryAllBookIdListSize = curLibraryAllBookIdList.length;
                        //int[] newArray = Arrays.copyOfRange(oldArray, startIndex, endIndex);
                        //String joinedString = String.join(" ", strArray);

                        if (setupDate + buildDay >= D) continue;

                        int bookChosenCnt = 0;
                        int curLibraryBooksToBeChosenSize = (int)Math.min(curLibraryAllBookIdListSize, 1.0 * (D - setupDate - buildDay) * BooksShippedPerDay);
//                        details.add(String.join(" ", Arrays.copyOfRange(bookArrayStringVersion, 0, curLibraryBooksChosenSize)));
                        StringBuilder tempBooksChosenIdBuffer = new StringBuilder();
                        for (int curBookId: curLibraryAllBookIdList) {
                            if (bookChosenCnt == curLibraryBooksToBeChosenSize) break;
                            if (booksArrayCopy[curBookId] == 0) continue;
                            tempMaxBookScore += booksArrayCopy[curBookId];
                            tempBooksChosenIdBuffer.append(curBookId).append(" ");
                            booksArrayCopy[curBookId] = 0;
                            bookChosenCnt++;
                        }

                        if (bookChosenCnt == 0) continue;

                        tempDetails.add(curLibraryId + " " + bookChosenCnt);
                        tempDetails.add(tempBooksChosenIdBuffer.toString());
                        buildDay += setupDate;
                        libraryUsed++;
                    }

                    List<String> finalTempDetails = new ArrayList<>();
                    finalTempDetails.add(String.valueOf(libraryUsed));
                    finalTempDetails.addAll(tempDetails);

                    if (tempMaxBookScore > globalMaxBookScore) {
                        optimalCoefficient = coefficient;
                        globalMaxBookScore = tempMaxBookScore;
                        globalDetails = new ArrayList<>(finalTempDetails);
                    }
                }
            }


            totalBookScore += tempMaxBookScore;

            System.out.println(fileName + " max score is " + globalMaxBookScore + " with multiplier = " + multiplier + " with coefficient = " + optimalCoefficient);

            //write into output file
            for (String detailLine: globalDetails) {
                writer.println(detailLine);
            }
            writer.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //所以就是先用dp[i]存在下前i的和 ，然后通过你的方程建立一个len 20的【】 然后再扫出来对应位置的dp【i】存起来
    private static int[] getCurPercentileAndAddToTotalPercentileArray(int[] prefixSum, int n, double[] totalPercentile) {
        int[] ret = new int[20];
        for (int i = 5; i <= 100; i = i + 5) {
            ret[(i - 1) / 5] = prefixSum[(int)(Math.ceil(n * i / 100.0) - 1)];
            totalPercentile[(i - 1) / 5] += ret[(i - 1) / 5];
        }
        return ret;
    }

    private static int getAverage(int sumOfBookValues, int size) {
        return sumOfBookValues / size;
    }

    private static double getMedian(int[] sortedBookIdArray, int[] booksArray) {
        int n = sortedBookIdArray.length;
        if (n % 2 == 1) {
            return booksArray[sortedBookIdArray[n / 2]];
        } else {
            return booksArray[sortedBookIdArray[(n - 1) / 2]] / 2.0 + booksArray[sortedBookIdArray[n / 2]] / 2.0;
        }
    }


    public static void main(String[] args) throws IOException {
        solveHashCode(new File(PATH));
    }

    static class Library {
        int libraryId;
        int[] sortedBookIdArray;
        int sumOfBookValues;
        int mostValueBook;
        int T;
        int M;
        double median;
        int average;
        int[] curPercentile;
        int curLibraryDaysUsedToShipBooks;


        public Library(int libraryId, int[] sortedBookIdArray, int sumOfBookValues, int mostValueBook, int t, int m, double median, int average, int[] curPercentile, int curLibraryDaysUsedToShipBooks) {
            this.libraryId = libraryId;
            this.sortedBookIdArray = sortedBookIdArray;
            this.sumOfBookValues = sumOfBookValues;
            this.mostValueBook = mostValueBook;
            T = t;
            M = m;
            this.median = median;
            this.average = average;
            this.curPercentile = curPercentile;
            this.curLibraryDaysUsedToShipBooks = curLibraryDaysUsedToShipBooks;
        }
    }
}
