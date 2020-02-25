package google.HashCode2020;


/*
this is the contest of google hash code 2020
 */

import java.io.*;
import java.util.*;

public class HashCodeSolvePriorityQueue {
    private final static String PATH = "/Users/Vincent_Xia/IdeaProjects/LeetCode/src/main/java/google/HashCode2020/";
    //SKIP_BIT 是取4的倍数 这样设置长度的时候方便很多
    private final static int SKIP_BIT = 12;

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
            //存某一本书在那些图书馆里面出现过
            Map<Integer, Set<Integer>> map = new HashMap<>();
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
            int[] originalBooksArray = new int[token.length];
            for(int i = 0; i < B; ++i) {
                originalBooksArray[i] = Integer.valueOf(token[i]);
            }

            //get library and store libraries
            Library[] orignalLibrariesArray = new Library[L];
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
                PriorityQueue<Integer> bookIdHeap = new PriorityQueue<>((a,b) -> originalBooksArray[a] - originalBooksArray[b]);
                for(String bookIdString: token) {
                    int bookId = Integer.valueOf(bookIdString);
                    bookIdHeap.add(bookId);
                    if (bookIdHeap.size() == curLibBookListSizeLimit + 1) {
                        bookIdHeap.poll();
                    }
                }
                int[] sortedBookIdArray = new int[curLibBookListSizeLimit];
                int sumOfBookValues = 0;
                int[] prefixSumAcsending = new int[curLibBookListSizeLimit];
                int tempSum = 0;
                for (int i = curLibBookListSizeLimit - 1; i >= 0; --i) {
                    int curBookId = bookIdHeap.remove();
                    map.putIfAbsent(curBookId, new HashSet<>());
                    map.get(curBookId).add(libraryId);
                    sumOfBookValues += originalBooksArray[curBookId];
                    sortedBookIdArray[i] = curBookId;
                    tempSum += originalBooksArray[curBookId];
                    prefixSumAcsending[curLibBookListSizeLimit - i - 1] = tempSum;
                }

                int[] curPercentile = getCurPercentileAndAddToTotalPercentileArray(prefixSumAcsending, curLibBookListSizeLimit, totalPercentile);
                //get total day
                int curLibraryDaysUsedToShipBooks = (int)Math.ceil(1.0 * curLibBookListSizeLimit / M) ;
                totalCumulativeDay += curLibraryDaysUsedToShipBooks;
                //get total sum
                sumOfAllLibrariesPartBooksScore += sumOfBookValues;
                //get total cumulative day
                double curMedian = getMedian(sortedBookIdArray, originalBooksArray);
                int curAverage = getAverage(sumOfBookValues, curLibBookListSizeLimit);
                orignalLibrariesArray[libraryId] = new Library(libraryId, sortedBookIdArray, sumOfBookValues, originalBooksArray[sortedBookIdArray[0]], T, M, curMedian, curAverage, curPercentile, curLibraryDaysUsedToShipBooks);
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
            double optimalCoefficient = -1;
            String optimalPattern = "";

            //here we have list of library and we are to sort library base on
            /**
             * 这里可以多试试不同的策略,我在这里用一个priorityqueue，方法和排序是一样的
             */
            //第三种方法
//            double finalSumOfAllLibrariesPartBooksScore = sumOfAllLibrariesPartBooksScore;
//            Arrays.sort(librariesArray, (a, b) -> Double.compare(a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T, b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T) == 0 ?
//                a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues : Double.compare(b.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - b.sumOfBookValues) * b.T, a.sumOfBookValues - (finalSumOfAllLibrariesPartBooksScore - a.sumOfBookValues) * a.T));
            double finalTotalCumulativeDay = totalCumulativeDay;
            System.out.println("一共有多少天 ： " + finalTotalCumulativeDay);

            for (int i = 1; i <= 1; i++) {
                double tempMaxBookScore = 0;
                TotalSum totalSum = new TotalSum(sumOfAllLibrariesPartBooksScore);
                CurrentDay currentDay = new CurrentDay(0);
                //empty pq
                PriorityQueue<Library> pq = new PriorityQueue<>((a, b) -> Double.compare(a.sumOfBookValues - totalPercentile[Math.min(19, (int)Math.floor(20.0 * (D - currentDay.curDay) / D))], b.sumOfBookValues - totalPercentile[Math.min(19, (int)Math.floor(20.0 * (D - currentDay.curDay) / D))]) == 0 ?
                        a.sumOfBookValues == b.sumOfBookValues ? a.mostValueBook == b.mostValueBook ? a.T - b.T : b.mostValueBook - a.mostValueBook : b.sumOfBookValues - a.sumOfBookValues : Double.compare(b.sumOfBookValues - totalPercentile[Math.min(19, (int)Math.floor(20.0 * (D - currentDay.curDay) / D))], a.sumOfBookValues - totalPercentile[Math.min(19, (int)Math.floor(20.0 * (D - currentDay.curDay) / D))]));
                //add library to pq
                Library[] librariesArray = orignalLibrariesArray.clone();
                int[] bookArrayCopy = originalBooksArray.clone();

                for (Library curLibrary: librariesArray) {
                    pq.add(curLibrary);
                }
//                System.out.println("pq size = " + pq.size());
                //这里0x1000 和 SKIP_BIT是相呼应的
//            String skipPattern = Integer.toBinaryString(0x1000 | i).substring(1);
                String skipPattern = "111111111111";
                int skipPatternLength = skipPattern.length();
                //first to store library to LibrarySetupIdList

                //tempDetails to store all the details
                List<String> tempDetails = new ArrayList<>();
                int cnt = 0;    //point to the skipPattern

                //这个libraryUsed就是最终究竟有几个图书馆被用到
                int libraryUsed = 0;

                while (!pq.isEmpty() && currentDay.curDay < D) {
                    Library curLibrary = pq.poll();
//                    System.out.println("cur pq size = " + pq.size());
                    int curLibraryId = curLibrary.libraryId;

                    int setupDate = librariesArray[curLibraryId].T;
                    int BooksShippedPerDay = librariesArray[curLibraryId].M;
                    int[] curLibraryAllBookIdList = librariesArray[curLibraryId].sortedBookIdArray;
                    int curLibraryAllBookIdListSize = curLibraryAllBookIdList.length;

                    if (setupDate + currentDay.curDay >= D) continue;
                    if (librariesArray[curLibraryId].sumOfBookValues == 0) continue;
                    //看我是1还是0 是0就跳过
                    if (cnt < skipPatternLength && skipPattern.charAt(cnt) == '0') {
                        cnt++;
                        continue;
                    }
                    cnt++;

                    int curLibraryBooksToBeChosenSize = (int) Math.min(curLibraryAllBookIdListSize, 1.0 * (D - setupDate - currentDay.curDay) * BooksShippedPerDay);
//                        details.add(String.join(" ", Arrays.copyOfRange(bookArrayStringVersion, 0, curLibraryBooksChosenSize)));
                    StringBuilder tempBooksChosenIdBuffer = new StringBuilder();
                    int bookChosenCnt = 0;
                    for (int curBookId : curLibraryAllBookIdList) {
                        if (bookChosenCnt == curLibraryBooksToBeChosenSize) break;
                        if (bookArrayCopy[curBookId] == 0) continue;
                        tempMaxBookScore += bookArrayCopy[curBookId];
//                        System.out.println("cur book value " + bookArrayCopy[curBookId]);
                        for (int relatedLibraryId : map.get(curBookId)) {
                            librariesArray[relatedLibraryId].sumOfBookValues -= bookArrayCopy[curBookId];
                            totalSum.finalSumOfAllLibrariesPartBooksScore -= bookArrayCopy[curBookId];
                        }
                        tempBooksChosenIdBuffer.append(curBookId).append(" ");
                        bookArrayCopy[curBookId] = 0;
                        bookChosenCnt++;
                    }
//                    if (bookChosenCnt == 0) continue;
                    tempDetails.add(curLibraryId + " " + bookChosenCnt);
                    tempDetails.add(tempBooksChosenIdBuffer.toString());
                    currentDay.curDay += setupDate;
                    libraryUsed++;
                }
                List<String> finalTempDetails = new ArrayList<>();
                finalTempDetails.add(String.valueOf(libraryUsed));
                finalTempDetails.addAll(tempDetails);

                if (tempMaxBookScore > globalMaxBookScore) {
//                optimalPattern = skipPattern;
                    globalMaxBookScore = tempMaxBookScore;
                    globalDetails = new ArrayList<>(finalTempDetails);
                }
            }

            totalBookScore += globalMaxBookScore;

            System.out.println(fileName + " max score is " + globalMaxBookScore + " with multiplier = " + multiplier + " with pattern = " + optimalPattern + "skipPattern length = " + optimalPattern.length());

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
    private static int[] getCurPercentileAndAddToTotalPercentileArray(int[] prefixSumAcsending, int n, double[] totalPercentile) {
        int[] ret = new int[20];
        for (int i = 5; i <= 100; i = i + 5) {
            ret[(i - 1) / 5] = prefixSumAcsending[(int)(Math.ceil(n * i / 100.0) - 1)];
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
}

class Library {
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

class TotalSum {
    double finalSumOfAllLibrariesPartBooksScore;

    public TotalSum(double finalSumOfAllLibrariesPartBooksScore) {
        this.finalSumOfAllLibrariesPartBooksScore = finalSumOfAllLibrariesPartBooksScore;
    }
}

class CurrentDay {
    int curDay;

    public CurrentDay(int curDay) {
        this.curDay = curDay;
    }
}
